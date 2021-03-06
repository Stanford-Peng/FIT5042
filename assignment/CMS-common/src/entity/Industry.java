package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
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


	@OneToMany(cascade = {CascadeType.MERGE}, fetch=FetchType.EAGER, mappedBy="customerIndustryType") //eager can be optimized when deleting an industry. cascade impact the operation from parent to child , mappedBy="customerIndustryType"	
	public Set<Customer> getCustomers() {
		return customers;
	}


	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	//unidirectional cannot change anything
	
	
	@Override
	public String toString() {		
		return industryID + "-" + (industryName == null ? "" : industryName);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + industryID;
		result = prime * result + ((industryName == null) ? 0 : industryName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Industry other = (Industry) obj;
		if (industryID != other.industryID)
			return false;
		if (industryName == null) {
			if (other.industryName != null)
				return false;
		} else if (!industryName.equals(other.industryName))
			return false;
		return true;
	}
	
	
	
}
