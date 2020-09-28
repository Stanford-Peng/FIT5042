package repository;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.NormalUser;
import entity.User;

@Stateless
public class JPAUser implements UserRepository{
	
	@PersistenceContext(unitName = "CMS-ejb")
    private EntityManager entityManager;

	@Override
	public void addUser(NormalUser user) throws Exception {
		// TODO Auto-generated method stub
		entityManager.persist(user);
		
	}

	@Override
	public Set<NormalUser> getAllUsers() throws Exception {
		// TODO Auto-generated method stub
		List<NormalUser> users = entityManager.createNamedQuery("NormalUser.findAll", NormalUser.class).getResultList();
		return JPACustomer.convertListToSet(users);
	}

	@Override
	public void updateUser(NormalUser user) throws Exception {
		// TODO Auto-generated method stub
		try {
			entityManager.merge(user);
		} catch (Exception ex) {
			
		}
		
	}

	@Override
	public void deleteUser(String account) throws Exception {
		// TODO Auto-generated method stub
		NormalUser user = entityManager.find(NormalUser.class, account);
		entityManager.remove(user);		
		
	}

	@Override
	public NormalUser searchUser(String account) throws Exception {
		// TODO Auto-generated method stub
		NormalUser user = entityManager.find(NormalUser.class, account);
		return user;

	}

	
}
