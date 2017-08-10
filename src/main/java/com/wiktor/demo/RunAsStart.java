package com.wiktor.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

        logger.info("UNSORTED");
        printAll(findAllUnsorted());

        logger.info("SORTED BY FIRST NAME");
        printAll(getSortedByFirstName());

        logger.info("SORTED BY FIRST AND LAST NAME");
        printAll(getSortedByFirstAndLastName());

        Page<Employee> page = employeeRepository.findAll((new PageRequest(2, 10)));
        logger.info("TOTAL NUMBER OF EMPLOYEES: " + page.getTotalElements());
        logger.info("TOTAL NUMBER OF PAGE: " + page.getTotalPages());
        logger.info("CURRENT PAGE NUMBER: " + page.getNumber());
        logger.info("ELEMENT OF PAGE");
        printAll(page.getContent());

    }

    private void generateManyEmployees() {
        for (int i = 0; i < 100; i++) {
            employeeRepository.save(
                    employeeGenerator.generate());
        }
    }

    private List<Employee> getSortedByFirstAndLastName() {
        return employeeRepository.findAll(
                new Sort(new Sort.Order(
                        Sort.Direction.ASC, "secondName"
                )));
    }

    private List<Employee> getSortedByFirstName() {
        return employeeRepository.findAll(
                new Sort(
                        new Sort.Order(
                                Sort.Direction.ASC, "firstName"
                        )));
    }

    private List<Employee> findAllUnsorted() {
        return employeeRepository.findAll();
    }

    private void printAll(List<Employee> allUnsorted) {
        allUnsorted.forEach(logger::info);
    }
}
