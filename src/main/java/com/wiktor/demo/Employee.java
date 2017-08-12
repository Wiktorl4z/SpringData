package com.wiktor.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NamedQuery(
        name = "Employee.findAllWithSalariesBetweenSomeValues",
        query = "select e from Employee e where salary between ?1 and ?2"
)
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate employeeDate;
}


