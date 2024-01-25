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

    public String getCustomer() {
        return this.customer;
    }

    public double getTotalLoan() {
        return this.totalLoan;
    }

    public double getInterest() {
        return this.interest;
    }

    public int getYears() {
        return this.years;
    }

    public String toOutputString(int prospectNumber, double monthlyPayment) {
        return String.format("Prospect %d: %s wants to borrow %.2f € for a period of %d years and pay %.2f € each month",
                             prospectNumber, customer, totalLoan, years, monthlyPayment);
    }

}
