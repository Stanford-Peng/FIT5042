package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="INDUSTRY")
@NamedQueries({
	@NamedQuery(name = "Industry.findAll", query = "SELECT i FROM Industry i order by i.industryID")
})
public class Industry implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int industryID;
	private String industryName;
	private Set<Customer> customers;
	
	
	public Industry() {
		super();
	}


	public Industry(int industryID, String industryName) {
		super();
		this.industryID = industryID;
		this.industryName = industryName;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getIndustryID() {
		return industryID;
	}


	public void setIndustryID(int industryID) {
		this.industryID = industryID;
	}


	@Column(name = "name")
	public String getIndustryName() {
		return industryName;
	}


	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}


	@OneToMany(fetch=FetchType.LAZY, mappedBy="customerIndustryType")	
	public Set<Customer> getCustomers() {
		return customers;
	}


	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
	
	
}
