import java.util.ArrayList;
import java.util.List;
/*
    ==================== APPROACH ====================

    Problem Understanding

    * Given a string containing letters and digits, generate ALL possible strings
        by changing each letter to either lowercase or uppercase.
    * Digits cannot be changed and must remain exactly as they are.
    * For every letter, we have exactly 2 choices:
        1. lowercase
        2. uppercase
    * Therefore, if there are L letters, the total number of permutations is:
        2^L

    ---

    CORE INTUITION — BACKTRACKING / DFS

    * At every position, we make a choice for the current character.

    * If the character is a digit:
        -> There is only ONE possible choice.
        -> Add the digit to the current string and move to the next character.

    * If the character is a letter:
        -> We have TWO choices:
        1. Add lowercase version.
        2. Add uppercase version.
        -> Recursively explore both choices.

    This naturally forms a binary recursion tree.

    Example:
    s = "a1b"

    
                ""
                |
                a
                |
                a1
                /  \
                a1b  a1B
    

    Result:
    ["a1b", "a1B", "A1b", "A1B"]

    ---

    RECURSIVE STATE

    DFS has four parameters:

    
    DFS(String p, String up, int idx, int len)
    

        p   -> "processed" part of the string
        Contains the characters chosen so far.

    up  -> "unprocessed" part of the string
        Contains the characters that still need to be processed.

    idx -> Current index / number of characters processed.

    len -> Original length of the string.

    Example:

    
    s = "a1b"

    DFS("", "a1b", 0, 3)

    Choose 'a':

    DFS("a", "1b", 1, 3)

    Choose '1':

    DFS("a1", "b", 2, 3)

    Choose 'b':

    DFS("a1b", "", 3, 3)
    

    ---

    BASE CASE

    When all characters have been processed:

    
    if(idx >= len){
        ans.add(p);
        return;
    }
    

    p now represents one complete valid permutation.
    Add it to the global answer list.
    Stop this recursive path.

    Example:

    
    DFS("a1B", "", 3, 3)

    idx == len

    => "a1B" is complete
    => add it to ans
    

    ---

    HANDLING DIGITS

    Digits have no uppercase/lowercase variation.

    Code:

    
    char ch = up.charAt(0);

    if(ch >= '0' && ch <= '9'){
        DFS(p + ch, up.substring(1), idx + 1, len);
        return;
    }
    

    Example:
    s = "a2b"

    When ch = '2':

        Only choice = '2'

    So:

        DFS("a", "2b", 1, 3)
                    |
                    2
                    |
        DFS("a2", "b", 2, 3)
    

    The return is important because we do NOT want to execute
    the lowercase/uppercase branches for a digit.

    ---

    HANDLING LETTERS

    For every letter, create TWO recursive branches.

    Branch 1 -> lowercase:

    char lowerCase = Character.toLowerCase(ch);

    DFS(p + lowerCase,
        up.substring(1),
        idx + 1,
        len);
    

    Branch 2 -> uppercase:

    
    char upperCase = Character.toUpperCase(ch);

    DFS(p + upperCase,
        up.substring(1),
        idx + 1,
        len);
    
    Example:
    ch = 'a'

    Branch 1:
        DFS(p + 'a', ...)

    Branch 2:
        DFS(p + 'A', ...)
    

    ---

    WHY THIS IS BACKTRACKING

    The main idea is:
    Choose -> Explore -> Return
    

    For a letter:

    Choose lowercase
        |
        Explore remaining characters
        |
        Return

    Choose uppercase
        |
        Explore remaining characters
        |
        Return
    

    Conceptually:

    
    for every letter:
        ├── lowercase
        └── uppercase
    

    The recursion automatically explores every possible combination.

    Unlike traditional backtracking where we explicitly undo a choice,
    this implementation does not need an "undo" step because:

    
    p + ch
    creates a new String instead of modifying the existing p.

    ---

    WHY `up.substring(1)` IS USED

    `up` represents the unprocessed portion of the string.

    Example:

    
    up = "a1b"

    Process 'a':

        up.substring(1)
        = "1b"

    Process '1':

        "1b".substring(1)
        = "b"

    Process 'b':

        "b".substring(1)
        = ""
    

    So each recursive call removes the first character from the
    unprocessed portion.

    This makes the recursion easy to understand:

    
    up.charAt(0)       -> character currently being processed

    up.substring(1)    -> remaining characters
    

    ---
    RECURSION TREE

    Example:

    
    s = "a1b"

                        ""
                        |
                        a/A
                        |
                    ┌────┴────┐
                a1         A1
                /  \       /  \
                a1b  a1B   A1b  A1B
    

    Notice that '1' produces only one branch because it is a digit.

    Therefore : Number of leaves = 2^(number of letters)
    
    13. TIME COMPLEXITY

    Let:

        n = length of string
        L = number of letters


    Each letter creates 2 branches.

    Therefore, number of complete permutations : 2^L


    Each result has length n, and constructing/storing each result
    takes O(n).

    Time Complexity : O(2^L * n)


    Since L <= n, worst case : O(2^n * n)
    ---

    14. SPACE COMPLEXITY

    Output itself requires : O(2^L * n)


    Recursion depth is : O(n)


    Additional recursion stack : O(n)


    Therefore, excluding the output : O(n)
    Including the answer list : O(2^L * n)

    ---


    This is a classic DFS + Backtracking problem where each letter
    represents a binary decision.

    ===================================================
*/

class Solution{
    static List<String> ans;
    public static void DFS(String p, String up, int idx , int len){
        if(idx>=len){
            ans.add(p);
            return;
        }
        char  ch = up.charAt(0);
        if(ch>='0' && ch<='9'){
            DFS(p+ch, up.substring(1), idx+1, len);
            return;
        }

        char lowerCase = Character.toLowerCase(ch);
        DFS(p+lowerCase, up.substring(1), idx+1, len);

        char upperCase = Character.toUpperCase(ch);
        DFS(p+upperCase, up.substring(1), idx+1, len);
    }

    public List<String> letterCasePermutation(String s){
        int len = s.length();
        ans=new ArrayList<>();
        DFS("", s, 0, len);
        return ans;
    }
}
public class LetterCasePermutation {
    public static void main(String[] args) {
        String str = "aa4b5C";

        Solution obj = new Solution();
        List<String> ans = obj.letterCasePermutation(str);
        for (String string : ans) {
            System.out.println(string);
        }
    }
}
