package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;

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
        FileReaderService fileReaderService = new FileReaderService();
        MortgageCalculatorService mortgageCalculatorService = new MortgageCalculatorService();
        // Read loan applications
        String filename = "src/main/resources/prospects.txt";
        List<LoanApplication> applications = fileReaderService.readLoanApplications(filename);
        ArrayList<String> stringList = stringLoanApplications(applications, mortgageCalculatorService);
        return stringList;
    }

    @PostMapping("/calculate")
    public LoanApplication calculateLoanApplication(@RequestBody LoanApplication application) {
        // Logic to calculate and return loan application details
        // Todo
        return application;
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

