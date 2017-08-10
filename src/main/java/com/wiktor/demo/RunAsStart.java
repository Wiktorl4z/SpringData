package com.wiktor.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class RunAsStart {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public RunAsStart(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void runAtStart() {
        Employee employee = new Employee();
        employee.setFirstName("Jan");
        employee.setSecondName("Nowak");
        employee.setSalary(new BigDecimal("1337.0"));
        employeeRepository.save(employee);

        Iterable<Employee> jans = employeeRepository.findByFirstName("Jan");
        Employee jan = jans.iterator().next();
        System.out.println("Janek " + jan);
    }
}
