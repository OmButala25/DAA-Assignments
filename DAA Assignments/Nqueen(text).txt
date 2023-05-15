import java.util.*;

public class Nqueen {
    public static int queens = 8;
    public static int[] x = new int[queens];
    public static int flag = 0;

    public static boolean place(int k, int j) {
        for (int q = 0; q < k; q++) {
            if ((j == x[q]) || (Math.abs(k - q) == Math.abs(j - x[q]))) {
                return false;
            }
        }
        return true;
    }

    public static void nq(int k, int n) {
        for (int j = 1; j <= n; j++) {
            if (place(k, j)) {
                x[k] = j;
                if (k == n - 1 && flag == 0) {
                    System.out.println(Arrays.toString(x));
                    flag = 1;
                } else {
                    nq(k + 1, n);
                }
            }
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        nq(0, queens);
        long end = System.nanoTime();
        long execution = end - start;
        System.out.println("Execution time: " + execution + " nanoseconds");
    }
}

// The time complexity of the provided code for the n-queen problem using backtracking is typically expressed as O(n!), where n is the number of queens (or the size of the chessboard).

// In the nq method, for each row (k), it tries to place a queen in each column (j). Since there are n rows, and in each row, it checks n columns, the total number of recursive calls made is n^n. However, due to the pruning effect of backtracking, not all possible combinations are explored.

/*
Here's an explanation of the code:

The code begins by defining the class Main.
It declares the necessary variables: queens to represent the number of queens (set to 8), x as an array to store the positions of the queens, and flag to track if a solution has been found.
The place method checks if it is safe to place a queen at a specific position (k, j). It iterates over the previously placed queens (from 0 to k-1) and checks if the new queen at position (k, j) conflicts with any of the previous queens horizontally or diagonally. If a conflict is found, it returns false, indicating that the position is not safe.
The nq method is the main recursive function that solves the n-queen problem. It takes two parameters: k representing the current row and n representing the total number of rows (or the number of queens).
The method iterates over the columns (from 1 to n) and tries to place a queen at each position in the current row (k). It checks if it is safe to place the queen using the place method.
If it is safe to place the queen, it assigns the position (j) to the x array at index k.
If the current row is the last row (k == n-1), it means all queens have been successfully placed. In this case, it prints the positions stored in the x array, representing a valid solution to the n-queen problem.
Otherwise, it recursively calls the nq function for the next row (k+1).
In the main method, it measures the execution time by recording the start time before calling nq and the end time after the execution finishes. It then calculates the execution time in nanoseconds.
Finally, it prints the execution time along with the solution(s) found.
When you run this code, it will print one of the possible solutions to the 8-queen problem, along with the execution time. The nq method uses a backtracking algorithm to explore all possible combinations of queen placements while ensuring no two queens threaten each other. The code recursively explores the search space, trying different positions for each queen until a valid solution is found or all possibilities are exhausted.
 */


