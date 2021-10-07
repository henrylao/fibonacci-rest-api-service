package com.example.hcl.service.prime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

//@AutoConfigureRestDocs(outputDir = "target/snippets")
@RestController
public class PrimeController {
    // TODO: add mechanism to store the last largest entry defining the
    // largest value that has been computed and cached in a database
    // TODO: can be consolidated under an interface
    public static final String VERSION = "/v1/";
    public static final String RESOURCE = "/prime/";
    public static final String BASE_URI = "/api/" + VERSION + RESOURCE;
    private int lastLargeEntry;

    @Autowired
    private PrimeRepository primeRepository;

    //////////////////////////////////////////////////////
    /////////////////////// CREATE ///////////////////////
    //////////////////////////////////////////////////////

    /**
     * POST method for adding a Prime element into the database
     *
     * @param input
     */
    @PostMapping(BASE_URI + "/add")
    public void addPrime(@RequestParam Integer input) throws ExecutionException, InterruptedException {
        // early stopping
        Boolean exists = primeRepository.existsById(input);
        if (exists) {
            return;
        }

        Prime prime = new Prime();
        List<Integer> primeSeries = prime.sieveOfEratosthenes(input);
        System.out.println(primeSeries);

        // create the collection of prime elements
        List<Prime> primeCollection = new ArrayList<>();
        Prime toAdd;
        for (Integer p : primeSeries) {
            toAdd = new Prime();
//            toAdd.setValue(p);
            toAdd.setId(p);
            toAdd.setValue(p.longValue());
            primeCollection.add(toAdd);
        }
        System.out.println("Prime objects: " + primeCollection);
        primeRepository.saveAll(primeCollection);   // should this be moved?
    }

    //////////////////////////////////////////////////////
    /////////////////////// READ /////////////////////////
    //////////////////////////////////////////////////////
    // TODO: add get by value

    @GetMapping(BASE_URI + "/get/all")
    public ResponseEntity<Iterable<Prime>> getAllPrimes() {
        try {
            List<Prime> PrimeList = primeRepository.findAll();
            if (PrimeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(PrimeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @PostMapping(BASE_URI + "/valid")
    public ResponseEntity<Boolean> isPrime(@RequestParam Integer input) throws ExecutionException, InterruptedException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SERVER, "isPrime");

        // known cases
        if (input.equals(0) || input.equals(1)) {
            return ResponseEntity.ok().headers(headers).body(Boolean.FALSE);
//            return new ResponseEntity<>(Boolean.valueOf(Boolean.FALSE), HttpStatus.OK);
        }

        // [1] can either get just the boolean value or
        // [2] get the actual object -- more resources are used by doing this,
        Boolean data = primeRepository.existsById(input);

        // TODO: add checking for the last largest input denoting, highest point of computation
        // add a variable for store this state


        if (data.equals(false)) {
            return ResponseEntity.ok().headers(headers).body(Boolean.FALSE);
//            return new ResponseEntity<>(Boolean.valueOf(Boolean.FALSE), HttpStatus.OK);
        }
//        return new ResponseEntity<>(Boolean.valueOf(Boolean.TRUE), HttpStatus.OK);
        return ResponseEntity.ok().headers(headers).body(Boolean.TRUE);

    }
    /**
     * TODO: add ability to access subset from the database of Prime elements
     *
     * @return
     */

//    @GetMapping(BASE_URI + "/list/{index}")
//    public Iterable<Prime> getPrime() {
//        return PrimeRepository.findAll();
//    }

    /**
     * GET method for reading a Prime element
     *
     * @param input
     * @return
     */
    @GetMapping(BASE_URI + "/get")
    public ResponseEntity<Prime> getPrimeById(@RequestParam Integer input) {
        Optional<Prime> data = primeRepository.findById(input);

        System.out.println(data);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(data.get(), HttpStatus.NOT_FOUND);
    }

    //////////////////////////////////////////////////////
    /////////////////////// UPDATE ///////////////////////
    //////////////////////////////////////////////////////

    // TODO: add update value by id


    //////////////////////////////////////////////////////
    /////////////////////// DELETE ///////////////////////
    //////////////////////////////////////////////////////

    // TODO: add delete by value

    @DeleteMapping(BASE_URI + "/delete")
    public ResponseEntity<HttpStatus> deletePrimeById(@RequestParam Integer id) {

        Prime toDelete = primeRepository.getById(id);
        try {
            primeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping(BASE_URI + "/delete/all")
    public ResponseEntity<HttpStatus> deleteAllPrimes() {
        try {
            primeRepository.deleteAllInBatch();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
