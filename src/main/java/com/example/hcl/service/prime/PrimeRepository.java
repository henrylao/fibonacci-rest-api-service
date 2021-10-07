package com.example.hcl.service.prime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimeRepository extends JpaRepository<Prime, Integer> {
//    Boolean findByValue(Integer value);
}
