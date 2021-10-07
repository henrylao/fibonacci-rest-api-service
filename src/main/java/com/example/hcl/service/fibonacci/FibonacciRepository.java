package com.example.hcl.service.fibonacci;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface declaring methods for interacting with the database
 * More Info:
 * <ul>
 *     <li>
 * JPA vs CRUD Repository: https://stackoverflow.com/questions/14014086/what-is-difference-between-crudrepository-and-jparepository-interfaces-in-spring
 *     </li>
 * </ul>
 */
public interface FibonacciRepository extends JpaRepository<Fibonacci, Integer> {
}
