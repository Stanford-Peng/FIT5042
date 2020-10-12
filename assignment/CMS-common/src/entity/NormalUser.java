package entity;


import java.io.Serializable;
import java.util.LinkedHashSet;
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
	//private String account;
	private static final long serialVersionUID = 1L;
	private LinkedHashSet<Customer> customers;
	
	public NormalUser() {
		super();
	}
	

	@OneToMany(fetch=FetchType.EAGER, mappedBy="normalUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //delete user will delete all customer
	public LinkedHashSet<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(LinkedHashSet<Customer> customers) {
		this.customers = customers;
	}
	
//	@Column(name = "account")
//	//have to override in order to get account to use
//	public String getAccount() {
//		return account;
//	}
//	
//	public void setAccount(String account) {
//		this.account = account;
//	}
}
