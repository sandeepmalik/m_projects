package com;

/**
 * Author: Monika
 * Date  : 1/30/14
 * Time  : 6:20 PM
 */
public class FizzBuzz {
    public void fizzBuzz(int n) {
        for (int i = 1; i <= n; i++) {
            if ((i % 3 == 0) && (i % 5 == 0)) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            }
        }
    }

    public static void main(String[] args) {
        int n = 16;
        FizzBuzz fizzBuzz = new FizzBuzz();
        fizzBuzz.fizzBuzz(16);
    }
   public int findMaximum (int[] array) {
       int max = Integer.MIN_VALUE;
       for (int  i = 0; i < array.length; i++) {
           if(array[i] > max) {
               max = array[i];
           }
       }
       return max;
   }
}
