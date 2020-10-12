package controllers.contact;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import beans.ContactBean;
import beans.CustomerBean;
import entity.Contact;

@RequestScoped
@Named
public class EditContactController {


	@Inject
	private ContactBean contactBean;
	@Inject
	private CustomerBean customerBean;
	
	private Contact contactToEdit;
	
	private int customerID;
	private String contactEmail;
	
	public EditContactController() {
		this.customerID = Integer.valueOf(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerID"));
		this.contactEmail = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contactEmail");

	}
	
	@PostConstruct
	public void init() {
		contactToEdit = contactBean.getContactByEmail(contactEmail);
		
	}
	
	
	
	public void editContact(Contact contact) {
		// Customer customer = customerBean.searchCustomerByID(customerID);
		
		//this.contactToEdit = contactBean.getContactByEmail(contactEmail);
		try {
			boolean result = contactBean.editContact(contact);
			if (result) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("The Contact has been updated succesfully"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to update : database error"));
			}

		} catch (Exception ex) {
			Logger.getLogger(CustomerContactsController.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed"));

		}

	}

	public Contact getContactToEdit() {
		return contactToEdit;
	}

	public void setContactToEdit(Contact contactToEdit) {
		this.contactToEdit = contactToEdit;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	
}
