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
		for(Item item : this.items){
			DataManager.getInstance().instertItem(item);
		}
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
			Assert.assertTrue(item.getId().equals(returnedItem.getId()));
			Assert.assertTrue(item.getDescription().equals(returnedItem.getDescription()));
			Assert.assertTrue(item.getLastSold().equals(returnedItem.getLastSold()));
			Assert.assertTrue(item.getShelfLife().equals(returnedItem.getShelfLife()));
			Assert.assertTrue(item.getDepartment().equals(returnedItem.getDepartment()));
			Assert.assertTrue(item.getPrice().equals(returnedItem.getPrice()));
			Assert.assertTrue(item.getUnit().equals(returnedItem.getUnit()));
			Assert.assertTrue(item.getXFor().equals(returnedItem.getXFor()));
			Assert.assertTrue(item.getCost().equals(returnedItem.getCost()));
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


}
