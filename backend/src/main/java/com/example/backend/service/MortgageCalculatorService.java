package com.example.backend.service;

public class MortgageCalculatorService {

    int monthsPerYear = 12;

    // Implementing exponentiation
    public double power(double base, int exponent) {
        double result = 1;
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }
        return result;
    }

    // Approximates the nth root of a value using the Newton-Raphson method
    private static double nthRoot(double value, int root) {
        double approx = value / root; // Initial guess
        double lastApprox;
        do {
            lastApprox = approx;
            approx = ((root - 1) * approx + value / Math.pow(approx, root - 1)) / root;
        } while (Math.abs(approx - lastApprox) > 0.0001); // or another threshold value
        return approx;
    }

    // Implementing a function to make years to months
    public int yearsToMonths(int years) {
        int months = years * monthsPerYear;
        return months;
    }

    // Function for converting to a monthly interest rate
    public double yearlyInterestToMonthlyInterest(double yearlyInterest) {
        double monthlyInterest = nthRoot((1 + yearlyInterest), monthsPerYear) - 1;
        return monthlyInterest;
    }

    // Calculate the monthly payment needed
    public double calculateMonthlyPayment(double totalLoan, double yearlyInterest, int years) {
        double monthlyInterest = yearlyInterestToMonthlyInterest(yearlyInterest);
        int amountOfPayments = yearsToMonths(years);
        // Doing the calculation
        double monthlyPayment = totalLoan*( monthlyInterest * power(1 + monthlyInterest, amountOfPayments)) / (power(1 + monthlyInterest, amountOfPayments) - 1);
        return monthlyPayment;
    }

}
