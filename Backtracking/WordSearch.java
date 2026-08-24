package Backtracking;

public class WordSearch {
    /*
    APPROACH: BACKTRACKING TO SEARCH FOR THE WORD
    
    1. Base Case:
        If idx reaches the length of the word, it means all characters
        have been matched successfully, so return true.
    
        if (idx == word.length()) {
            return true;
        }
    
    
    2. Validate the Current Cell:
        Return false if:
        - The position is outside the board.
        - The cell has already been visited in the current path.
        - The current board character does not match word.charAt(idx).
    
        if (r < 0 || c < 0 ||
            r>= board.length || c >= board[0].length ||
            board[r][c] == '$') {
            return false;
        }
    
        if (board[r][c] != word.charAt(idx)) {
            return false;
        }
    
    
    3. Mark the Current Cell:
        Store the original character and replace it with '$' to mark
        the cell as visited. This prevents using the same cell again
        while searching for the current word path.
    
        char temp = board[r][c];
        board[r][c] = '$';
    
    
    4. Explore All Four Directions:
        After matching the current character, recursively search for
        the next character in Down, Right, Up, and Left directions.
    
        boolean down = find(board, r + 1, c, word, idx + 1);
        boolean right = find(board, r, c + 1, word, idx + 1);
        boolean up = find(board, r - 1, c, word, idx + 1);
        boolean left = find(board, r, c - 1, word, idx + 1);
    
    
    5. Backtrack:
        Restore the original character after exploring all directions.
        This allows the same cell to be used again while checking a
        different possible path.
    
        board[r][c] = temp;
    
    
    6. Return the Result:
        If any of the four directions successfully finds the remaining
        characters of the word, return true.
    
        return down || right || up || left;
    
    
    BACKTRACKING PATTERN:
    Validate -> Match -> Mark -> Explore -> Undo -> Return
    
    NOTE:
    Each recursive call moves to the next cell and increments idx,
    meaning we search for the next character of the word.
    
    TIME COMPLEXITY: O(N * M * 4^L)
    where N = number of rows, M = number of columns,
    and L = length of the word.
    
    SPACE COMPLEXITY: O(L)
    due to the recursion stack in the worst case.
*/
    static boolean find(char[][] board, int r, int c, String word, int idx){
        if(idx==word.length()) return true;

        if(r<0||c<0||r>=board.length||c>= board[0].length|| board[r][c]=='$') return false;

        if(board[r][c] != word.charAt(idx)) return false;

        char temp = board[r][c];
        board[r][c] = '$';

        boolean down = find(board, r+1, c, word, idx+1);
        boolean right = find(board, r, c+1, word, idx+1);
        boolean up = find(board, r-1, c, word, idx+1);
        boolean left = find(board, r, c-1, word, idx+1);

        board[r][c]=temp;

        return down||right||up||left;
    }
    /*
    APPROACH: TRY EVERY CELL AS A STARTING POINT

    1. Traverse every cell of the board using nested loops.
    Any cell can potentially be the starting position of the word.

    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {

        }
    }


    2. Start searching only if the current cell matches the first
    character of the word. This avoids unnecessary recursive calls.

    if (board[i][j] == word.charAt(0)) {
        Start backtracking
    }


    3. Start the recursive function with idx = 0 because the current
    board cell should be matched with word.charAt(0).

    int idx = 0;

    find(board, i, j, word, idx);


    4. The find() function explores all possible directions from the
    current cell using backtracking. If it successfully finds all
    characters of the word, immediately return true.

    if (board[i][j] == word.charAt(0) &&
        find(board, i, j, word, 0)) {
        return true;
    }


    5. If no starting cell can form the complete word, return false
    after checking the entire board.

    return false;

*/
    static boolean exist(char[][] board, String word){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int idx = 0;
                if(board[i][j]==word.charAt(0) && find(board, i,j, word, idx))
                    return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        char[][] board ={
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        String word = "ABCCED";

        boolean ans = exist(board, word);
        System.out.println(ans);
    }
    
}
