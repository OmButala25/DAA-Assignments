import java.util.Arrays;

public class QuickSort {
    private static int stepCount = 0;

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        long start = System.nanoTime();
        sort(arr, 0, arr.length -1);
        long end = System.nanoTime();
        long time = end - start;
        System.out.println(Arrays.toString(arr));
        System.out.println("Execution time: " + time);
        System.out.println("Step count: " + stepCount);
    }

    static void sort(int[] nums, int low, int hi) {
        if (low >= hi) {
            return;
        }

        int s = low;
        int e = hi;
        int m = s + (e - s) / 2;
        int pivot = nums[m];

        while (s <= e) {
            while (nums[s] < pivot) {
                s++;
                stepCount++; // Increment step count for comparison
            }
            while (nums[e] > pivot) {
                e--;
                stepCount++; // Increment step count for comparison
            }
            if (s <= e) {
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;
                s++;
                e--;
                stepCount += 3; // Increment step count for 3 operations (swap and increments)
            }
        }
        sort(nums, low, e);
        sort(nums, s, hi);
    }
}


/*The given code implements the QuickSort algorithm to sort an array of integers. Here's an explanation of the code:

The main function initializes an array arr with values {5, 4, 3, 2, 1}. It measures the execution time of the sorting algorithm and prints the sorted array, execution time, and step count.

The sort function is a recursive function that performs the QuickSort algorithm. It takes an array nums, a lower index low, and a higher index hi as parameters.

The base case of the sort function is when the lower index low is greater than or equal to the higher index hi. In this case, the function returns and no sorting is performed.

Otherwise, the function proceeds to choose a pivot element. It calculates the middle index m between the lower and higher indices, and sets the pivot as the element at index m in the array nums.

The function enters a while loop that continues as long as the lower index s is less than or equal to the higher index e. Inside the loop, it finds two elements to swap: one from the left side of the pivot that is greater than the pivot, and one from the right side of the pivot that is smaller than the pivot.

It uses two nested while loops to find such elements. The first while loop increments the lower index s until it finds an element greater than the pivot. The second while loop decrements the higher index e until it finds an element smaller than the pivot.

If the lower index s is still less than or equal to the higher index e, it means that elements are out of order and need to be swapped. The function performs the swap by exchanging the elements at indices s and e, and increments the lower index s and decrements the higher index e.

After the while loop completes, the array is partitioned into two segments. The function recursively calls itself to sort the left segment before the pivot (indices low to e), and the right segment after the pivot (indices s to hi).

The recursion continues until the base case is reached (subarrays of size 1 or less), and eventually, the entire array is sorted.

The complexity of the QuickSort algorithm is O(n log n) in the average and best cases, and O(n^2) in the worst case. */