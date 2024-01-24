package com.example.backend.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService {

    public List<LoanApplication> readLoanApplications(String filename) {
        List<LoanApplication> applications = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = parseLine(line);
                if (values.length >= 4) {
                    LoanApplication application = new LoanApplication(values[0], 
                        Double.parseDouble(values[1]), 
                        Double.parseDouble(values[2]), 
                        Integer.parseInt(values[3]));
                    applications.add(application);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Or handle more gracefully
        }
        return applications;
    }

    private String[] parseLine(String line) {
        List<String> fields = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();
    
        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes; // Toggle the inQuotes flag
            } else if (c == ',' && !inQuotes) {
                fields.add(field.toString());
                field = new StringBuilder(); // Start a new field
            } else {
                field.append(c);
            }
        }
        fields.add(field.toString()); // Add the last field
    
        return fields.toArray(new String[0]);
    }
    
}

