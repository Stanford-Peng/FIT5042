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


@Named(value = "viewCustomersController")
@RequestScoped
public class ViewCustomersController {

	@Inject
	CustomerBean customerBean;

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

}
