package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="CONTACT")
@NamedQueries({
	@NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")
})
public class Contact implements Serializable{
	
	private String contactEmail;
	private String contactFirstName;
	private String contactLastName;
	private Date contactDOB;
	private Character contactGender;
	private String contactPhoneNo;
	private Customer customer;
	
	public Contact() {
		super();
	}


	public Contact(String contactEmail, String contactFirstName, String contactLastName, Date contactDOB,
			Character contactGender, String contactPhoneNo) {
		super();
		this.contactEmail = contactEmail;
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.contactDOB = contactDOB;
		this.contactGender = contactGender;
		this.contactPhoneNo = contactPhoneNo;
	}
	
	@Id
	@Column(name="email")
	@Pattern(regexp="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
	public String getContactEmail() {
		return contactEmail;
	}



	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}


	@Column(name="first_name")
	@Size(min = 1, max = 25)
	public String getContactFirstName() {
		return contactFirstName;
	}


	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	@Column(name="last_name")
	@Size(min = 1, max = 25)
	public String getContactLastName() {
		return contactLastName;
	}


	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	
	@Column(name="contact_dob")
	@Temporal(TemporalType.DATE)
	@Past
	public Date getContactDOB() {
		return contactDOB;
	}


	public void setContactDOB(Date contactDOB) {
		this.contactDOB = contactDOB;
	}


	@Column(name = "gender")
	public Character getContactGender() {
		return contactGender;
	}


	public void setContactGender(Character contactGender) {
		this.contactGender = contactGender;
	}


	@Column(name="phone_no")
	@Digits(fraction = 0, integer = 15)
	public String getContactPhoneNo() {
		return contactPhoneNo;
	}


	public void setContactPhoneNo(String contactPhoneNo) {
		this.contactPhoneNo = contactPhoneNo;
	}

	@ManyToOne
	@JoinColumn(name="customer_id", referencedColumnName="customer_id")
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	

	
}
