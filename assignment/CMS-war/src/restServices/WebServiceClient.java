package restServices;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import java.io.Serializable;

@Named
@SessionScoped
public class WebServiceClient implements Serializable {
	//not used, can be deleted because the ajax is sent directly from jsf
	
	private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://0.0.0.0:8080/cms-war/webresources";
    
    public WebServiceClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("checkname");
    }
    
    public void postUsername(String account) throws ClientErrorException {
    	Form form = new Form();
        form.param("account", account);
        webTarget.request(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
    }

}
