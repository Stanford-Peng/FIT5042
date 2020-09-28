package repository;

import java.util.Set;

import javax.ejb.Remote;

import entity.Customer;

@Remote
public interface CustomerRepository {
	
	public void addCustomer(Customer customer) throws Exception;
	
	public Set<Customer> getAllCustomers() throws Exception;
	
	public void updateCustomer(Customer customer) throws Exception;
	
	public void deleteCustomer(int customerID) throws Exception;

	
}
