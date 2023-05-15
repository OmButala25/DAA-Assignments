import java.util.*;

public class TSPBranchAndBound {
    private int[][] graph;
    private int numCities;
    private int minDistance;
    private Stack<Integer> currentPath;
    private boolean[] visited;

    public TSPBranchAndBound(int[][] graph) {
        this.graph = graph;
        this.numCities = graph.length;
        this.visited = new boolean[numCities];
        this.currentPath = new Stack<>();
        this.minDistance = Integer.MAX_VALUE;
    }

    public int solve() {
        currentPath.push(0);
        visited[0] = true;
        branchAndBound(currentPath, 0, 0);
        return minDistance;
    }

    private void branchAndBound(Stack<Integer> path, int distance, int level) {
        if (level == numCities - 1) {
            distance += graph[path.peek()][0];
            if (distance < minDistance) {
                minDistance = distance;
            }
        } else {
            for (int i = 1; i < numCities; i++) {
                if (!visited[i]) {
                    int lowerBound = calculateLowerBound(path, distance, i);
                    if (lowerBound < minDistance) {
                        path.push(i);
                        visited[i] = true;
                        branchAndBound(path, distance + graph[path.get(level)][i], level + 1);
                        path.pop();
                        visited[i] = false;
                    }
                }
            }
        }
    }

    private int calculateLowerBound(Stack<Integer> path, int distance, int nextCity) {
        distance += graph[path.get(path.size() - 1)][nextCity];
        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                int closestCityDistance = Integer.MAX_VALUE;
                for (int j = 0; j < numCities; j++) {
                    if (i != j && !visited[j] && graph[i][j] < closestCityDistance) {
                        closestCityDistance = graph[i][j];
                    }
                }
                distance += closestCityDistance;
            }
        }
        distance += graph[nextCity][0];
        return distance;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        TSPBranchAndBound tsp = new TSPBranchAndBound(graph);
        long start = System.nanoTime();
        int minDistance = tsp.solve();
        long end = System.nanoTime();
        long elapsedTime = end - start;

        // Print the minimum distance found
        System.out.println("Minimum distance: " + minDistance);
        System.out.println("Elapsed Time: " + elapsedTime);
    }
}


// In the worst case, the Branch and Bound algorithm has an exponential time complexity of O(n!) since it explores all possible permutations of the cities. This is because the algorithm needs to consider all possible orderings of the cities to find the optimal solution.

/* 
Here's an explanation of the code:

The TSPBranchAndBound class represents the implementation of the Branch and Bound algorithm for the Traveling Salesman Problem (TSP). It takes a 2D array graph as input, which represents the distance matrix between cities.

The class has instance variables to store the graph, the number of cities (numCities), the minimum distance found (minDistance), the current path (currentPath), and an array to track visited cities (visited).

The solve() method is the entry point of the algorithm. It initializes the current path with the starting city (0) and marks it as visited. Then it calls the branchAndBound() method to perform the search.

The branchAndBound() method is a recursive function that implements the main logic of the Branch and Bound algorithm. It takes the current path, current distance, and the current level as parameters.

If the current level is equal to numCities - 1, it means all cities have been visited. In this case, the distance is updated by adding the distance from the last city to the starting city. If the updated distance is smaller than the current minimum distance (minDistance), the minimum distance is updated.

If the current level is less than numCities - 1, it means there are unvisited cities. The method iterates over the unvisited cities and calculates the lower bound for each possible next city. If the lower bound is smaller than the current minimum distance, the next city is added to the current path, marked as visited, and the branchAndBound() method is recursively called with the updated path and distance.

The calculateLowerBound() method calculates the lower bound heuristic for a given path and next city. It calculates the distance by summing the distance from the last city in the path to the next city, and the minimum distances from each unvisited city to its closest neighboring city.

The main() method demonstrates the usage of the TSPBranchAndBound class. It creates a distance matrix graph representing a TSP instance, initializes an instance of TSPBranchAndBound with the distance matrix, and then calls the solve() method. It measures the execution time using System.nanoTime() and prints the minimum distance found and the elapsed time in nanoseconds.

The code uses a stack to keep track of the current path and a boolean array to track visited cities. The algorithm prunes branches based on the calculated lower bound, ensuring that only promising paths are explored. The minimum distance found represents the shortest tour for the given TSP instance.
*/