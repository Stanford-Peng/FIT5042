package restServices;

import java.text.DecimalFormat;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import beans.UserBean;
import repository.ContactRepository;
import repository.UserRepository;

@Path("checkname")
public class NameCheckREST {
	
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
//    @Inject
//    private UserBean userBean;
    
    @EJB
    private UserRepository userRepository;
    @EJB
    private ContactRepository contactRepository;

    /**
     * Default constructor. 
     */
    public NameCheckREST() {
        // TODO Auto-generated constructor stub
    }

//    /**
//     * Retrieves representation of an instance of NameCheckREST
//     * @return an instance of String
//     */
//    @GET
//    @Produces("application/json")
//    public String getJson() {
//        // TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }
    
    /**
     * Retrieves representation of an instance of HelloWorld
     * @return an instance of String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        // TODO return proper representation object
    	//DecimalFormat df = new DecimalFormat("0.00");
    	return "<html><body><h1>" + "Web resources for CMS"  + "</h1> </body></html>";
        //throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of NameCheckREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    @POST
    @Path("user")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject checkUsername(@FormParam("account") String account) throws Exception {
    	
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("account", account);
        
    	if(userRepository.searchUser(account) == null) {   		
            objectBuilder.add("exist", false);               		   		
    	}else {
    		objectBuilder.add("exist", true);    		
    	}   	

        return objectBuilder.build();
    }
    
    @POST
    @Path("contact")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject checkContactEmail(@FormParam("email") String email) throws Exception{
    	
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("email", email);
        
    	if(contactRepository.getContactByEmail(email) == null) {   		
            objectBuilder.add("exist", false);               		   		
    	}else {
    		objectBuilder.add("exist", true);    		
    	}   	

        return objectBuilder.build();
    	
    }
    

}