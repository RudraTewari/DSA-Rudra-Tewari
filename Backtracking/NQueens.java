package Backtracking;

public class NQueens {
    public static int nQueens(boolean[][] board, int row){
        if(row==board.length){
            display(board);
            System.out.println();
            return 1;
        }

        int count=0;
        for(int col = 0; col<board[row].length;col++){
            // CHECK WHETHER THE QUEEN CAN SAFELY BE PLACED
            // ----------------------------------------------------
            // Before placing a queen at [row][col], we check
            // whether another queen can attack this position.
            //
            // isSafe() checks:
            // 1. Same column
            // 2. Upper-left diagonal
            // 3. Upper-right diagonal
            //
            // We don't need to check the current row or lower
            // rows because:
            //
            // - We place only ONE queen in each row.
            // - Lower rows have not been processed yet.
            if(isSafe(board, row, col)){
                board[row][col]=true;
                count += nQueens(board, row +1);
                board[row][col]=false;
            }
        }
        return count;
    }  
    public static boolean isSafe(boolean[][] board, int row, int col) {
        // VERTICAL / COLUMN CHECK
        // Check all rows ABOVE the current row in the same column.
        //
        // We don't check the current row because we are about to
        // place the queen there.
        //
        // We don't check rows below because queens haven't been
        // placed there yet.
        for(int i=0; i< row;i++){
            // If there is already a queen in this column,
            // the new queen would be attacked vertically.
            if(board[i][col]) return false;
        }

        // LEFT DIAGONAL CHECK
        // ========================================================
        // From [row][col], move diagonally toward the upper-left:
        //
        // [row-1][col-1]
        // [row-2][col-2]
        // [row-3][col-3]
        // ...
        //
        // We need to make sure there is no queen on this diagonal.
        // ========================================================

        // --------------------------------------------------------
        // Find how many steps we can safely move diagonally
        // toward the upper-left.
        //
        // We cannot move more than:
        // - 'row' positions upward
        // - 'col' positions toward the left
        //
        // Math.min() gives us the smaller of these two values.
        int maxLeft = Math.min(row, col);
        for(int i=1;i<= maxLeft; i++){
            if(board[row-i][col-i]) return false;
        }


        // RIGHT DIAGONAL CHECK
        // ========================================================
        // From [row][col], move diagonally toward the upper-right:
        //
        // [row-1][col+1]
        // [row-2][col+2]
        // [row-3][col+3]
        // ...
        //
        // Again, we check whether another queen exists there.
        
        // Calculate the maximum number of steps we can move
        // toward the upper-right.
        //
        // We cannot move more than:
        // - 'row' positions upward
        // - board.length - col - 1 positions toward the right
        //
        // Math.min() gives the maximum safe number of steps.
        int maxRight= Math.min(row, board.length-col-1);
        for (int i = 1; i <= maxRight; i++) {
            if(board[row-i][col+i]) return false;
        }

        return true;
    }
    public static void display(boolean[][] board) {
        for(int i=0;i<10;i++) System.out.print("- ");
        System.out.println();
        for(boolean[] row : board ){
            for(boolean element : row){
                if(element){
                    System.out.print("Q ");
                }else{
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0;i<10;i++) System.out.print("- ");
    }
    public static void main(String[] args) {
        int n=6;
        boolean[][] board = new boolean[n][n];
        System.out.println("Number of Ways : " + nQueens(board, 0));
    }
}
