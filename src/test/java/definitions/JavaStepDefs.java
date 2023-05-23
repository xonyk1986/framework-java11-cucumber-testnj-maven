package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.Animal;
import pages.Cat;
import pages.Dog;

import java.util.*;

public class JavaStepDefs {
    @Given("I work with classes")
    public void iWorkWithClasses() {

        Animal tom = new Cat("Tom");
        tom.walk();
        tom.sleep();
        tom.eat("fish");
        tom.speak();

        Animal jack = new Dog();
        jack.walk();
        jack.sleep();
        jack.eat("bone");
        jack.speak();

    }

// write a function, accepts integer argument
    // should print all the numbers up to the argument

    // BUT:
    // if number is multiple of 3, it should print Fizz instead of number
    // if number is multiple of 5, it should print Buzz instead of number
    // If it is multiple of both 3 and 5, it should print FizzBuzz instead of number


    // SKIP multiples of 4

    @Given("I solve FizzBuzz challenge with {int} number")
    public void iSolveFizzBuzzChallengeWithNumber(int num) {

        for (int i = 1; i <= num; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.print("FizzBuzz ");
            } else if (i % 3 == 0) {
                System.out.print("Fizz ");
            } else if (i % 5 == 0) {
                System.out.print("Buzz ");
            } else if (i % 4 == 0) {
                // do nothing
            } else {
                System.out.print(i + " ");
            }
        }
    }

    public int charOccurrence(String word, Character c) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    public List<Object> uniqueElements(List<Object> list1, List<Object> list2) {
        List<Object> result = new LinkedList<>();
        boolean isFound;
        for(Object item1 : list1) {
            isFound = false;
            for (Object item2 : list2) {
                if (item1.equals(item2)) {
                    isFound = true;
                }
            }
            if (!isFound) {
                result.add(item1);
            }
        }

        for(Object item2 : list2) {
            isFound = false;
            for (Object item1 : list1) {
                if (item2.equals(item1)) {
                    isFound = true;
                }
            }
            if (!isFound) {
                result.add(item2);
            }
        }
        return result;

    }

    @Given("I solve coding challenges")
    public void iSolveCodingChallenges() {
        // Find the number of character occurrence in a word
        System.out.println(charOccurrence("WebDriver", 'e'));

        // Build a new list out of two lists unique elements
        List<Object> list1 = List.of(5, 3, 2, 6);
        List<Object> list2 = List.of(8, 7, 6, 3);
        System.out.println(uniqueElements(list1, list2));


        // 1. write a function that would exchange first and last numbers in an array

        // 2. Write a function that accepts integer number and prints
        //
        //"divisible by 3" if number is divisible by 3
        //"divisible by 4" if element is divisible by 4
        //"divisible by 3 and 4" if divisible by 3 and 4

        // 3. Write a function that prints all even numbers from 0 up to n

        // 4. Write a function that finds the largest element of an array and test it

        // 5. Reverse a string

        // 6. Reverse words in a sentence

        // 7. Write a function that sorts an array

        // 8. Write a function that would find if any two elements in an array result in sum

        // 9. Write a function that would create an array of duplicated numbers from original one

        // 10. Write a function, accepts integer argument
        //It should print all the numbers up to the argument
        //BUT:
        // if number is multiple of 3, it should print Fizz instead of number
        // if number is multiple of 5, it should print Buzz instead of number
        // If it is multiple of both 3 and 5, it should print FizzBuzz instead of number
        //Result for 20:
        //1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz 16 17 Fizz 19 Buzz

        // write a function that would determine if the number is prime
        int number = 17;
        System.out.println("Is " + number + " prime? " + isPrime(number));

        // find factorial using recursive algorithm
        System.out.println(factorial(5));

        // array search
        int[] arr = {2, 4, 6, 8, 9, 10, 14, 15, 19};
        int num = 14;

        System.out.println("Contains num? " + search(arr, num));
        // array binary search
        System.out.println("Contains binary num? " + binarySearch(arr, num));
    }

    public boolean binarySearch(int arr[], int num) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            if (arr[middle] == num) {
                return true;
            } else if (arr[middle] < num) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return false;
    }


    public boolean search(int arr[], int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }


    public int factorial(int num) {
        if (num == 0) {
            return 1;
        }
        return num * factorial(num - 1);
    }

    public boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        if (num % 2 == 0 && num != 2) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i+=2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;

    }



}
