package controllers.contact;

import java.util.ArrayList;
import java.util.List;
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
import entity.Customer;

@RequestScoped
@Named
public class CustomerContactsController {

	private int customerID;
	private List<Contact> contacts = new ArrayList<Contact>();
	private Contact contactToAdd = new Contact();
	

	public CustomerContactsController() {
		this.customerID = Integer.valueOf(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerID"));

	}

	@Inject
	private ContactBean contactBean;
	@Inject
	private CustomerBean customerBean;

	@PostConstruct
	public void init() {

		// if (contactBean.getContactsByCustomerID(customerID) != null) {
		this.contacts = contactBean.getContactsByCustomerID(customerID);
		// }
		

	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contact getContactToAdd() {
		return contactToAdd;
	}

	public void setContactToAdd(Contact contactToAdd) {
		this.contactToAdd = contactToAdd;
	}

	public void addContact(Contact contact) {
		try {
			Customer customer = customerBean.searchCustomerByID(customerID);
			
			
			boolean result = contactBean.addContact(contact);

			// boolean result2 = contactBean.addContact(contact);
			if (result) {
				// add the relationship from the other side
				contact.setCustomer(customer);
				customer.getCustomerContact().add(contact);
				// boolean result = contactBean.addContact(contact);
				boolean result2 = customerBean.editCustomer(customer);
				if(result2) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("The Contact has been added succesfully"));
				}else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("failed when merge it to customer"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed when adding contact"));
			}

		} catch (Exception ex) {
			Logger.getLogger(CustomerContactsController.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed"));

		}
	}

	public void deleteContact(String contactEmail) {
		try {
			Customer customer = customerBean.searchCustomerByID(customerID);
			Contact contact = contactBean.getContactByEmail(contactEmail);
			
			boolean result0 = contactBean.removeContact(contactEmail);

			
			if ( result0) {
				customer.getCustomerContact().remove(contact);				
				boolean result = customerBean.editCustomer(customer);
				if(result) {
				init();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("The Contact has been deleted succesfully"));
				}else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Failed when removing from customer"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed when removing contact"));
			}

		} catch (Exception ex) {
			Logger.getLogger(CustomerContactsController.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed"));
		}

	}



}
