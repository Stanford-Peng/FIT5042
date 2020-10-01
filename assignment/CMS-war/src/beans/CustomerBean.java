package beans;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import entity.Customer;
import repository.CustomerRepository;

@Named
@ApplicationScoped
public class CustomerBean {
	
	@EJB
	CustomerRepository customerRepository;
	
	public Set<Customer> getAllCustomers(){
		try {
			Set<Customer> customers = customerRepository.getAllCustomers();
			Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, "customers:" + customers.size());
			return customers;
		} catch (Exception ex) {
			Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public boolean addCustomer(Customer customer) {
		try {
			customerRepository.addCustomer(customer);
			return true;
		}catch(Exception ex) {
			Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}
	
	public boolean removeCustomer(int customerID) {
		try {
			customerRepository.deleteCustomer(customerID);
			return true;
		} catch(Exception ex) {
			Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	
	public boolean editCustomer(Customer customer) {
		try {
			customerRepository.updateCustomer(customer);
			return true;
		}catch(Exception ex) {
			Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	
	public Customer searchCustomerByID(int customerID) {
		try {
			Customer customer = customerRepository.searchCustomer(customerID);
			return customer;			
		} catch(Exception ex) {
			Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public List<Customer> getCustomersByAccount(String account){
		
		try {
			List<Customer> customers = customerRepository.getCustomersByRole(account);
			Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, "customersByRole:" + customers.size());
			return customers;
		} catch (Exception ex) {
			Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;		
	}
	
	
	

}
