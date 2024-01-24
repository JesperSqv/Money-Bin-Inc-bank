package com.example.backend.service;

public class LoanApplication {
    String customer;
    double totalLoan;
    double interest;
    int years;

    public LoanApplication(String customer, double totalLoan, double interest, int years) {
        this.customer = customer;
        this.totalLoan = totalLoan;
        this.interest = interest;
        this.years = years;
    }

}
