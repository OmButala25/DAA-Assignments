//place queen in n number of boards cross to n number of queens
public class NQueens {
    public static void main(String[] args) {
        int n = 8;
        boolean[][] board = new boolean[n][n];
        System.out.println(queen(board,0));
    }

    static int queen(boolean[][] board, int row){
        if(row == board.length){
            display(board);
            System.out.println();
            return 1;
        }

        int count = 0;
        // here we will placing the queen and check the every row n col
        for (int col = 0; col < board.length; col++) {
            //place the queen is it is safe
            if (issafe(board, row, col)){
                board[row][col] = true;
                count += queen(board,row+1);
                board[row][col] = false;                                //backtracking
            }
        }
        return count;
    }

    private static boolean issafe(boolean[][] board, int row, int col) {
        //check vertical row
        for (int i = 0; i < row; i++) {
            if (board[i][col]){
                return false;
            }
        }

        //check for diagonal left
        int maxLeft = Math.min(row, col);
        for (int i = 1; i <= maxLeft; i++) {
            if (board[row - i][col - i]){
                return false;
            }
        }

        //check for diagonal right
        int maxRight = Math.min(row, board.length -col -1);
        for (int i = 1; i <= maxRight; i++) {
            if (board[row - i][col + i]){
                return false;
            }
        }

        return true;
    }

    private static void display(boolean[][] board) {
        for (boolean[] row : board){                                //for every row in board
            for (boolean element : row){                            //for every element in row
                if(element){                                        //element == true if
                    System.out.print("Q ");
                }else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}

// The time complexity of the n-queens problem using backtracking is typically expressed as O(n!), where n is the number of queens (or the size of the chessboard).

/*Here's an explanation of the code:

The code is a backtracking solution to the n-queens problem. It aims to place n queens on an n x n chessboard in such a way that no two queens threaten each other.

The main method initializes the chessboard as a boolean matrix called board, with each cell representing a position on the board. Initially, all cells are set to false.

The queen method is a recursive function that tries to place queens on the board starting from the given row. It returns the number of valid configurations found.

In the queen method, if the current row is equal to the board length, it means all queens have been successfully placed. It displays the board configuration using the display method and returns 1 to indicate a valid configuration has been found.

The count variable keeps track of the total number of valid configurations found so far.

The method then iterates over each column in the current row and checks if it is safe to place a queen at that position using the issafe method.

The issafe method checks three conditions to determine if it is safe to place a queen:

It checks if there is any queen in the same column above the current row.
It checks if there is any queen in the diagonal to the top-left of the current position.
It checks if there is any queen in the diagonal to the top-right of the current position.
If it is safe to place a queen at the current position, the method sets the corresponding cell in the board matrix to true and makes a recursive call to the queen method for the next row (row+1).

After the recursive call returns, the method performs backtracking by setting the current position back to false in the board matrix. This allows the algorithm to explore other possibilities.

Finally, the display method is used to print the board configuration. It replaces true cells with "Q" to represent queens and false cells with "X" to represent empty cells.

The main method initializes the board and calls the queen method with the initial row set to 0. It then prints the number of valid configurations found.

When you run this code, it will display all the valid configurations of placing n queens on an n x n chessboard, along with the count of the total number of valid configurations. */