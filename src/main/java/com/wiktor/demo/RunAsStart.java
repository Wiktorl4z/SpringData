package com.wiktor.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

@Component
public class RunAsStart {
    private final EmployeeRepository employeeRepository;
    private final EmployeeGenerator employeeGenerator;
    private final Logger logger = Logger.getLogger(RunAsStart.class);

    @Autowired
    public RunAsStart(EmployeeRepository employeeRepository, EmployeeGenerator employeeGenerator) {
        this.employeeRepository = employeeRepository;
        this.employeeGenerator = employeeGenerator;
    }

    @PostConstruct
    public void runAtStart() {
        generateManyEmployees();

//        logger.info("ALL EMPLOYEES");
//       printAll(employeeRepository.findByFirstNameIgnoreCase("jOhN"));

//        logger.info("ALL EMPLOYEES");
//        printAll(employeeRepository.findByLastNameOrderByFirstNameDesc("Smith"));

//        logger.info("ALL EMPLOYEES");
//        printAll(employeeRepository.findBySalaryBetween(new BigDecimal("1000"), new BigDecimal("2000")));

        logger.info("FIRST JOHN:" + employeeRepository.findByFirstName("John"));
        logger.info("FIRST Arthur:" + employeeRepository.findByFirstName("Arthur"));
    }

    private void generateManyEmployees() {
        for (int i = 0; i < 100; i++) {
            employeeRepository.save(
                    employeeGenerator.generate());
        }
    }


    private void printAll(List<Employee> allUnsorted) {
        allUnsorted.forEach(logger::info);
    }
}
