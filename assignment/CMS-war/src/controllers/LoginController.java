package controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

//@ManagedBean(name = "loginController")
@Named(value="loginController")
@SessionScoped
public class LoginController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pageTitle;

	public LoginController() {
		//super();
		//System.out.print("LoginController Created");
		Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,"LoginController Created");
		pageTitle = "Authentication ";
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;		
		
		
	}
	
	public void navigate(String pageTitle) throws IOException {
		this.setPageTitle(pageTitle);		
		String page = "";
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		if (pageTitle.contains("User")) {
			page = "normal/index.xhtml?faces-redirect=true";
		} else {
			page = "admin/index.xhtml?faces-redirect=true";
		}
		context.redirect(page);
		
	}
	
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
	
//	public void go(String sth) throws IOException {
//		
//		String page = "error.xhtml";
//		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//		context.redirect(page);
//		this.setPageTitle(sth);
//		//return page;
//	}
	
	

}

//<!-- 			<h:commandButton action="#{loginController.go('Somthing')}" value = "Access Test"
//>
//</h:commandButton> --> <!-- <h:commandLink action = "#{loginController.setPageTitle('Something')}" value = "Do"></h:commandLink> -->
