package com.wiktor.demo;

import org.springframework.beans.factory.annotation.Autowired;
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
        for (int i = 0; i < 100; i++) {
            employeeRepository.save(
                    employeeGenerator.generate());
        }

        List<Employee> allUnsorted = employeeRepository.findAll();
        printAll(allUnsorted);

        List<Employee> sortedByFirstName = employeeRepository.findAll(
                new Sort(
                        new Sort.Order(
                                Sort.Direction.ASC, "firstName"
                        ),
                        new Sort.Order(
                                Sort.Direction.ASC, "secondName"
                        )
                ));
        logger.info("SORTED BY FIRST NAME");
        printAll(sortedByFirstName);
    }

    private void printAll(List<Employee> allUnsorted) {
        allUnsorted.forEach(logger::info);
    }
}
