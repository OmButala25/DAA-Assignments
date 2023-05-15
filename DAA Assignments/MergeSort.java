import java.util.Arrays;

public class MergeSort {
    private static int steps = 0;

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1, 6, 8, 3, 12, 20};
        long start = System.nanoTime();
        int[] ans = mergesort(arr);
        long end = System.nanoTime();
        long time = end - start;
        System.out.println(Arrays.toString(ans));
        System.out.println("Execution time: " + time);
        System.out.println("Total steps: " + steps);
    }

    static int[] mergesort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }

        int mid = arr.length / 2;

        int[] left = mergesort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergesort(Arrays.copyOfRange(arr, mid, arr.length));

        return merge(left, right);
    }

    static int[] merge(int[] first, int[] second) {
        int[] mix = new int[first.length + second.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < first.length && j < second.length) {
            steps++; // Increment step count
            if (first[i] < second[j]) {
                mix[k] = first[i];
                i++;
            } else {
                mix[k] = second[j];
                j++;
            }
            k++;
        }

        while (i < first.length) {
            steps++; // Increment step count
            mix[k] = first[i];
            i++;
            k++;
        }
        while (j < second.length) {
            steps++; // Increment step count
            mix[k] = second[j];
            j++;
            k++;
        }
        return mix;
    }
}

/*We start by importing the necessary libraries: java.util.Arrays for array manipulation.

The class MergeSort is defined.

Inside the MergeSort class, the main method is declared. This method is the entry point of the program.

In the main method, an array arr is defined and initialized with some integer values.

The variable start is assigned the value of the current time in nanoseconds using System.nanoTime(). This is done to measure the execution time of the merge sort algorithm.

The mergesort method is called, passing the arr array as an argument. The returned sorted array is assigned to the ans variable.

The variable end is assigned the value of the current time in nanoseconds using System.nanoTime(). This marks the end of the merge sort algorithm execution.

The variable time is calculated as the difference between end and start. It represents the execution time of the merge sort algorithm.

The sorted array ans is printed using System.out.println(Arrays.toString(ans)).

The execution time is printed using System.out.println("Execution time: " + time).

The complexity of merge sort is printed using System.out.println("Total steps: " + steps).

The mergesort method is defined. It takes an array arr as an input and returns the sorted array.

The base case is checked. If the length of the array is 1, it means the array is already sorted, so the same array is returned.

The middle index mid is calculated by dividing the length of the array by 2.

The mergesort method is recursively called on two halves of the array: the left half from index 0 to mid-1 and the right half from index mid to the end of the array.

The merge method is called with the two sorted halves as arguments, and the result is returned.

The merge method is defined. It takes two sorted arrays, first and second, as input and merges them into a single sorted array.

An array mix is initialized with a size equal to the combined lengths of first and second.

Three pointers i, j, and k are initialized to keep track of the current positions in the first, second, and mix arrays, respectively.

A while loop is used to compare elements from first and second arrays and merge them into the mix array. The loop continues as long as there are elements remaining in both arrays.

Inside the loop, if the element in first at index i is smaller than the element in second at index j, it is copied to the mix array at index k, and i and k are incremented. Otherwise, the element from second is copied to mix, and j and k are incremented.

After the first while loop, there may be remaining elements in either first or second arrays. Two separate while loops are used to copy the remaining elements to the mix array.

Finally, the mix array is returned as the sorted result.

The complexity of Merge Sort is O(n log n), where n is the number of elements in the array */