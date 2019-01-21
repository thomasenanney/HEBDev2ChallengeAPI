package hebdev2.services.v1;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")

public class ItemsHolder implements Serializable {
	
	/*
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	@XmlElement(name = "id")
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
}
