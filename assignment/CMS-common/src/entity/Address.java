package entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
@Access(AccessType.PROPERTY)
public class Address implements Serializable {
	private String streetNumber;
    private String streetAddress;
    private String suburb;
    private String postcode;
    private String state;
    
    public Address() {
    	super();
    }
	public Address(String streetNumber, String streetAddress, String suburb, String postcode, String state) {
		super();
		this.streetNumber = streetNumber;
		this.streetAddress = streetAddress;
		this.suburb = suburb;
		this.postcode = postcode;
		this.state = state;
	}
	
	@Column(name = "street_number")
	//@Pattern(regexp="\\d{1,4}")
	//@Min(1)
	//@Max(1000)
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	@Column(name = "street_address")
	@Size(min = 1, max = 50)
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	@Column(name = "suburb")
	@Size(min = 1, max = 50)
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	
	@Column(name = "postcode")
//	@Digits(integer=4, fraction=0, message="Postcode Error")
	@Pattern(regexp="\\d{4}")
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@Column(name = "state")
	@Pattern(regexp = "NSW|QLD|SA|TAS|VIC|WA|ACT")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return streetNumber + " " + streetAddress + ", " + suburb + ", " + state + " " + postcode;
	}
    
	
}
