package beans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import java.util.logging.Level;
import java.util.logging.Logger;

import entity.NormalUser;
//import entity.User;
import repository.UserRepository;


@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserRepository userRepository;
	
	//default constructor
	public UserBean() {
	}
	
	public List<NormalUser> getAllUsers(){
		try {
		
		List<NormalUser> users = userRepository.getAllUsers();
		//System.out.print("users:" + users.size());
		Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, "users:" + users.size());
		return users;
		} catch(Exception ex) {
			Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public boolean addUser(NormalUser user) {
		try {
			userRepository.addUser(user);
			return true;
		}catch (Exception ex) {
			Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}
	
	public boolean removeUser(String account) {
		try {
			userRepository.deleteUser(account);
			return true;
			
		}catch (Exception ex) {
			Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	
	public boolean editUser(NormalUser user) {
		try {
			userRepository.updateUser(user);
			return true;
			
		}catch (Exception ex) {
			Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}	
	
	public NormalUser searchUserByAccount(String account) {
		try {
			NormalUser user = userRepository.searchUser(account);
			return user;
		}catch (Exception ex) {
			Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return null;
	}

}
