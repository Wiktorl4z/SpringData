package com.wiktor.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String secondName;
    private BigDecimal salary;
}