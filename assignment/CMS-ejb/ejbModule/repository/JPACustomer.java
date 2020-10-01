package repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entity.Customer;
import entity.User;

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

	@Override
	public Customer searchCustomer(int customerID) throws Exception {
		// TODO Auto-generated method stub
		Customer c = entityManager.find(Customer.class, customerID);
		
		
		return c;
	}

	@Override
	public List<Customer> getCustomersByRole(String role) throws Exception {
		// TODO Auto-generated method stub
		boolean isAdmin;
		List<Customer> customers;
		//entityManager.flush();
		
		List<User> users = entityManager.createNamedQuery("User.findByAccount", User.class).setParameter("account", role).getResultList();
		if (users.get(0).getUserType().equals("Admin")) {
			isAdmin = true;
		
		} else {
			isAdmin = false;
		}
		
		if (isAdmin) {
			customers = entityManager.createNamedQuery("Customer.findAll", Customer.class).getResultList();
			
		} else {
			//customers = entityManager.createNamedQuery("Customers.findByAccount", Customer.class).setParameter("account", role).getResultList();
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    	CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
	    	Root<Customer> c = criteriaQuery.from(Customer.class);
	    	criteriaQuery.select(c).where(builder.equal(c.get("normalUser").get("account"), role)); //(p.get("price"), budget));//danger4
	    	Query query = entityManager.createQuery(criteriaQuery);    	
	    	//List<Property> properties = query.getResultList();
	        customers =  query.getResultList();
			
		}
		
		return customers;
	} 

}
