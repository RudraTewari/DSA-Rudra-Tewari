package Backtracking;

public class NKnights {
    public static int knights(boolean[][] board, int row, int col, int knights){
        // If all knights are placed, we found one valid arrangement.
        // Display the board and return 1 to count this solution.
        if (knights == 0) {
            display(board);
            System.out.println();
            return 1;
        }
    
        // If we reach the end of the board without placing all knights,
        // this branch is invalid, so return 0.
        if (row == board.length - 1 && col == board.length) {
            return 0;
        }
    
        // If the current row is finished, move to the first column
        // of the next row while keeping the remaining knights unchanged.
        if (col == board.length) {
            return knights(board, row + 1, 0, knights);
        }
    
        int count = 0;
    
        // Try placing a knight at the current cell if it is safe.
        // Then recursively solve the remaining board with one less knight.
        if (isSafe(board, row, col)) {
            board[row][col] = true;
    
            count += knights(board, row, col + 1, knights - 1);
    
            // Backtrack: remove the knight so other possibilities can be tried.
            board[row][col] = false;
        }
    
        // Second choice: don't place a knight at the current cell.
        // Continue checking the next cell.
        count += knights(board, row, col + 1, knights);
    
        // Return the total number of valid arrangements found
        // from both choices: placing and skipping the current cell.
        return count;
    }
    private static boolean isSafe(boolean[][] board, int row, int col) {
        
        // Check the four positions from which an already placed knight
        // could attack the current cell.
        if (isValid(board, row - 1, col + 2)) {
            if (board[row - 1][col + 2])
                return false;
        }

        if (isValid(board, row - 1, col - 2)) {
            if (board[row - 1][col - 2])
                return false;
        }

        if (isValid(board, row - 2, col - 1)) {
            if (board[row - 2][col - 1])
                return false;
        }

        if (isValid(board, row - 2, col + 1)) {
            if (board[row - 2][col + 1])
                return false;
        }

        // If none of the four positions contains a knight,
        // the current cell is safe.
        return true;
    }

    private static boolean isValid(boolean[][] board, int row, int col) {
        if(row >= 0 && row < board.length && col >= 0 && col < board.length) 
            return true;
        return false;
    }
    
    private static void display(boolean[][] board) {
        for (boolean[] bs : board) {
            for (boolean bs2 : bs) {
                if(bs2){
                    System.out.print("K ");
                }else{
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int n=4;
        boolean[][] board= new boolean[n][n];
        System.out.println(knights(board, 0, 0, 4));
    }
}
