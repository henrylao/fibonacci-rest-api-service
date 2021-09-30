package com.example.hcl.service.fibonacci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * This is the service class controlling the routing
 * when serving fibonacci responses
 */
@RestController
public class FibonacciController {
    // TODO: can be consolidated under an interface
    public static final String VERSION = "/v1/";
    public static final String RESOURCE = "/fibonacci/";
    public static final String BASE_URI = "/api/" + VERSION + RESOURCE;

    @Autowired
    private FibonacciRepository fibonacciRepository;

    /**
     * POST method for adding a Fibonacci element into the database
     *
     * @param id
     */
    @PostMapping(BASE_URI + "/add")
    public void addFibonacci(@RequestParam Integer id) throws ExecutionException, InterruptedException {
//        Fibonacci f = fibonacciRepository.getById(id);
//        // check for existing value in database
//        if (f != null) {
//            return;
//        }

        // create new fibonacci element
        FibonacciCalculator fibonacciCalculator = new FibonacciCalculator(id);
        BigInteger value = fibonacciCalculator.calculateFibonacci(BigInteger.valueOf(id));
        HashMap<Integer, BigInteger> cache = (HashMap<Integer, BigInteger>) fibonacciCalculator.getMemo();
        List<Fibonacci> fibonacciSeries = new ArrayList<>();
        for (Map.Entry e : cache.entrySet()) {
            fibonacciSeries.add(new Fibonacci((Integer) e.getKey(), ((BigInteger) e.getValue())));
//            fibonacciSeries.add(new Fibonacci((Integer) e.getKey(), ((BigInteger) e.getValue()).longValue()));  // long
        }
        System.out.println("Computed " + fibonacciSeries.size() + " fibonacci elements");
//        Fibonacci fibonacci = new Fibonacci();
//        fibonacci.setId(id);
//        BigInteger value = fibonacci.calculate(id);
//        System.out.println("Calculated " + value);
//        fibonacci.setValue(value);
        fibonacciRepository.saveAll(fibonacciSeries);   // should this be moved?
//        fibonacciRepository.save(fibonacci);
    }

    /**
     * GET method for reading all Fibonacci elements
     *
     * @return
     */
    @GetMapping(BASE_URI + "/get/all")
    public ResponseEntity<Iterable<Fibonacci>> getFibonacci() {
        try
        {
            List<Fibonacci> fibonacciList = fibonacciRepository.findAll();
            if (fibonacciList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(fibonacciList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * TODO: add ability to access subset from the database of fibonacci elements
     *
     * @return
     */
//    @GetMapping(BASE_URI + "/list/{index}")
//    public Iterable<Fibonacci> getFibonacci() {
//        return fibonacciRepository.findAll();
//    }

    /**
     * GET method for reading a Fibonacci element
     *
     * @param id
     * @return
     */
    @GetMapping(BASE_URI + "/get")
    public ResponseEntity<Fibonacci> getFibonacciById(@RequestParam Integer id) {
        Optional<Fibonacci> data = fibonacciRepository.findById(id);

        System.out.println(data);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(data.get(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(BASE_URI + "/delete")
    public ResponseEntity<HttpStatus> deleteFibonacci(@RequestParam Integer id) {

        Fibonacci toDelete = fibonacciRepository.getById(id);
        if (toDelete.equals(null)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
//        fibonacciRepository.delete(toDelete);
//        fibonacciRepository.saveAll(fibonacciRepository.findAll());
        try {
            fibonacciRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Transactional
//    @Modifying
    @DeleteMapping(BASE_URI + "/delete/all")
    public ResponseEntity<HttpStatus> deleteAllFibonacci() {
//    public void deleteAllFibonacci() {
        try {
//            if (fibonacciRepository.findAll().size() == 0){
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            fibonacciRepository.deleteAll();
            fibonacciRepository.deleteAllInBatch();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        fibonacciRepository.deleteAll();
//        fibonacciRepository.deleteAll(fibonacciRepository.findAll());
//        fibonacciRepository.saveAll(fibonacciRepository.findAll());
    }

}
