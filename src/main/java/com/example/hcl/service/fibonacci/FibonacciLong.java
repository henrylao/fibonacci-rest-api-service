package com.example.hcl.service.fibonacci;//package com.example.hcl.service.demo;//package com.example.springboottutorial;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * Class defining an element in a Fibonacci series
 */
@Entity
public class FibonacciLong {
    public static final String DEBUG = "Fibonacci: ";

    // integer denoting position in a Fibonacci series
    @Id
    private Integer id;

    // value as a result of the given position
//    private BigInteger value;
    private long value;

    public FibonacciLong() {
//        this(-1, BigInteger.valueOf(-1));
//        this(-1, BigInteger.valueOf(-1));
        this(-1, -1);
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "FibonacciLong{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public BigInteger getValue() {
//        return value;
//    }

    //    public Integer getValue() {
//        return value;
//    }
    public long getValue() {
        return value;
    }

//    public void setValue(BigInteger value) {
//        this.value = value;
//    }

    //    public void setValue(Integer value) {
//        this.value = value;
//    }
    public void setValue(long value) {
        this.value = value;
    }

    public FibonacciLong(Integer id, long value) {
        this.id = id;
        this.value = value;
    }

/**
 * TODO: refactor to use memoization when reading from the existing database
 * Brute-force solution for generating
 *
 * @param position
 * @return
 */
//    public BigInteger calculate(int position) throws ExecutionException, InterruptedException {
////        Map<Integer, Integer> seriesCache = new HashMap<>();
////        seriesCache.put(0, 0);
////        seriesCache.put(1, 0);
////
////        System.out.println(DEBUG + "calculate(" + position + ")" + "=" +);
//        FibonacciCalculator fibonacciCalculator = new FibonacciCalculator(position);
//        Integer value = fibonacciCalculator.calculateFibonacci(position);
//        Map<Integer, Integer> cache = fibonacciCalculator.getMemo();
//
////        BigInteger.valueOf(fib(position))
//        return;


    // attempt 2 to allow for multithreading
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Future<BigInteger> future = null;
//        future = executor.submit(new FibonacciCalculator(position));
//        return future.get();

    // attempt 1
//        FibonacciCalculator calculator = new FibonacciCalculator();
//        if (position == 0 || position == 1) {
//            return BigInteger.valueOf(position);
//        }
//        BigInteger n0 = BigInteger.ZERO;
//        BigInteger n1 = BigInteger.ONE;
//        BigInteger tempPositionValue;
//        for (int i = 2; i <= position; i++) {
//            tempPositionValue = n0.add(n1);
//            n0 = n1;
//            n1 = tempPositionValue;
//            if (Thread.interrupted()) {
//                break;
//            }
//        }
//        return n1;
//}

/**
 * TODO: refactor to allow for internal storage of existing elements
 *
 * @param position
 * @return
 */
//    public Map<String, Object> calculate(int position){
//        Map rv = new HashMap<String, Object>();
//        if (position == 0 || position == 1) {
//            Fibonacci f = new Fibonacci();
//            f.position = position;
//            f.value = BigInteger.valueOf(0);
//            rv.put("result", f);
//        }
//        BigInteger n0 = BigInteger.ZERO;
//        BigInteger n1 = BigInteger.ONE;
//        BigInteger tempPositionValue;
//        for (int i = 2; i <= position; i++) {
//            tempPositionValue = n0.add(n1);
//            n0 = n1;
//            n1 = tempPositionValue;
//            if (Thread.interrupted()) {
//                break;
//            }
//        }
//        return n1;
//    }


//    private String firstName;
//    private String lastName;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
}