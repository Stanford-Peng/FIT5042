package controllers.customer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import beans.CustomerBean;

@Named(value = "deleteCustomerController")
@RequestScoped
public class DeleteCustomerController {
	

	@Inject
	CustomerBean customerBean;
	
	@Inject
	ViewCustomersController viewCustomersController;
	
	
	public DeleteCustomerController(){
		
		
	}
	
	public void deleteCustomer(int customerID) {
		
		
		try {
			int param  = Integer.valueOf(FacesContext.getCurrentInstance()
	                .getExternalContext()
	                .getRequestParameterMap()
	                .get("customerID"));
			boolean result = false;
			if (param == customerID) {
			 result = customerBean.removeCustomer(customerID);
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed and please refresh the page before deleting"));
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed and please refresh the page before deleting"));
				Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, "Failed and please refresh the page before deleting");
			}
			if (result) {
				viewCustomersController.init();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The Customer has been deleted succesfully"));
				//return "allCustomers.xhtml?faces-redirect=true";
			
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed"));
			}
		}catch(Exception ex) {
			
		}
		//return "allCustomers.xhtml?faces-redirect=true";
	}
}
