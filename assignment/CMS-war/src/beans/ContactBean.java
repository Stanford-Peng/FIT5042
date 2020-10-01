package beans;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import entity.Contact;
import repository.ContactRepository;

@Named
@ApplicationScoped
public class ContactBean {
	
	@EJB
	ContactRepository contactRepository;
	
	public Set<Contact> getAllContacts(){
		try {
		Set<Contact> contacts = contactRepository.getAllContacts();
		return contacts;
		}catch (Exception ex) {
			Logger.getLogger(ContactBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public boolean addContact(Contact contact) {
		try {
			contactRepository.addContact(contact);
			return true;
		}catch(Exception ex) {
			Logger.getLogger(ContactBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	
	public boolean removeContact(String contactEmail) {
		try {
			contactRepository.deleteContact(contactEmail);
			return true;
		} catch(Exception ex) {
			Logger.getLogger(ContactBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	
	public boolean editCustomer(Contact contact) {
		try {
			contactRepository.updateContact(contact);
			return true;
		}catch(Exception ex) {
			Logger.getLogger(ContactBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	
	public List<Contact> getContactsByCustomerID(int customerID){
		
		try {
			return contactRepository.getContactsByCustomerID(customerID);
			
		}
		catch(Exception ex) {
			Logger.getLogger(ContactBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
		
	}
	

}
