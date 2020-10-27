package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="CUSTOMER")
@NamedQueries({
	@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c order by c.customerID desc")
	,@NamedQuery(name = "Customer.findByUser", query = "SELECT c FROM Customer c WHERE c.normalUser.account = :account") // cannot use the parent attribute
})
public class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int customerID;
	private Industry customerIndustryType;
	private String customerName;
	private Address customerAddress;
	private String customerNationality;
	private Date customerAddedDate;
	private Double customerDiscountRate;
	private String customerScale;
	private List<Contact> customerContacts;
	private NormalUser normalUser;
	
	public Customer() {
		super();
	}
	public Customer(int customerID, Industry customerIndustryType, String customerName, Address customerAddress,
			String customerNationality, Date customerAddedDate, Double customerDiscountRate, String customerScale) {
		super();
		this.customerID = customerID;
		this.customerIndustryType = customerIndustryType;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerNationality = customerNationality;
		this.customerAddedDate = customerAddedDate;
		this.customerDiscountRate = customerDiscountRate;
		this.customerScale = customerScale;
		
	}
	
	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "industry_id", referencedColumnName="id")//careful
	public Industry getCustomerIndustryType() {
		return customerIndustryType;
	}
	public void setCustomerIndustryType(Industry customerIndustryType) {
		this.customerIndustryType = customerIndustryType;
	}
	
	
	@Column(name="name")
	@Size(min = 1, max = 50)
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Embedded
	public Address getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	@Column(name = "nationality")
	@Size(min = 1, max = 50)
	public String getCustomerNationality() {
		return customerNationality;
	}
	public void setCustomerNationality(String customerNationality) {
		this.customerNationality = customerNationality;
	}
	
	@Temporal(TemporalType.DATE)
	Date getCustomerAddedDate() {
		return customerAddedDate;
	}
	public void setCustomerAddedDate(Date customerAddedDate) {
		this.customerAddedDate = customerAddedDate;
	}
	
	@Column(name = "discount_rate")
	@Max(value=1)
	@Min(value=0)
	public Double getCustomerDiscountRate() {
		return customerDiscountRate;
	}
	public void setCustomerDiscountRate(Double customerDiscountRate) {
		this.customerDiscountRate = customerDiscountRate;
	}
	
	@Column(name="scale")
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[SML]")
	public String getCustomerScale() {
		return customerScale;
	}
	public void setCustomerScale(String customerScale) {
		this.customerScale = customerScale;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="customer")
	public List<Contact> getCustomerContact() {
		return customerContacts;
	}
	public void setCustomerContact(List<Contact> customerContacts) {
		this.customerContacts = customerContacts;
	}
	
	
	@ManyToOne(fetch=FetchType.EAGER)	
	@JoinColumn(name="normalUser", referencedColumnName="account") //careful, always access via getter naming error cause biggest bug
	public NormalUser getNormalUser() {
		return normalUser;
	}
	public void setNormalUser(NormalUser normalUser) {
		this.normalUser = normalUser;
	}
	
	@Override
    public String toString() {
        return this.customerID + " - " + this.customerName;
    }

}
