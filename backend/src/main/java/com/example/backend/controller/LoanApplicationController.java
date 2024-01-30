package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.FileReaderService;
import com.example.backend.service.MortgageCalculatorService;
import com.example.backend.service.LoanApplication;

@RestController
@RequestMapping("/api")
public class LoanApplicationController {


    @CrossOrigin(origins = "http://localhost:5173/")
    @GetMapping("/loan-applications")
    public ArrayList<String> getAllLoanApplicationsString() {
        // Logic to retrieve and return all loan applications
        MortgageCalculatorService mortgageCalculatorService = new MortgageCalculatorService();
        FileReaderService fileReaderService = new FileReaderService();
        // Read loan applications
        String filename = "prospects.txt";
        List<LoanApplication> applications = fileReaderService.readLoanApplications(filename);
        ArrayList<String> stringList = stringLoanApplications(applications, mortgageCalculatorService);
        return stringList;
    }

    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping("/calculate-mortgage")
    public ResponseEntity<String> calculateMortgage(@RequestBody LoanApplication loanApplication) {
        MortgageCalculatorService mortgageCalculatorService = new MortgageCalculatorService();
        try {
            double monthlyPayment = mortgageCalculatorService.calculateMonthlyPayment(
                loanApplication.getTotalLoan(), 
                loanApplication.getInterest(), 
                loanApplication.getYears()
            );
            
            // Format the monthly payment to a string with two decimal places
            String result = String.format("The monthly payment is: %.2f euros", monthlyPayment);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("There was an error processing your request");
        }
    }

    static ArrayList<String> stringLoanApplications(List<LoanApplication> applications, MortgageCalculatorService calculatorService) {
        int prospectNumber = 1;
        ArrayList<String> stringList = new ArrayList<>();
        for (LoanApplication application : applications) {
            double monthlyPayment = calculatorService.calculateMonthlyPayment(
                application.getTotalLoan(), application.getInterest(), application.getYears());
            
            stringList.add(application.toOutputString(prospectNumber, monthlyPayment));
            prospectNumber++;
        }
        return stringList;
    }

}

