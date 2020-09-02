package helloworld;

import java.text.DecimalFormat;

import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import calculator.LoanBean;

@Path("greeting")
public class HelloWorld {
	
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    @EJB
    private NameStorageBean nameStorage;
    
    @EJB
    private LoanBean loanBean;
    /**
     * Default constructor. 
     */
    public HelloWorld() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of HelloWorld
     * @return an instance of String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        // TODO return proper representation object
    	DecimalFormat df = new DecimalFormat("0.00");
    	return "<html><body><h1>Hello " + nameStorage.getName() + ", the monthly payment is " + df.format(loanBean.calculate()) +"!</h1> </body></html>";
        //throw new UnsupportedOperationException();
    }
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void setPostName( @FormParam("name") String content) {
    	nameStorage.setName(content);
    }
    
    @POST
    @Path("loan")
    @Consumes("application/json")
    public void setPostLoan(Loan loan) {
    	loanBean.setPrinciple(loan.getPrinciple());
    	loanBean.setInterestRate(loan.getInterestRate());
    	loanBean.setNumberOfYears(loan.getNumberOfYears());
    	loanBean.setMonthlyPayment(loanBean.calculate());
//    	ELContext context
//        = FacesContext.getCurrentInstance().getELContext();
//    	WebServiceBean currentWebBean = (WebServiceBean) FacesContext.getCurrentInstance().getApplication()
//                .getELResolver().getValue(context, null, "webServiceBean");
//    	currentWebBean.setMonthlyPayment(loanBean.calculate());
    	
    }
    /**
     * PUT method for updating or creating an instance of HelloWorld
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }

}