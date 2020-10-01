package repository;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Industry;

@Stateless
public class JPAIndustry implements IndustryRepository {
	
	@PersistenceContext(unitName = "CMS-ejb")
    private EntityManager entityManager;

	@Override
	public void addIndustry(Industry industry) throws Exception {
		// TODO Auto-generated method stub
		entityManager.persist(industry);
		
	}

	@Override
	public List<Industry> getAllIndustries() throws Exception {
		// TODO Auto-generated method stub
		List<Industry> industries = entityManager.createNamedQuery("Industry.findAll", Industry.class).getResultList();
		return industries;
	}

	@Override
	public void updateIndustry(Industry industry) throws Exception {
		// TODO Auto-generated method stub
		try {
			entityManager.merge(industry);
		} catch (Exception ex) {
			
		}
		
	}

	@Override
	public void deleteIndustry(int industryID) throws Exception {
		// TODO Auto-generated method stub
		Industry industry = entityManager.find(Industry.class, industryID);
		if (industry != null) {
			entityManager.remove(industry);
		}
		
	}

	@Override
	public Industry findIndustryByID(int industryID) throws Exception {
		// TODO Auto-generated method stub
		
		Industry industry = entityManager.find(Industry.class, industryID);
		return industry;
	}
	
	

}
