package helloworld;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class NamedStorageBean
 */
@Singleton
@LocalBean
public class NameStorageBean {

	/**
	 * Default constructor. 
	 */
	public NameStorageBean() {
		// TODO Auto-generated constructor stub
	}
	
	private String name = "World";
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
