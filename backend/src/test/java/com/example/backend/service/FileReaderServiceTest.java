package com.example.backend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class FileReaderServiceTest {

    private final FileReaderService fileReaderService = new FileReaderService();

    @Test
    public void testReadLoanApplications() {
        
        String filename = "prospects.txt";
        List<LoanApplication> applications = fileReaderService.readLoanApplications(filename);

        // Check the number of applications read
        Assertions.assertEquals(4, applications.size(), "Should read 4 loan applications");

        // Check that it reads the first applicant is correct
        LoanApplication firstApplication = applications.get(0);
        Assertions.assertEquals("Juha", firstApplication.getCustomer(), "Customer name should match");
        Assertions.assertEquals(1000, firstApplication.getTotalLoan(), "Total loan should match");
        // Test the interest is now in the correct format. 
        Assertions.assertEquals(0.05, firstApplication.getInterest(), "Interest should match");
        // Here we check that it does not get smaller
        Assertions.assertEquals(0.05, firstApplication.getInterest(), "Interest should match");
        Assertions.assertEquals(2, firstApplication.getYears(), "Years should match");
        
    }

    @Test
    public void testReadLoanApplicationsFurther() {
        
        String filename = "prospects.txt";
        List<LoanApplication> applications = fileReaderService.readLoanApplications(filename);

        // Check the number of applications read
        Assertions.assertEquals(4, applications.size(), "Should read 4 loan applications");

        // Check that it reads the second applicant is correct
        LoanApplication secondApplication = applications.get(1);
        Assertions.assertEquals("Karvinen", secondApplication.getCustomer(), "Customer name should match");
        Assertions.assertEquals(4356, secondApplication.getTotalLoan(), "Total loan should match");
        // Test the interest is now in the correct format. 
        Assertions.assertEquals(0.0127, secondApplication.getInterest(), "Interest should match");
        Assertions.assertEquals(6, secondApplication.getYears(), "Years should match");

         // Check that it reads the fourth applicant is correct
         LoanApplication fourthApplication = applications.get(3);
         Assertions.assertEquals("Clarencé,Andersson", fourthApplication.getCustomer(), "Customer name should match");
        Assertions.assertEquals(2000, fourthApplication.getTotalLoan(), "Total loan should match");
        // Test the interest is now in the correct format. 
        Assertions.assertEquals(0.06, fourthApplication.getInterest(), "Interest should match");
        Assertions.assertEquals(4, fourthApplication.getYears(), "Years should match");
        
    }
}
