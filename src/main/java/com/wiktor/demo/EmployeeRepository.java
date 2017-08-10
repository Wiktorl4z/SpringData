package com.wiktor.demo;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Iterable <Employee> findByFirstName(String firstName);
}

