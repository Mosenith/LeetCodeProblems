package AlgorithmsCode.DynamicProgramming;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FibonacciSequenceDP {
    public static int calculateFibonacci(int n) {
        Integer[] lookup = new Integer[n + 1];
        int res = fib(n, lookup);
        return res;
    }

    private static int fib(int n, Integer[] lookup) {
        if(n == 0 || n == 1) {
            lookup[n] = n;
            return lookup[n] = n;
        }

        if(lookup[n] == null) {
            lookup[n] = fib(n-1, lookup) + fib(n-2, lookup);
        }
        return lookup[n];
    }


    public static void main(String[] args) {
        System.out.println(calculateFibonacci(4));
    }
}
