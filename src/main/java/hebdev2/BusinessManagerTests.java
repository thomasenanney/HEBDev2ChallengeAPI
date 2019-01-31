package hebdev2;

import java.util.ArrayList;
import java.util.List;

import hebdev2.services.v1.Item;
import junit.framework.Assert;
import junit.framework.TestCase;

public class BusinessManagerTests extends TestCase {

	public List<Item> items = new ArrayList<Item>();

	protected void setUp() throws Exception {
		super.setUp();
		this.items = BusinessManager.getInstance().findItems();
		BusinessManager.getInstance().deleteAllItems();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		BusinessManager.getInstance().deleteAllItems();
		DataManager.getInstance().addItems(this.items);
	}
	
	public void testInstertItem() {
    	Item item = new Item();
		item.setId("753542");
		item.setDescription("banana");
		item.setLastSold("9/5/2017");
		item.setShelfLife("4d");
		item.setDepartment("Produce");
		item.setPrice("$2.99");
		item.setUnit("lb");
		item.setXFor("1");
		item.setCost("$0.44");
		BusinessManager.getInstance().addItem(item);
    	
    	List<Item> items = BusinessManager.getInstance().findItems();
		Assert.assertTrue(items.size() == 1);
		for (Item returnedItem : items){
			Assert.assertTrue(this.compareItems(returnedItem, item));
		}
    }
	
	public void testInstertExistingItem() {
    	Item item = new Item();
		item.setId("753542");
		item.setDescription("banana");
		item.setLastSold("9/5/2017");
		item.setShelfLife("4d");
		item.setDepartment("Produce");
		item.setPrice("$2.99");
		item.setUnit("lb");
		item.setXFor("1");
		item.setCost("$0.44");
		BusinessManager.getInstance().addItem(item);
		
		Item item2 = new Item();
		item2.setId("753542");
		item2.setDescription("banana2");
		item2.setLastSold("9/5/2017");
		item2.setShelfLife("4d");
		item2.setDepartment("Produce");
		item2.setPrice("$2.99");
		item2.setUnit("lb");
		item2.setXFor("1");
		item2.setCost("$0.44");
		
		BusinessManager.getInstance().addItem(item2);
    	
    	List<Item> items = BusinessManager.getInstance().findItems();
		Assert.assertTrue(items.size() == 1);
		for (Item returnedItem : items){
			Assert.assertTrue(this.compareItems(returnedItem, item2));
			Assert.assertFalse(this.compareItems(returnedItem, item));
		}
    }
	
	public void testDeleteItem() {
    	Item item = new Item();
		item.setId("753542");
		item.setDescription("banana");
		item.setLastSold("9/5/2017");
		item.setShelfLife("4d");
		item.setDepartment("Produce");
		item.setPrice("$2.99");
		item.setUnit("lb");
		item.setXFor("1");
		item.setCost("$0.44");
		BusinessManager.getInstance().addItem(item);
    	
    	BusinessManager.getInstance().deleteItem(item.getId());
    	List<Item> items = BusinessManager.getInstance().findItems();

		Assert.assertTrue(items.size() == 0);
		
    }
	
	public void testFindItemByString() {
    	Item item = new Item();
		item.setId("753542");
		item.setDescription("banana");
		item.setLastSold("9/5/2017");
		item.setShelfLife("4d");
		item.setDepartment("Produce");
		item.setPrice("$2.99");
		item.setUnit("lb");
		item.setXFor("1");
		item.setCost("$0.44");
		
		Item item2 = new Item();
		item2.setId("1189");
		item2.setDescription("hamburger buns");
		item2.setLastSold("9/13/2017");
		item2.setShelfLife("14d");
		item2.setDepartment("Grocery");
		item2.setPrice("$1.75");
		item2.setUnit("Each");
		item2.setXFor("1");
		item2.setCost("$0.14");
		
		Item item3 = new Item();
		item3.setId("9000005");
		item3.setDescription("Pain");
		item3.setLastSold("9/24/2017");
		item3.setShelfLife("365d");
		item3.setDepartment("Pharmacy");
		item3.setPrice("$2.89");
		item3.setUnit("Each");
		item3.setXFor("1");
		item3.setCost("$1.00");
		
		BusinessManager.getInstance().addItem(item);
		BusinessManager.getInstance().addItem(item2);
		BusinessManager.getInstance().addItem(item3);
    	
    	List<Item> items = BusinessManager.getInstance().findItemsByString("Produce");
		Assert.assertTrue(items.size() == 1);
		for (Item returnedItem : items){
			Assert.assertTrue(this.compareItems(returnedItem, item));
		}
		List<Item> items2 = BusinessManager.getInstance().findItemsByString("Grocery");
		Assert.assertTrue(items2.size() == 1);
		for (Item returnedItem : items2){
			Assert.assertTrue(this.compareItems(returnedItem, item2));
		}
		List<Item> items3 = BusinessManager.getInstance().findItemsByString("Pharmacy");
		Assert.assertTrue(items3.size() == 1);
		for (Item returnedItem : items3){
			Assert.assertTrue(this.compareItems(returnedItem, item3));
		}
    }
	
	public Boolean compareItems (Item item1, Item item2) {
		if (item1.getId().equals(item2.getId()) && item1.getDescription().equals(item2.getDescription()) 
				&& item1.getLastSold().equals(item2.getLastSold()) && item1.getShelfLife().equals(item2.getShelfLife())
				&& item1.getDepartment().equals(item2.getDepartment()) && item1.getPrice().equals(item2.getPrice())
				&& item1.getUnit().equals(item2.getUnit()) && item1.getXFor().equals(item2.getXFor())
				&& item1.getCost().equals(item2.getCost())) {
			return true;
		} else {
			return false;
		}
	}

	public void testCreateItems() {
    	Item item = new Item();
		item.setId("753542");
		item.setDescription("banana");
		item.setLastSold("9/5/2017");
		item.setShelfLife("4d");
		item.setDepartment("Produce");
		item.setPrice("$2.99");
		item.setUnit("lb");
		item.setXFor("1");
		item.setCost("$0.44");
		
		Item item2 = new Item();
		item2.setId("1189");
		item2.setDescription("hamburger buns");
		item2.setLastSold("9/13/2017");
		item2.setShelfLife("14d");
		item2.setDepartment("Grocery");
		item2.setPrice("$1.75");
		item2.setUnit("Each");
		item2.setXFor("1");
		item2.setCost("$0.14");
		
		Item item3 = new Item();
		item3.setId("9000005");
		item3.setDescription("Pain");
		item3.setLastSold("9/24/2017");
		item3.setShelfLife("365d");
		item3.setDepartment("Pharmacy");
		item3.setPrice("$2.89");
		item3.setUnit("Each");
		item3.setXFor("1");
		item3.setCost("$1.00");
		
		List<Item> items = new ArrayList<Item>();
		items.add(item);
		items.add(item2);
		items.add(item3);

		List<Item> returnedItems = BusinessManager.getInstance().addItems(items);
    	
		Assert.assertTrue(returnedItems.size() == 3);
		for (Item returnedItem : returnedItems){
			if (returnedItem.getId().equals(item.getId())) {
				Assert.assertTrue(this.compareItems(returnedItem, item));
			} else if (returnedItem.getId().equals(item2.getId())) {
				Assert.assertTrue(this.compareItems(returnedItem, item2));
			} else if (returnedItem.getId().equals(item3.getId())) {
				Assert.assertTrue(this.compareItems(returnedItem, item3));
			}
		}
		
    }

}
