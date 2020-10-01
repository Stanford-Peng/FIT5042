package beans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entity.Industry;
import repository.IndustryRepository;

@Named
@SessionScoped
public class IndustryTypeBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	IndustryRepository industryRepository;
	
	public List<Industry> getAllIndustrys(){
		try {
		List<Industry> industrys = industryRepository.getAllIndustries();
		return industrys;
		}catch (Exception ex) {
			Logger.getLogger(IndustryTypeBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public boolean addIndustry(Industry Industry) {
		try {
			industryRepository.addIndustry(Industry);
			return true;
		}catch(Exception ex) {
			Logger.getLogger(IndustryTypeBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	
	public boolean removeIndustry(int industryID) {
		try {
			industryRepository.deleteIndustry(industryID);
			return true;
		} catch(Exception ex) {
			Logger.getLogger(IndustryTypeBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	
	public boolean editIndustry(Industry Industry) {
		try {
			industryRepository.updateIndustry(Industry);
			return true;
		}catch(Exception ex) {
			Logger.getLogger(IndustryTypeBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	
	public Industry findIndustryByID (int industryID) {
		try {
			
			return industryRepository.findIndustryByID(industryID);
		}catch(Exception ex) {
			Logger.getLogger(IndustryTypeBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;	
	}
	

}
