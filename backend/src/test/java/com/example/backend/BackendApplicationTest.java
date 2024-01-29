package com.example.backend;

import com.example.backend.service.FileReaderService;
import com.example.backend.service.MortgageCalculatorService;
import com.example.backend.service.LoanApplication;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackendApplicationTest {

    @Test
    public void testLoanApplicationOutputs() {
        FileReaderService fileReaderService = new FileReaderService();
        MortgageCalculatorService mortgageCalculatorService = new MortgageCalculatorService();

        // Read test loan applications
        String filename = "prospects.txt";; // Use a test-specific file
        List<LoanApplication> applications = fileReaderService.readLoanApplications(filename);

        int prospectNumber = 1;
        for (LoanApplication application : applications) {
            double monthlyPayment = mortgageCalculatorService.calculateMonthlyPayment(
                application.getTotalLoan(), application.getInterest(), application.getYears());

            String output = application.toOutputString(prospectNumber, monthlyPayment);

            // Construct the expected output string
            String expectedOutput = String.format("Prospect %d: %s wants to borrow %.2f € for a period of %d years and pay %.2f € each month",
                    prospectNumber, application.getCustomer(), application.getTotalLoan(), application.getYears(), monthlyPayment);

            assertEquals(expectedOutput, output, "The output string should match the expected format and values");
            prospectNumber++;
        }
    }
}

