package entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Admin")
public class Admin extends User implements Serializable{
	
	public Admin() {
		super();
	}
	

}
