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

    // Implementing a function to make years to months
    public int yearsToMonths(int years) {
        int months = years * monthsPerYear;
        return months;
    }

    // Function for converting to a monthly interest rate
    public double yearlyInterestToMonthlyInterest(double yearlyInterest) {
        // Assuming that to go from yearly to monthly we divide by 12 months
        double monthlyInterest = yearlyInterest / monthsPerYear;
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
