package Backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Approach:
1. We need to generate all happy strings of length n in lexicographical order.
2. A happy string is a string where no two adjacent characters are the same.
3. We use Backtracking to build the string character by character.
4. At every position, we try characters 'a', 'b', and 'c' in order.
5. If the current character is the same as the previous character, we skip it.
6. When the string reaches length n, we store it in the answer list.
7. Since we try 'a', 'b', and 'c' in order, the generated strings are
    automatically in lexicographical order.
8. Finally, if fewer than k strings exist, return an empty string.
    Otherwise, return the (k-1)th string because List indexing starts from 0.

Intuition:
- Think of the solution as a tree of possible strings.
- At every level, we choose one of 'a', 'b', or 'c'.
- We cannot choose the same character as the previous character.
- Whenever a complete string is formed, we add it to the list.
- Backtracking removes the last character so that we can try another choice.
*/
class Solution {

    static List<String> ans;  // Stores all valid happy strings
    static int count;
    static String result;
    public static void Solve(int n, int k, StringBuilder p) {

        if (p.length() == n) {
            ans.add(p.toString());  // Complete happy string found
            return;                 // Stop exploring this branch
        }

        // Try 'a', 'b', and 'c' in order -> gives lexicographical order
        for (char ch = 'a'; ch <= 'c'; ch++) {

            // Skip if current character is same as previous character
            if (!p.isEmpty() && p.charAt(p.length() - 1) == ch)
                continue;

            p.append(ch);  // Choose the current character
            Solve(n, k, p);  // Recursively build the remaining string
            p.deleteCharAt(p.length() - 1);  // Backtrack: undo the choice
        }
    }

    public static void SolveOptimised(int n, int k, StringBuilder p) {
        // A complete happy string has been formed
        if (p.length() == n) {
            count++;    // Count this happy string
            // If this is the kth string, store it
            if (count == k) {
                result = p.toString();
            }
            return;
        }
        // Try characters in lexicographical order
        for (char ch = 'a'; ch <= 'c'; ch++) {
            // Same adjacent characters are not allowed
            if (!p.isEmpty() && p.charAt(p.length() - 1) == ch)
                continue;
    
            p.append(ch);               // Choose
            SolveOptimised(n, k, p);    // Explore
            p.deleteCharAt(p.length() - 1); // Backtrack
        }
    }

    public String getHappyString(int n, int k) {

        ans = new ArrayList<>();    // Initialize list to store valid strings
        StringBuilder sb = new StringBuilder();  // Stores the current string
        Solve(n, k, sb);  // Generate all happy strings
        if (ans.size() < k) return ""; // Fewer than k happy strings exist
        return ans.get(k - 1);       // Return kth string (list uses 0-based index)
    }
    public String getHappyStringII(int n, int k){
        count=0;
        result="";
        StringBuilder sbII = new StringBuilder();
        SolveOptimised(n, k, sbII);
        return result;
    }
}
public class KthLexicographicHappy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Enter N :");
        int n = sc.nextInt();
        System.out.printf("Enter K:");
        int k=sc.nextInt();

        Solution obj = new Solution();
        String ans = obj.getHappyString(n,k);
        String ansII = obj.getHappyStringII(n, k);
        System.out.println(ans);
        System.out.println(ansII);
    }
}
