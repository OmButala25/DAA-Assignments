//1. Using DP
import java.lang.Math;
public class Knapsack {
    public static int knapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(values[i - 1] + dp[i - 1][j - weights[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    public static void main(String[] args) {
        int[] values = {6, 10, 12};
        int[] weights = {2, 3, 4};
        int capacity = 5;
        
        long start = System.nanoTime();
        int maxValue = knapsack(values, weights, capacity);
        long end = System.nanoTime();
        long duration = end - start;
        
        System.out.println("Max value: " + maxValue);
        System.out.println("Duration: " + duration + " nanoseconds");
    }
}

//The time complexity of the dynamic programming solution for the 0/1 knapsack problem is O(n * capacity), where n is the number of items and capacity is the maximum capacity of the knapsack.

/*The knapsack method takes three parameters: an array of values, an array of weights, and the capacity of the knapsack. It returns the maximum value that can be obtained.

The variable n represents the length of the values array.

The dp array is a two-dimensional array used for dynamic programming. It has dimensions (n + 1) x (capacity + 1). Each cell dp[i][j] represents the maximum value that can be obtained by considering the first i items and having a knapsack capacity of j.

The nested for loop iterates over the items and capacities to fill the dp array. The outer loop iterates from i = 1 to n, representing the items. The inner loop iterates from j = 0 to capacity, representing the knapsack capacities.

Inside the loop, we check if the weight of the current item is less than or equal to the current capacity (weights[i - 1] <= j). If it is, we can consider including the item in the knapsack. In this case, we calculate the maximum value by either including the current item (values[i - 1] + dp[i - 1][j - weights[i - 1]]) or excluding the current item (dp[i - 1][j]). We take the maximum of these two values and store it in dp[i][j].

If the weight of the current item is greater than the current capacity, we cannot include the item. In this case, we simply take the value from the previous row (dp[i - 1][j]).

Finally, we return the maximum value from the last row and last column of the dp array (dp[n][capacity]), which represents the maximum value that can be obtained with the given capacity.

In the main method, we initialize the values, weights, and capacity arrays with sample values. We measure the execution time of the knapsack method using System.nanoTime() before and after the method call to calculate the duration. We then print the maximum value and the duration in nanoseconds.

The code implements the 0/1 knapsack problem using dynamic programming to efficiently solve it. It calculates the maximum value that can be obtained by selecting items with given weights and values, while respecting the capacity constraint of the knapsack. */




//2. Using recurrsion
import java.lang.Math;
public class Knapsack {
    public static int knapsack(int[] values, int[] weights, int capacity, int n) {
        if (n == 0 || capacity == 0) {
            return 0;
        }
        
        if (weights[n - 1] > capacity) {
            return knapsack(values, weights, capacity, n - 1);
        }
        int withItem = values[n - 1] + knapsack(values, weights, capacity - weights[n - 1], n - 1);
        int withoutItem = knapsack(values, weights, capacity, n - 1);
        return Math.max(withItem, withoutItem);
    }
    
    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        int n = values.length;
        
        long start = System.nanoTime();
        int maxValue = knapsack(values, weights, capacity, n);
        long end = System.nanoTime();
        long duration = end - start;
        
        System.out.println("Max value: " + maxValue);
        System.out.println("Duration: " + duration + " nanoseconds");
    }
}

//The time complexity of the recursive solution for the knapsack problem is exponential, specifically O(2^n), where n is the number of items.

/*This code solves the knapsack problem using a recursive approach. It calculates the maximum value that can be obtained by selecting items with given weights and values while considering the capacity constraint of the knapsack.

The knapsack method takes four parameters: an array of values, an array of weights, the capacity of the knapsack, and the current index n. It returns the maximum value that can be obtained by considering the items up to index n.

The base cases check if either n or capacity is 0, in which case the maximum value is 0.

If the weight of the current item (weights[n - 1]) is greater than the current capacity, we cannot include it in the knapsack. In this case, we recursively call the knapsack method with the remaining items (from index 0 to n - 1) and the same capacity.

If the weight of the current item is less than or equal to the current capacity, we have two choices: either include the current item or exclude it. We recursively call the knapsack method twice: once by including the current item (values[n - 1] + knapsack(values, weights, capacity - weights[n - 1], n - 1)) and once by excluding it (knapsack(values, weights, capacity, n - 1)). We return the maximum value among these two choices.

In the main method, we initialize the values, weights, and capacity arrays with sample values. We measure the execution time of the knapsack method using System.nanoTime() before and after the method call to calculate the duration. We then print the maximum value and the duration in nanoseconds. */

