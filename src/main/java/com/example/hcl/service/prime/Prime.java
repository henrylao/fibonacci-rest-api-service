package com.example.hcl.service.prime;


import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Sieve of Eratosthenes
 */
@Entity
@Table(name = "prime")
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class Prime {
    // integer denoting position in a Fibonacci series
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private long value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Prime{" +
                "id=" + id +
//                ", value=" + value +
                '}';
    }

    List<Integer> sieveOfEratosthenes(int n) {
        // Create a boolean array
        // "prime[0..n]" and
        // initialize all entries
        // it as true. A value in
        // prime[i] will finally be
        // false if i is Not a
        // prime, else true.
        boolean[] prime = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a
            // prime
            if (prime[p] == true) {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        List<Integer> rv = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true) {
                rv.add(i);
//                System.out.print(i + " ");

            }
        }
        return rv;
    }


}
