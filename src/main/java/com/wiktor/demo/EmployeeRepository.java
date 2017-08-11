package com.wiktor.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
List<Employee> findByFirstNameIgnoreCase(String firstName);
List<Employee> findByLastNameOrderByFirstNameDesc(String lastName);

List<Employee> findBySalaryBetween(BigDecimal salary1, BigDecimal salary2);
List<Employee> findByFirstName(String firstName);

List<Employee> findTop3ByFirstName(String firstName);

}

