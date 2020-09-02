package helloworld;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import calculator.LoanBean;
import helloworld.client.WebServiceClient;

@Named(value = "webServiceBean")
@SessionScoped
public class WebServiceBean {

	private String name;
	
	private double principle;
	private double interestRate;
	private int numberOfYears;
	private double monthlyPayment = 0;
	
	@EJB
	private LoanBean loanBean;
	
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public double getPrinciple() {
		return principle;
	}
	public void setPrinciple(double principle) {
		this.principle = principle;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public int getNumberOfYears() {
		return numberOfYears;
	}
	public void setNumberOfYears(int numberOfYears) {
		this.numberOfYears = numberOfYears;
	}

	private WebServiceClient webServiceClient;
	public WebServiceBean() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWebServiceClient() {
		webServiceClient = new WebServiceClient();
		webServiceClient.setPostName2(getName());
	}
	
	public void setLoanService() {
		webServiceClient = new WebServiceClient();
		webServiceClient.setPostLoan(principle, numberOfYears, interestRate);
		//setMonthlyPayment(loanBean.calculate());
		setMonthlyPayment(loanBean.getMonthlyPayment());
		
	}

}
