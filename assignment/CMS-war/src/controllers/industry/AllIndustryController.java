package controllers.industry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import beans.CustomerBean;
import beans.IndustryTypeBean;
import controllers.customer.AddCustomerController;
import entity.Customer;
import entity.Industry;

@Named(value="allIndustryController")
@RequestScoped
public class AllIndustryController {
	
	@Inject
	private IndustryTypeBean industryTypeBean;
	
	@Inject
	private CustomerBean customerBean;
	
	private List<Industry> industries;
	
	public AllIndustryController() {
		
//		ELContext context = FacesContext.getCurrentInstance().getELContext();
//		industryTypeBean= (IndustryTypeBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context,
//				null, "industryTypeBean");
		
		
	}
	
	@PostConstruct
	public void init() {
		industries = industryTypeBean.getAllIndustrys();
	}


	public List<Industry> getIndustries() {
		
		return industries;
	}

	public void setIndustries(List<Industry> industries) {
		this.industries = industries;
	}
	
	public void deleteIndustry(int industryID) {

		try {
			int param  = Integer.valueOf(FacesContext.getCurrentInstance()
	                .getExternalContext()
	                .getRequestParameterMap()
	                .get("industryID"));
			boolean result = false;
			boolean result0 = false;
			if (param == industryID) {
				Industry industry = industryTypeBean.findIndustryByID(industryID);
				for(Customer c: industry.getCustomers()) {
					c.setCustomerIndustryType(null);
					customerBean.editCustomer(c);
					
				}
				industry.setCustomers(null);
				result0 = industryTypeBean.editIndustry(industry);				
				result = industryTypeBean.removeIndustry(industryID);
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed and please refresh the page before deleting"));
					Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, "Failed and please refresh the page before deleting");
				}
//			
			if (result && result0) {
				init();//update list
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Industry " + industryID +" has been deleted succesfully"));
			}
		}catch(Exception ex){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete Industry Failed"));
		}
//		return "allIndustries.xhtml?faces-redirect=true";
		
	}
	//,int industryID, String industryName
	public void updateIndustry(RowEditEvent<Industry> event) {
		Industry industry = industryTypeBean.findIndustryByID(event.getObject().getIndustryID());
		industry.setIndustryName(event.getObject().getIndustryName());
		industryTypeBean.editIndustry(industry);
        FacesMessage msg = new FacesMessage("Industry Editted", event.getObject().getIndustryName());	       
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String addIndustry() {
		Industry industry = new Industry();
		industryTypeBean.addIndustry(industry);
		return "allIndustries.xhtml?faces-redirect=true";
		
	}
	
	 public void onRowCancel(RowEditEvent<Industry> event) {
	        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().getIndustryName());
	       
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }

}
