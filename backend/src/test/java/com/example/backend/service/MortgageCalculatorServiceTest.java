package com.example.backend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MortgageCalculatorServiceTest {

    private final MortgageCalculatorService service = new MortgageCalculatorService();

    @Test
    public void testPower() {
        Assertions.assertEquals(8, service.power(2, 3), "2 to the power of 3 should be 8");
    }

    @Test
    public void testYearsToMonths() {
        Assertions.assertEquals(12, service.yearsToMonths(1), "1 year to months should be 12");
    }

    @Test
    public void testYearlyInterestToMonthlyInterest() {
        Assertions.assertEquals(0.004868, service.yearlyInterestToMonthlyInterest(0.06), 0.0001, "Yearly interest of 6% should be 0.5% monthly");
    }

    @Test
    public void testCalculateMonthlyPayment() {
        // Example parameters: totalLoan = 100000, yearlyInterest = 5%, years = 30
        double monthlyPayment = service.calculateMonthlyPayment(100000, 0.05, 30);
        Assertions.assertEquals(530.06, monthlyPayment, 0.01, "Monthly payment calculation should be correct");
    }
}
