package com.wiktor.demo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class RunAsStart {
    private final EmployeeRepository employeeRepository;
    private final EmployeeGenerator employeeGenerator;
    private final EmployeeRepositoryFromBaseRepository employeeRepositoryFromBaseRepository;
//    private final EmployeeBaseRepository employeeBaseRepository;
    private final Logger logger = Logger.getLogger(RunAsStart.class);

    @Autowired
    public RunAsStart(EmployeeRepository employeeRepository, EmployeeGenerator employeeGenerator, EmployeeRepositoryFromBaseRepository employeeRepositoryFromBaseRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeGenerator = employeeGenerator;
        this.employeeRepositoryFromBaseRepository = employeeRepositoryFromBaseRepository;
//        this.employeeBaseRepository = employeeBaseRepository;
    }

    @PostConstruct
    public void runAtStart() {
        generateManyEmployees();

//        List<Employee> allWithSalariesBetweenSomeValues = employeeRepository.findAllWithSalariesBetweenSomeValues(
//                new BigDecimal("1000"),
//                new BigDecimal("2000")
//        );
//        printAll(allWithSalariesBetweenSomeValues);

//        List<Employee> guyWithHighestSalary = employeeRepository.findGuyWithHighestSalary();
//        printAll(guyWithHighestSalary);

//        Employee onlyGuyWithHighSalary = employeeRepository.findOnlyOneGuyWithHighestSalary();
//        logger.info(onlyGuyWithHighSalary);

//        List<Employee> nativelyWithSalaryBetween = employeeRepository.findNativelyWithSalaryBetween(new BigDecimal("1000"), new BigDecimal("2000"));
//
//        logger.info(nativelyWithSalaryBetween);

        printAll(employeeRepositoryFromBaseRepository.findAll());

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
