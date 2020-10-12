package controllers.customer;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import beans.CustomerBean;
import beans.UserBean;
import controllers.contact.CustomerContactsController;
import entity.Address;
import entity.Customer;
import entity.NormalUser;
import entity.User;

@Named(value = "addCustomerController")
@RequestScoped
public class AddCustomerController {
	
	@Inject
	private CustomerBean customerBean;
	private Customer customer = new Customer();
	
	@Inject
	private UserBean userBean;
	
	public AddCustomerController(){
		Address address = new Address();
		customer.setCustomerAddress(address);
	}
	
	public void addCustomer(Customer customer) {
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		NormalUser user = userBean.searchUserByAccount(username);
		boolean isAdmin;
		if (user == null) {
			isAdmin = true;
		
		} else {
			isAdmin = false;
		}
		
		try {
			Date date = new Date();
			if(!isAdmin) {
				customer.setNormalUser(user);				
//				LinkedHashSet<Customer> customers = user.getCustomers();
//				customers.add(customer);
			}			
			customer.setCustomerAddedDate(date);
			boolean result = customerBean.addCustomer(customer);
			
			if (result) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The Customer has been added succesfully"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed, database error"));
			}
		}catch(Exception ex) {
			Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed, unexpected exception"));
		}

	}

	public Customer getCustomer() {
		
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
