/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Stan
 */

@LocalBean
@Stateless
public class LoanBean implements Serializable {

    private double principle;
    private double interestRate;
    private int numberOfYears;
    private double monthlyPayment;


    
    public LoanBean() {
        this.principle = 0.0;
        this.interestRate = 0.0;
        this.numberOfYears = 0;
        this.monthlyPayment = 0.0;
    }

//    public LoanBean(double principle, double interestRate, int numberOfYears) {
//        this.principle = principle;
//        this.interestRate = interestRate;
//        this.numberOfYears = numberOfYears;
//    }

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

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
    
    public double calculate() {
        //double monthlyPayment = 0.0;
        int numberOfPayments = numberOfYears * 12;
        if (interestRate > 0) {
            monthlyPayment = principle * (interestRate * (Math.pow((1 + interestRate), numberOfPayments))) / ((Math.pow((1 + interestRate), numberOfPayments)) - 1);
        } else {
            monthlyPayment = 1000;
        }
        

        return monthlyPayment;
    }
}
