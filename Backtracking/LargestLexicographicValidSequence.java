package Backtracking;

import java.util.Scanner;
/*
    QUESTION DESCRIPTION:

    Given an integer n, construct a sequence of length 2*n - 1 such that:

    1. The number 1 occurs exactly once.
    2. Every number from 2 to n occurs exactly twice.
    3. For every number x (2 <= x <= n), the two occurrences of x
       have exactly x numbers between them.

    In other words, if x is placed at index i, its second occurrence
    must be placed at index i + x.

    Among all valid sequences, return the lexicographically largest one.


    APPROACH:

    We use BACKTRACKING.

    At every empty position, we try to place numbers from n down to 1.
    Trying larger numbers first is important because we want the
    lexicographically largest sequence.

    For a number x > 1:
        - Place x at the current index.
        - Place its second occurrence at index currentIndex + x.

    For number 1:
        - Place it only once.

    If placing a number eventually leads to a valid complete sequence,
    return true.

    If it leads to a dead end:
        - Remove the number from the sequence.
        - Mark it as unused again.
        - Try the next smaller number.


    INTUITION:

    Suppose n = 3.

    We have to construct an array of size:

        2*n - 1 = 5

    We try the largest number first:

        3 _ _ 3 _

    Then at the next empty position we try 2:

        3 2 _ 3 2

    Then the remaining empty position gets 1:

        3 2 1 3 2

    This is valid.

    The reason we try numbers from n down to 1 is that the first
    position where two valid sequences differ determines which one
    is lexicographically larger. Therefore, trying the largest
    available number first allows us to find the lexicographically
    largest valid sequence first.

    If a choice cannot produce a valid sequence, BACKTRACK and try
    another choice.
*/
class Solution {

    static int[] ans;  // Stores the sequence being constructed

    public static boolean Solve(int n, int idx, boolean[] used) {

        // If we have reached the end, a complete valid sequence is found
        if (idx >= ans.length)
            return true;

        // If this position is already occupied by the second occurrence
        // of a previously placed number, move to the next position
        if (ans[idx] != 0)
            return Solve(n, idx + 1, used);

        // Try larger numbers first to get the lexicographically largest sequence
        for (int num = n; num >= 1; num--) {

            // Skip the number if it has already been placed
            if (used[num - 1])
                continue;

            used[num - 1] = true;  // Mark this number as used
            ans[idx] = num;        // Place first occurrence at current index

            if (num == 1) {
                // 1 occurs only once, so directly move to the next position
                if (Solve(n, idx + 1, used))
                    return true;
            } else {
                // Second occurrence must be exactly 'num' positions away
                int j = num + idx;
                // Check that the second position is inside the array
                // and has not already been occupied
                if (j < ans.length && ans[j] == 0) {
                    ans[j] = num;  // Place the second occurrence
                    // Continue constructing the sequence
                    if (Solve(n, idx + 1, used))
                        return true;
                    // Backtrack: remove the second occurrence
                    ans[j] = 0;
                }
            }

            // Backtrack: remove the first occurrence
            ans[idx] = 0;
            used[num - 1] = false;
        }

        // No number can produce a valid sequence from this position
        return false;
    }

    public int[] constructDistancedSequence(int n) {

        // Required sequence length is 2*n - 1
        ans = new int[2 * n - 1];

        // used[i] tells whether number i+1 has already been placed
        boolean[] used = new boolean[n];

        // Start constructing from index 0
        Solve(n, 0, used);

        return ans;
    }
}
public class LargestLexicographicValidSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Enter value of n:");
        int n=sc.nextInt();

        Solution obj = new Solution();
        int[] ans = obj.constructDistancedSequence(n);
        for(int num : ans){
            System.out.println(num);
        }
    }
}
