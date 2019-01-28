package hebdev2.services.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")

public class Item implements Serializable{
	
	private static final long serialVersionUID = 1;
	
	@XmlElement(name = "objectId")
	private String objectId;
	
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	@XmlElement(name = "id")
	private String id;
	
	@XmlElement(name = "description")
	private String description;
	
	@XmlElement(name = "lastSold")
	private String lastSold;
	
	@XmlElement(name = "shelfLife")
	private String shelfLife;
	
	@XmlElement(name = "department")
	private String department;
	
	@XmlElement(name = "price")
	private String price;
	
	@XmlElement(name = "unit")
	private String unit;
	
	@XmlElement(name = "xFor")
	private String xFor;
	
	@XmlElement(name = "cost")
	private String cost;

	public String getLastSold() {
		return lastSold;
	}

	public void setLastSold(String lastSold) {
		this.lastSold = lastSold;
	}

	public String getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getXFor() {
		return xFor;
	}

	public void setXFor(String xFor) {
		this.xFor = xFor;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean checkItemForString(Item item, String string){
		if(item.getCost().contains(string) || item.getId().contains(string)
				|| item.getDescription().contains(string)
				|| item.getLastSold().contains(string)
				|| item.getShelfLife().contains(string)
				|| item.getDepartment().contains(string)
				|| item.getPrice().contains(string)
				|| item.getUnit().contains(string)
				|| item.getXFor().contains(string)
				) {
			return true;
		} else {
			return false;
		}
	}
	
	

}
