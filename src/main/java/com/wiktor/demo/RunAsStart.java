package com.wiktor.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

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
//
//        logger.info("FIRST JOHN:" + employeeRepository.findByFirstName("John"));
//        logger.info("FIRST Arthur:" + employeeRepository.findByFirstName("Arthur"));

//        printAll(employeeRepository.findTop3ByFirstName("John"));

//        logger.info(
//                String.format(
//                        "Number of John Smith's: ",
//                employeeRepository
//                        .countByFirstNameAndLastNameIgnoreCase(
//                                "John",
//                                "Smith")));


//        Page<Employee> johnPage = employeeRepository.findByFirstName("John", new PageRequest(1,3));
//        printAll(johnPage.getContent());
//        logger.info("Total number of pages: " + johnPage.getTotalPages());


//        Stream<Employee> johnStream = employeeRepository.findTop10ByFirstName("John");
//        List<String> johnsLastName = johnStream
//                .map(Employee::getLastName)
//                .collect(Collectors.toList());
//        logger.info(johnsLastName);


        employeeRepository.findFirstByFirstNameIgnoreCase("John").thenAccept(john -> {
            logger.info("John: " + john);
        });
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
