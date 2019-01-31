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
		
		log.info("DataManager::insertItem started item: " + item);
		
		List<Item> items = this.findAllItems();
		for(Item itemToCheck : items) {
			if (item.getId().equals(itemToCheck.getId())){
				this.deleteItem(item.getId());
			}
		}
		
		//instert item into collection
		BasicDBObject doc = new BasicDBObject();
		
		//doc.put("_id", item.getObjectId());
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
		
		item.setObjectId(doc.get("_id").toString());
		
		return item;
	}
	
	public Item mapItemFromdDBObject(DBObject dbObject) {

		//map the object returned from db to an item object
		Item item = new Item();

		item.setObjectId((String) dbObject.get("_id").toString());
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
	
	public List<Item> findAllItems() {
		
		log.info("DataManager::findAllItems started");

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
			log.error(e);
		}

		return null;
	}

	public void deleteCollection() {
		log.info("DataManager::deleteCollection started");

		hebdev2DB.getCollection("Items").drop();
	}

	public List<Item> deleteItem(String itemId) {
		
		log.info("DataManager::deleteItem started itemId: " + itemId);
	
		List<Item> items = this.findAllItems();
		
		if (itemId == null)
			return null;
		
		String objectId = "";
		for(Item item : items){
			if(item.getId().equals(itemId)){
				objectId = item.getObjectId();
			}
		}
		if (objectId == null)
			return null;
	
		try {
			DBObject searchById = new BasicDBObject("_id", new ObjectId(objectId));

			DBObject ItemObject = ItemCollection.findOne(searchById); 

			ItemCollection.remove(ItemObject);

		} catch (Exception e) {
			log.error("DBManager::findItemById Exception e=", e);
		}

		return this.findAllItems();

	}
}
