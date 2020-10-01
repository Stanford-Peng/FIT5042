package repository;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entity.Industry;

@Remote
public interface IndustryRepository {

	public void addIndustry(Industry industry) throws Exception;
	public List<Industry> getAllIndustries() throws Exception;
	public void updateIndustry(Industry industry) throws Exception;
	public void deleteIndustry(int industryID) throws Exception;
	public Industry findIndustryByID(int industryID) throws Exception;
}
