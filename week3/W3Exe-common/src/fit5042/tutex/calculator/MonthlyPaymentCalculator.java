package fit5042.tutex.calculator;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * A simple monthly payment calculator implementation
 *
 * @author Stan
 */

@Remote

public interface MonthlyPaymentCalculator {

    double calculate(double principle, int year, double interestRate);
        
}
