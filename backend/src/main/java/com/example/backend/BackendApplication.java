package com.example.backend;

import com.example.backend.service.FileReaderService;
import com.example.backend.service.MortgageCalculatorService;
import com.example.backend.service.LoanApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);

        // Create service instances
        FileReaderService fileReaderService = new FileReaderService();
        MortgageCalculatorService mortgageCalculatorService = new MortgageCalculatorService();

        // Read loan applications
        String filename = "src/main/resources/prospects.txt";
        List<LoanApplication> applications = fileReaderService.readLoanApplications(filename);

        // Calculate and print loan details
        printLoanApplications(applications, mortgageCalculatorService);
    }

    static void printLoanApplications(List<LoanApplication> applications, MortgageCalculatorService calculatorService) {
        int prospectNumber = 1;
        for (LoanApplication application : applications) {
            double monthlyPayment = calculatorService.calculateMonthlyPayment(
                application.getTotalLoan(), application.getInterest(), application.getYears());
            
            System.out.println(application.toOutputString(prospectNumber, monthlyPayment));
            prospectNumber++;
        }
    }
}
