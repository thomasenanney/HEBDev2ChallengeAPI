package hebdev2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	public List<Item> findItems() {
		
		log.info("BusinessManager::findAllItems started");
	
		return DataManager.getInstance().findAllItems();

	}

	public Item addItem(Item item) {
		
		log.info("BusinessManager::addItem started");
		
		Item newItem = DataManager.getInstance().addItem(item);
		return newItem;
	}
	
	public List<Item> deleteItem(String itemId) {	
		log.info("BusinessManager::deleteItem started itemId: " + itemId);
		return DataManager.getInstance().deleteItem(itemId);
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
	
		return DataManager.getInstance().addItems(items);
	}

	public List<Item> addItemsFromFile(File file) {
		log.info("BusinessManager::addItemsFromFile started");

		BufferedReader reader = null;
		List<Item> items = new ArrayList<Item>();
		
		try {
			
		    reader = new BufferedReader(new FileReader(file));
		    String text = null;
		    
		    while ((text = reader.readLine()) != null) {
		    	
		    	if(!text.contains("ID")){		
		    		
		    		List<String> list = Arrays.asList(text.split(","));
		    		Item item = new Item();
		    		item.setId(list.get(0));
		    		item.setDescription(list.get(1));
		    		item.setLastSold(list.get(2));
		    		item.setShelfLife(list.get(3));
		    		item.setDepartment(list.get(4));
		    		item.setPrice(list.get(5));
		    		item.setUnit(list.get(6));
		    		item.setXFor(list.get(7));
		    		item.setCost(list.get(8));
		    		items.add(item);
		    	}
		    }
		    
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		    log.error(e);
		    
		} catch (IOException e) {
		    e.printStackTrace();
		    log.error(e);
		    
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    	log.error(e);
		    }
		}
		
		return this.addItems(items);
	}

}
