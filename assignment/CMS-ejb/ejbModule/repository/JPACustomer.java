package repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Customer;

@Stateless
public class JPACustomer implements CustomerRepository {
	
	@PersistenceContext(unitName = "CMS-ejb")
    private EntityManager entityManager;

	@Override
	public void addCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		
		entityManager.persist(customer);
		
		
	}

	@Override
	public Set<Customer> getAllCustomers() throws Exception {
		// TODO Auto-generated method stub
		List<Customer> customers = entityManager.createNamedQuery("Customer.findAll", Customer.class).getResultList();		
		return convertListToSet(customers);
	}

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
        try {
            entityManager.merge(customer);
        } catch (Exception ex) {

        }
		
	}

	@Override
	public void deleteCustomer(int customerID) throws Exception {
		// TODO Auto-generated method stub
		Customer c = entityManager.find(Customer.class, customerID);
		if (c != null) {
			entityManager.remove(c);
		}
		
	}
	
    // Generic function to convert list to set 
	//reference : https://www.geeksforgeeks.org/convert-list-to-set-in-java/
    public static <T> Set<T> convertListToSet(List<T> list) 
    { 
        // create an empty set 
        Set<T> set = new HashSet<>(); 
  
        // Add each element of list into the set 
        for (T t : list) 
            set.add(t); 
  
        // return the set 
        return set; 
    } 

}
