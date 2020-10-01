package controllers.industry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import beans.CustomerBean;
import beans.IndustryTypeBean;
import entity.Industry;

@Named(value="allIndustryController")
@RequestScoped
public class AllIndustryController {
	
	@Inject
	IndustryTypeBean industryTypeBean;
	
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
	
	public String deleteIndustry(int industryID) {
		
		try {
			
			boolean result = industryTypeBean.removeIndustry(industryID);
			if (result) {
				init();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Industry " + industryID +" has been deleted succesfully"));
			}
		}catch(Exception ex){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete Industry Failed"));
		}
		return "allIndustries.xhtml?faces-redirect=true";
		
	}
	
	public void updateIndustry(int industryID, String industryName) {
		Industry industry = industryTypeBean.findIndustryByID(industryID);
		industry.setIndustryName(industryName);
		industryTypeBean.editIndustry(industry);
		
	}
	
	public String addIndustry() {
		Industry industry = new Industry();
		industryTypeBean.addIndustry(industry);
		return "allIndustries.xhtml?faces-redirect=true";
		
	}

}
