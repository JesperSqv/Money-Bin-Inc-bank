package com.example.backend;

import com.example.backend.service.FileReaderService;
import com.example.backend.service.MortgageCalculatorService;
import com.example.backend.service.LoanApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter customer name:");
        String customerName = scanner.nextLine();
        
        System.out.println("Enter total loan amount:");
        double totalLoan = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter yearly interest rate:");
        double interest = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter loan period in years:");
        int years = scanner.nextInt();
        scanner.nextLine();

        LoanApplication newApplication = new LoanApplication(customerName, totalLoan, interest, years);
        MortgageCalculatorService calculatorService = new MortgageCalculatorService();
        double monthlyPayment = calculatorService.calculateMonthlyPayment(totalLoan, newApplication.getInterest(), years);

        int nextProspectNumber = getNextProspectNumber();
        System.out.println(newApplication.toOutputString(nextProspectNumber, monthlyPayment));
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

    private static int getNextProspectNumber() {
        // Implement logic to determine the next prospect number
        return 4;
    }

}
