package repository;

import java.util.Set;

import javax.ejb.Remote;

import entity.Industry;

@Remote
public interface IndustryRepository {

	public void addIndustry(Industry industry) throws Exception;
	public Set<Industry> getAllIndustries() throws Exception;
	public void updateIndustry(Industry industry) throws Exception;
	public void deleteIndustry(int industryID) throws Exception;
	
}
