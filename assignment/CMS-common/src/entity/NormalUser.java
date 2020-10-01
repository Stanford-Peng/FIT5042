package entity;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("Normal")
@NamedQueries({
	@NamedQuery(name = "NormalUser.findAll", query = "SELECT u FROM NormalUser u")
})
public class NormalUser extends User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<Customer> customers;
	
	public NormalUser() {
		super();
	}
	

	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
}
