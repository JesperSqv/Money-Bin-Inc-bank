package com.example.backend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class FileReaderServiceTest {

    private final FileReaderService fileReaderService = new FileReaderService();

    @Test
    public void testReadLoanApplications() {
        
        String filename = "src/main/resources/prospects.txt";
        List<LoanApplication> applications = fileReaderService.readLoanApplications(filename);

        // Check the number of applications read
        Assertions.assertEquals(4, applications.size(), "Should read 4 loan applications");

        // Check that it reads the first applicant is correct
        LoanApplication firstApplication = applications.get(0);
        Assertions.assertEquals("Juha", firstApplication.customer, "Customer name should match");
        Assertions.assertEquals(1000, firstApplication.totalLoan, "Total loan should match");
        // Test the interest is now in the correct format.
        Assertions.assertEquals(0.05, firstApplication.interest, "Interest should match");
        Assertions.assertEquals(0.05, firstApplication.interest, "Interest should match");
        Assertions.assertEquals(2, firstApplication.years, "Years should match");
        
    }
}
