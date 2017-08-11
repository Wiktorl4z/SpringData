package com.wiktor.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeGenerator {
    private static final String FIRST_NAMES_FILE_PATH = "firstNames";
    private static final double SALARY_BASE = 1_000.0;
    private static final double SALARY_SPREAD = 10_000.0;
    private static final int COMPANY_AGE_IN_DAYS = 900;
    private List<String> firstNames;
    private List<String> lastNames;
    private final Random random = new Random();

    Employee generate() {
        Employee employee = new Employee();
        employee.setFirstName(getRandomFirstName());
        employee.setLastName(getRandomLastName());
        employee.setSalary(getRandomSalary());
        employee.setEmployeeDate(getRandomEmploymentDate());

        return employee;
    }

    private String getRandomFirstName() {
        return getRandom(getFirstNames());
    }

    private String getRandomLastName() {
        return getRandom(getSecondNames());
    }

    private String getRandom(List<String> elements) {
        return elements.get(
                random.nextInt(elements.size()));
    }

    private List<String> getFirstNames() {
        if (firstNames == null) {
            firstNames = loadLines(FIRST_NAMES_FILE_PATH);
        }
        return firstNames;
    }

    private List<String> getSecondNames() {
        if (lastNames == null) {
            lastNames = loadLines("lastNames");
        }
        return lastNames;
    }

    private List<String> loadLines(String filePatch) {
        try {
            return Files.readAllLines(Paths.get(new ClassPathResource(filePatch).getURI()));
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Error while reading lines from file %s", filePatch), e);
        }
    }

    private BigDecimal getRandomSalary() {
        return new BigDecimal(SALARY_BASE + Math.random() * SALARY_SPREAD);
    }

    private LocalDate getRandomEmploymentDate() {
        return LocalDate
                .now()
                .minus(
                        getRandomNumberOfDays());
    }

    private Period getRandomNumberOfDays() {
        return Period.ofDays(
                random.nextInt(
                        COMPANY_AGE_IN_DAYS));
    }
}

