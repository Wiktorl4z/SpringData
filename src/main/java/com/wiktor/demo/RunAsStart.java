package com.wiktor.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
        employee.setEmployeeDate(LocalDate.now());
        employeeRepository.save(employee);

        List<Employee> jans = employeeRepository.findByFirstName("Jan");
        Employee jan = jans.get(0);
        System.out.println("Janek " + jan);
    }
}
