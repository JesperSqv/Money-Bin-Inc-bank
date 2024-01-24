package com.example.backend.service;


public class LoanApplication {
    String customer;
    double totalLoan;
    double interest;
    int years;

    public double interestToPrecentage(double interest) {
        double interestInPrecentage = interest / 100;
        return interestInPrecentage;
    }

    public LoanApplication(String customer, double totalLoan, double interest, int years) {
        this.customer = customer;
        this.totalLoan = totalLoan;
        // Interest can be inserted in digit and then converted here to 
        this.interest = interestToPrecentage(interest);
        this.years = years;
    }

}
