package hebdev2;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import hebdev2.services.v1.Item;

public class BusinessManager {
	
	private static final Logger log = Logger.getLogger(BusinessManager.class.getName());
	private static BusinessManager INSTANCE = new BusinessManager();
	
	public static BusinessManager getInstance() {
		return INSTANCE;
	}
	
	private BusinessManager() {
		
	}
	
public Item findItem(String ItemId) throws Exception {
		
		log.info("BusinessManager::findItemById started");
		
		Item item = DataManager.getInstance().findItemById(ItemId);
		
		if (item == null) {
			throw new Exception("Nothing Found");
		}
		
		return item;
	}

	public List<Item> findItems() {
		
		log.info("BusinessManager::findAllItems started");
	
		return DataManager.getInstance().findAllItems();

	}

	public Item addItem(Item item) {
		
		log.info("BusinessManager::addItem started");
		
		Item newItem = DataManager.getInstance().instertItem(item);
		return newItem;
	}
	
	public Item updateItemAttribute(String ItemId, String attribute, String value) {
		
		return DataManager.getInstance().updateItemAttribute(ItemId, attribute, value);
		
	}
	
	public void deleteItem(String itemId) {	
		log.info("BusinessManager::deleteItem started itemId: " + itemId);
		DataManager.getInstance().deleteItem(itemId);	
	}

	public List<Item> findItemsByString(String string) {
		
		log.info("BusinessManager::findItemByString started");
		
		List<Item> items = DataManager.getInstance().findAllItems();
		List<Item> itemsToReturn = new ArrayList<Item>();
		for (Item itemInItems : items){
			if(itemInItems.checkItemForString(itemInItems, string)){
				itemsToReturn.add(itemInItems);
			}
		}
		return itemsToReturn;
	}

	public void deleteAllItems() {
		DataManager.getInstance().deleteCollection();		
	}

	public List<Item> addItems(List<Item> items) {
		log.info("BusinessManager::addItems started");
		
		List<Item> itemsToReturn = new ArrayList<Item>();
				
		for (Item item : items) {
			Item newItem = DataManager.getInstance().instertItem(item);
			itemsToReturn.add(newItem);
		}
	
		return itemsToReturn;
	}

}
