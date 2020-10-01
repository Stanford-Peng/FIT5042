package repository;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entity.Contact;


@Remote
public interface ContactRepository {
	
	public void addContact(Contact contact) throws Exception;
	
	public Set<Contact> getAllContacts() throws Exception;
	
	public void updateContact(Contact contact) throws Exception;
	
	public void deleteContact(String contactEmail) throws Exception;
	
	public List<Contact> getContactsByCustomerID(int customerID) throws Exception;

}
