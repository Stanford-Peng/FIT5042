package repository;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Contact;

@Stateless
public class JPAContact implements ContactRepository {

	@PersistenceContext(unitName = "CMS-ejb")
    private EntityManager entityManager;

	@Override
	public void addContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		entityManager.persist(contact);
		entityManager.flush();
		
	}

	@Override
	public Set<Contact> getAllContacts() throws Exception {
		// TODO Auto-generated method stub
		List<Contact> contacts = entityManager.createNamedQuery("Contact.findAll",Contact.class).getResultList();		
		return JPACustomer.convertListToSet(contacts);
	}

	@Override
	public void updateContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		try {
			entityManager.merge(contact);
		}catch(Exception ex) {
			
		}
		
		
	}

	@Override
	public void deleteContact(String contactEmail) throws Exception {
		// TODO Auto-generated method stub
		Contact contact = entityManager.find(Contact.class, contactEmail);
		if (contact != null) {
			entityManager.remove(contact);
		}
		
	}

	@Override
	public List<Contact> getContactsByCustomerID(int customerID) throws Exception {
		// TODO Auto-generated method stub
		List<Contact> contacts = entityManager.createNamedQuery("Contact.findByCustomerID",Contact.class).setParameter("customerID", customerID).getResultList();
		System.out.print(contacts.size());
		return contacts;
	}
	
	@Override
	public Contact getContactByEmail(String contactEmail) throws Exception {
		
		Contact contact = entityManager.find(Contact.class, contactEmail);
		return contact;
		
		
	}
	
	
}
