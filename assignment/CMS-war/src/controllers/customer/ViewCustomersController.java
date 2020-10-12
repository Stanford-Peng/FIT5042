package controllers.customer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import beans.CustomerBean;
import beans.UserBean;
import entity.Customer;
import entity.Industry;

@Named(value = "viewCustomersController")
@RequestScoped
public class ViewCustomersController {

	@Inject
	CustomerBean customerBean;

	private String searchedString = "";

	private Industry searchIndustryType;
	private String searchScale = "";

	private List<Customer> customers = new ArrayList<Customer>();

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public ViewCustomersController() {
//		ELContext context = FacesContext.getCurrentInstance().getELContext();
//
//		customerBean= (CustomerBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context,
//				null, "customerBean");

	}

	@PostConstruct
	public void init() {
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		Logger.getLogger(ViewCustomersController.class.getName()).log(Level.SEVERE, "users:" + username);

		// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User
		// Account:"+ username));

		if (customerBean.getCustomersByAccount(username) != null) {
			this.customers = customerBean.getCustomersByAccount(username);
		}

	}

	public String getSearchedString() {
		return searchedString;
	}

	public void setSearchedString(String searchedString) {
		this.searchedString = searchedString;
	}

	public Industry getSearchIndustryType() {
		return searchIndustryType;
	}

	public void setSearchIndustryType(Industry searchIndustryType) {
		this.searchIndustryType = searchIndustryType;
	}

	public String getSearchScale() {
		return searchScale;
	}

	public void setSearchScale(String searchScale) {
		this.searchScale = searchScale;
	}

	public void searchCustomerByNameId() {
		customers.clear();
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		for (Customer c : customerBean.getCustomersByAccount(username)) {
			if (c.toString().contains(searchedString)) {
				customers.add(c);
			}
		}

	}

	public void viewAllCustomers() {
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		customers.clear();
		if (customerBean.getCustomersByAccount(username) != null) {
			this.customers = customerBean.getCustomersByAccount(username);
		}

	}

	public void searchByIndustryScale(Industry searchIndustry, String searchScale) {//request scope will instantiated twice when command is sent
		customers.clear();
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		List<Customer> allCustomers = customerBean.getCustomersByAccount(username);
		try {
		if(searchScale.equals("") && searchIndustryType == null) {
			customers.addAll(allCustomers);
			return;
		}
		else if ( !searchScale.equals("") && searchIndustryType != null) {
			for (Customer c : allCustomers) {
				if( searchIndustryType.equals(c.getCustomerIndustryType()) && c.getCustomerScale().equals(searchScale)) {
					customers.add(c);
				}
			}
		} else if( !searchScale.equals("") && searchIndustryType == null) {
			for (Customer c : allCustomers) {
				if(c.getCustomerScale().equals(searchScale)) {
					customers.add(c);
				}
			}
			
		} else {
			for (Customer c : allCustomers) {
				if(searchIndustryType.equals(c.getCustomerIndustryType())) {
					customers.add(c);
				}
			}
			
		}
		}catch(Exception ex) {
			throw ex;
			
		}
	}

}
