package Backtracking;

import java.util.ArrayList;
import java.util.List;
/*
Approach: Backtracking for Palindrome Partitioning

1. We need to divide the given string into multiple substrings such that
   every substring in the partition is a palindrome.

2. At every recursive call, `up` represents the remaining unprocessed
   part of the string, while `partition` stores the palindrome substrings
   chosen so far.

3. Generate every possible prefix of the remaining string:

       for (int i = 0; i < up.length(); i++) {
           String part = up.substring(0, i + 1);
       }

   For example, if up = "aab", possible prefixes are:

       "a"
       "aa"
       "aab"

4. Check whether the current prefix is a palindrome:

        if (isPalindrome(part)) {
            ...
        }

    Only palindrome substrings are allowed to be included in the current
    partition.

5. If the substring is a palindrome, choose it and add it to the current
    partition:

        partition.add(part);

    Then recursively process the remaining part of the string:

        partitioning(up.substring(i + 1), partition, ans);

6. After returning from recursion, remove the last added substring:

        partition.remove(partition.size() - 1);

    This is the backtracking step. It restores the partition to its previous
    state so that we can try another possible substring.

7. Base Case:
    When the remaining string becomes empty, it means the entire original
    string has been successfully divided into palindrome substrings.

        if (up.length() == 0) {
            ans.add(new ArrayList<>(partition));
            return;
        }

    We add a COPY of `partition` because the same list is modified later
    during backtracking.

Backtracking Pattern:

       partition.add(part);                         // Choose
       partitioning(remainingString, partition, ans); // Explore
       partition.remove(partition.size() - 1);     // Backtrack

Example: "aab"

                        "aab"
                    /      \
                "a"       "aa"
                |          |
                "ab"        "b"
                |          |
                "a" ❌       "b" ✓
                            |
                        ["aa", "b"]

Valid Partitions:

        ["a", "a", "b"]
        ["aa", "b"]

Time Complexity:
    O(N * 2^N) approximately

    - There can be up to 2^(N-1) possible partitions.
    - Palindrome checking can take O(N).

Space Complexity:
    O(N) auxiliary recursion space

    - The recursion depth and current partition can contain at most N
        characters/substrings in the worst case.
    - The answer list is not included in auxiliary space complexity.
*/
public class PalindromePartitioning {
    static boolean isPalindrome(String p){
        String p2 = new StringBuilder(p).reverse().toString();
        return p.equals(p2);
    }
    static void partitioning(String up, List<String> partition,List<List<String>> ans){
        if(up.length()==0){
            ans.add(new ArrayList<>(partition));
            return;
        }
        for(int i=0;i<up.length();i++){
            String part = up.substring(0,i+1);
            if(isPalindrome(part)){
                partition.add(part);
                partitioning(up.substring(i+1), partition,ans);
                partition.remove(partition.size()-1);
            }
        }
    }
    public static void main(String[] args) {
        String s = "aab";

        List<List<String>> ans = new ArrayList<>();
        partitioning(s, new ArrayList<>(),ans);

        System.out.println(ans);
    }
}
