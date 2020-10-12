package controllers;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import beans.UserBean;
import entity.NormalUser;

@Named(value = "editUserController")
@RequestScoped
public class EditUserController {
	
//	@Inject
//	private AdminApplication app;
	
	@Inject
	private UserBean userBean;
	
	private String account;
	private NormalUser editUser;

	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public EditUserController() {
		account = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("userAccount");
		
		
	}
	
	@PostConstruct
	public void init() {
		this.editUser = userBean.searchUserByAccount(account);
		
	}
	
	public NormalUser getEditUser() {
		
//		if(editUser == null) {
////			ELContext context
////            = FacesContext.getCurrentInstance().getELContext();
////			
////			AdminApplication app = (AdminApplication) FacesContext.getCurrentInstance()
////                    .getApplication()
////                    .getELResolver()
////                    .getValue(context, null, "adminApplication");
//			this.editUser =  app.getUserByAccount(account);
//			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User " + editUser.getPassword() + editUser.toString()));
//			return app.getUserByAccount(account);
//		}
		return editUser;	
	}


	public void setEditUser(NormalUser editUser) {
		this.editUser = editUser;
	}
	
	
	
	

	
}
