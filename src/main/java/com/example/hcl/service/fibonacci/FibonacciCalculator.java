package com.example.hcl.service.fibonacci;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Helper class for handling calculation requiring storage of
 * non-basic types
 */
public class FibonacciCalculator {
    //        implements Callable<BigInteger> {
    private int position;

    public HashMap<Integer, BigInteger> getMemo() {
        return memo;
    }

    private HashMap<Integer, BigInteger> memo = new HashMap<>();

    public FibonacciCalculator(int position) {
        this.position = position;
    }

    // runs extremely slow when n = 100, why?
    // is it because it's single threaded?
    public BigInteger calculateFibonacci(BigInteger n) {

//        if (n <= 1)
//            return n;
//        return fib(n-1, cache) + fib(n-2, cache);
        if (n.compareTo(BigInteger.valueOf(0)) == -1) {
            throw new IllegalArgumentException(
                    "Index was negative. No such thing as a negative index in a series.");

            // base cases
        } else if ((n.compareTo(BigInteger.valueOf(0)) == 0)
                || (n.compareTo(BigInteger.valueOf(1)) == 0)) {
            memo.put(n.intValue(), BigInteger.valueOf(1));
//            return BigInteger.valueOf(n);
            return n;
        }

        // see if we've already calculated this
        if (memo.containsKey(n)) {
//            System.out.printf("grabbing memo[%d]\n", n);
            return memo.get(n.intValueExact());
        }

//        System.out.printf("computing fib(%d)\n", n);
        BigInteger result = calculateFibonacci(
                n.subtract(BigInteger.valueOf(1)))
                .add(calculateFibonacci(n.subtract(BigInteger.valueOf(2))));

        // memoize
        memo.put(n.intValueExact(), result);

        return result;

    }
    /**
     * Returns a Fibonacci number of the specified position.
     *
     * @return the Fibonacci number as BigInteger
     */
//    @Override
//    public BigInteger call() {
//        return BigInteger.valueOf((fib(position)));
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
//        System.out.println(n1);
//        return n1;
//    }

//    static int fib(int n)
//    {
//        if (n <= 1)
//            return n;
//        return fib(n-1) + fib(n-2);
//    }


}