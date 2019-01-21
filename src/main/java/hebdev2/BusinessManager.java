package hebdev2;

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
		
		log.info("BusinessManager::findItem started");
		

		Item item = DataManager.getInstance().findItemById(ItemId);
		
		if (item == null) {
			throw new Exception("Nothing Found");
		}
		
		return item;
	}

	public List<Item> findItems() {
	
		return DataManager.getInstance().findAllItems();

	}

	public Item addItem(Item item) {
		Item newItem = DataManager.getInstance().instertItem(item);
		return newItem;
	}
	
	public Item updateItemAttribute(String ItemId, String attribute, String value) {
		
		return DataManager.getInstance().updateItemAttribute(ItemId, attribute, value);
		
	}
	
	public void deleteItem(String ItemId) {		
		return;		
	}

	public List<Item> findItemsByString(String string) {
		return DataManager.getInstance().findItemsByString(string);
		//return null;
	}

}
