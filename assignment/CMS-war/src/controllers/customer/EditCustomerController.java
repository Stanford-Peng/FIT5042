package controllers.customer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import beans.CustomerBean;
import beans.UserBean;
import entity.Customer;
import entity.NormalUser;



@Named
@RequestScoped
public class EditCustomerController {
	
	@Inject
	private CustomerBean customerBean;
	
	@Inject
	private UserBean userBean;
	
	private int customerID;
	private Customer customer;
	private String account;
	
	public EditCustomerController() {
		this.customerID = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerID"));
	}
	
	@PostConstruct
	public void init() {
		this.customer = customerBean.searchCustomerByID(customerID);
		if(customer.getNormalUser() != null)
		{
			this.account = customer.getNormalUser().getAccount();
		}
		
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void assignUser() {
		//remove user 's reference to customer if there is one
		if(customer.getNormalUser() != null)
		{
			customer.getNormalUser().getCustomers().remove(customer);
		}
		//get the new user
		NormalUser user = userBean.searchUserByAccount(account);
		
		//set new user 's reference to this customer
		if (user != null) {
			user.getCustomers().add(customer);
		}
		//set customer 's reference to new user
		customer.setNormalUser(user);		
		boolean result = customerBean.editCustomer(customer);
		
		if (result) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Assign User Successfully"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Assigning failed"));
		}

		
	}
	
	public void editCustomer() {
		
		boolean result = customerBean.editCustomer(customer);		
		if (result) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edit Successfully"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edit failed"));
		}
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	
	
	
	

}
