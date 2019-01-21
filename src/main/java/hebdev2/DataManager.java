package hebdev2;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import hebdev2.services.v1.Item;

public class DataManager {
	
	private static final Logger log = Logger.getLogger(DataManager.class.getName());
	
	private static DB hebdev2DB;
	
	private static DBCollection ItemCollection;
	
	private static DataManager INSTANCE;
	
	public static DataManager getInstance() {
		
		if (INSTANCE == null){
			INSTANCE = new DataManager();
		}
		
		return INSTANCE;
	}
	
	private DataManager() {
		
		//set up connection to mongodb
		try {
			MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
			
			hebdev2DB = mongoClient.getDB("hebdev2");
			
			ItemCollection = hebdev2DB.getCollection("Items");
			
		} catch (Exception e){
			log.error("db connection error e=", e);
		}
		
	}
	
	public Item instertItem(Item item) {
		
		//instert item into collection
		BasicDBObject doc = new BasicDBObject();
		
		doc.put("id", item.getId());
		doc.put("description", item.getDescription());
		doc.put("lastSold", item.getLastSold());
		doc.put("shelfLife", item.getShelfLife());
		doc.put("department", item.getDepartment());
		doc.put("price", item.getPrice());
		doc.put("unit", item.getUnit());
		doc.put("xFor", item.getXFor());
		doc.put("cost", item.getCost());
		
		ItemCollection.insert(doc);
		
		item.setId(doc.get("id").toString());
		
		return item;
	}
	
	public Item mapItemFromdDBObject(DBObject dbObject) {

		//map the object returned from db to an item object
		Item item = new Item();

		item.setId((String) dbObject.get("id").toString());
		item.setDescription((String) dbObject.get("description"));
		item.setLastSold((String) dbObject.get("lastSold"));
		item.setShelfLife((String) dbObject.get("shelfLife"));
		item.setDepartment((String) dbObject.get("department"));
		item.setPrice((String) dbObject.get("price"));
		item.setUnit((String) dbObject.get("unit"));
		item.setXFor((String) dbObject.get("xFor"));
		item.setCost((String) dbObject.get("cost"));

		return item;
	}
	
	
	// Find Item by Id
	public Item findItemById(String ItemIdString) {
		
		if (ItemIdString == null)
			return null;
	
		try {
			DBObject searchById = new BasicDBObject("_id", new ObjectId(ItemIdString));

			DBObject ItemObject = ItemCollection.findOne(searchById);

			if (ItemObject != null) {
				return mapItemFromdDBObject(ItemObject);
			} else {
				return null;
			}

		} catch (Exception e) {
			log.error("DBManager::findItemById Exception e=", e);
		}

		return null;
	
	}
	
	// Find item by string
		public List<Item> findItemsByString(String string) {
		
			if (string == null)
				return null;

			List<Item> items = new ArrayList<Item>();

			//get all items from db
			//not the best way, a better way would be to perform search in db
			try {

				DBCursor cursor = ItemCollection.find();

				if (cursor != null) {

					while (cursor.hasNext()) {

						BasicDBObject doc = (BasicDBObject) cursor.next();

						Item item = mapItemFromdDBObject(doc);
						
						items.add(item);
					}
				}
			} catch (Exception e) {
				System.err.println(e);
			}
			List<Item> itemsToReturn = new ArrayList<Item>();
			for (Item itemInItems : items){
				if(itemInItems.checkItemForString(itemInItems, string)){
					itemsToReturn.add(itemInItems);
				}
			}
			return itemsToReturn;
		}
	
	public List<Item> findAllItems() {

		List<Item> items = new ArrayList<Item>();

		try {


			DBCursor cursor = ItemCollection.find();

			if (cursor != null) {

				while (cursor.hasNext()) {

					BasicDBObject doc = (BasicDBObject) cursor.next();

					Item item = mapItemFromdDBObject(doc);

					items.add(item);
				}

				return items;
			}

			return null;
		} catch (Exception e) {

		}

		return null;
	}

	
	public Item updateItemAttribute(String ItemId, String attribute,
			String value) {

		String updateValue = value;

		BasicDBObject doc = new BasicDBObject();

		doc.append("$set", new BasicDBObject().append(attribute, updateValue));

		DBObject searchById = new BasicDBObject("_id", new ObjectId(ItemId));

		ItemCollection.update(searchById, doc);


		return findItemById(ItemId);
	}
}
