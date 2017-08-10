package com.wiktor.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String secondName;
    private BigDecimal salary;
    private LocalDate employeeDate;
}


