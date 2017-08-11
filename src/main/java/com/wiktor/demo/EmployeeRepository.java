package com.wiktor.demo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameIgnoreCase(String firstName);

    List<Employee> findByLastNameOrderByFirstNameDesc(String lastName);

    List<Employee> findBySalaryBetween(BigDecimal salary1, BigDecimal salary2);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findTop3ByFirstName(String firstName);

    List<Employee> countByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);

    Page<Employee> findByFirstName(String firstName, Pageable pageable);

    Stream<Employee> findTop10ByFirstName(String firstName);

    @Async
    Future<List<Employee>> findDistinctFirstByFirstName (String firstName);

    @Async
    CompletableFuture<Employee> findFirstByFirstNameIgnoreCase(String firstName);

    @Async
    ListenableFuture<Employee> findFirstByLastName (String lastName);
}

