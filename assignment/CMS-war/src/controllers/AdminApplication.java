package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import beans.UserBean;
import controllers.customer.AddCustomerController;
//import entity.User;
import entity.NormalUser;

import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 


@Named(value = "adminApplication")
@SessionScoped
public class AdminApplication implements Serializable {
	
	@Inject
	UserBean userBean;
	
	private List<NormalUser> users = new ArrayList<NormalUser>();
	
	private String searchedAccount = "";
	
	
	public AdminApplication() {
		Logger.getLogger(AdminApplication.class.getName()).log(Level.SEVERE, "AdminApplication Created");
		
//		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//		userBean = (UserBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "userBean");
		
	}
	
	@PostConstruct
	public void init() {
		updateUserList();
	}
	
	public void updateUserList() {
		users.clear();
		for (NormalUser user : userBean.getAllUsers()) {
			users.add(user);
		}			
		
	}
	
	public void searchUserByAccount(String account) {
		users.clear();
		NormalUser searched = userBean.searchUserByAccount(account);
		if (searched != null) {
			users.add(searched);
		}
		
	}
	
	public NormalUser getUserByAccount(String account) {
		//double check 
		NormalUser searched = userBean.searchUserByAccount(account);
		return searched;
	}

	public List<NormalUser> getUsers() {
		
		return users;
	}

	public void setUsers(List<NormalUser> users) {
		this.users = users;
	}
	
	
	
	public String getSearchedAccount() {
		return searchedAccount;
	}

	public void setSearchedAccount(String searchedAccount) {
		this.searchedAccount = searchedAccount;
	}

	public String addUser(User localUser) {
		entity.NormalUser entityUser = new NormalUser();
		entityUser.setAccount(localUser.getAccount());
		try {
		entityUser.setPassword(toHexString(getSHA(localUser.getPassword())));
		} catch (NoSuchAlgorithmException e) {  
            System.out.println("Exception thrown for incorrect algorithm: " + e);  
            return "addUser.xhtml";
        }  
		
		boolean result = userBean.addUser(entityUser);
		
		if (result == true) {
			updateUserList();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A new user has been added succesfully"));
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed"));
		}	
		return "addUser.xhtml";
	}
	
	public String deleteUser(String account) {
		
		
		
		String param  = String.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("userAccount"));
		boolean result = false;
		if (param == account) {
		result = userBean.removeUser(account);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed and please refresh the page before deleting"));
			Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, "Failed and please refresh the page before deleting");
		}
		
		if (result == true)	{
			updateUserList();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User " + account +" has been deleted succesfully"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed, it may not exit and please click view all to refresh page"));
		}	
				
		return "allUsers.xhtml";
	}
	
	public void updateUser(NormalUser user) {
		try {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User " + toHexString(getSHA(user.getPassword())) +" has been changed password succesfully "));
		    user.setPassword(toHexString(getSHA(user.getPassword())));
		} catch (NoSuchAlgorithmException e) {  
            System.out.println("Exception thrown for incorrect algorithm: " + e);  
        } 
		
		boolean result  = userBean.editUser(user);
		
		if (result == true)	{
			updateUserList();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User " + user.getAccount() +" has been changed password succesfully "));
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed"));
		}		
		
	}
	
	//reference:https://www.geeksforgeeks.org/sha-256-hash-in-java/;https://www.baeldung.com/sha-256-hashing-java
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) 
    { 
    	StringBuffer hexString = new StringBuffer();
    	for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        } 
  
        return hexString.toString();  
    } 
	
	
	
	
	

}
