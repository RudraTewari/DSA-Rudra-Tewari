package Backtracking;

import java.util.HashSet;
import java.util.Set;
/*
 * ================================================================
 * APPROACH: BACKTRACKING + HASHSET + PRUNING
 * ================================================================
 *
 * The goal is to split the string into the maximum number of
 * substrings such that every substring is unique.
 *
 *
 * INTUITION:
 * ---------------------------------------------------------------
 *
 * At every index, we have multiple choices for the next substring.
 *
 * For example, for:
 *
 *              "abab"
 *
 * Starting at index 0, we can choose:
 *
 *              "a"
 *              "ab"
 *              "aba"
 *              "abab"
 *
 * After choosing one substring, we recursively solve the remaining
 * part of the string.
 *
 * Therefore, we use a loop:
 *
 *      for (int j = idx; j < str.length(); j++)
 *
 * to generate every possible substring starting from idx.
 *
 *
 * HASHSET:
 * ---------------------------------------------------------------
 *
 * The HashSet 'check' stores the substrings that have already been
 * selected in the CURRENT partition.
 *
 * For example:
 *
 *              a | b | ab
 *
 *      check = {"a", "b", "ab"}
 *
 * Before selecting a substring, we check:
 *
 *      !check.contains(sub)
 *
 * This guarantees that no substring is used more than once.
 *
 *
 * BACKTRACKING:
 * ---------------------------------------------------------------
 *
 * For every valid substring, we perform three steps:
 *
 *      1. CHOOSE
 *             check.add(sub)
 *
 *      2. EXPLORE
 *             Solve(check, j + 1, str, currCount + 1)
 *
 *      3. UNCHOOSE
 *             check.remove(sub)
 *
 * The remove operation is extremely important.
 *
 * Suppose we selected:
 *
 *              "a"
 *
 * and explored every possibility after choosing "a".
 *
 * We now need to remove "a" so that we can try another possibility
 * from the same position, such as:
 *
 *              "ab"
 *
 * Without removing the substring, choices from one branch would
 * affect other branches.
 *
 *
 * RECURSIVE INDEX:
 * ---------------------------------------------------------------
 *
 * If the selected substring is:
 *
 *              str[idx ... j]
 *
 * then the next unused character is at:
 *
 *              j + 1
 *
 * Therefore, the recursive call starts from:
 *
 *              Solve(..., j + 1, ...)
 *
 *
 * CURRENT COUNT VS MAXIMUM COUNT:
 * ---------------------------------------------------------------
 *
 * currCount represents the number of unique substrings selected
 * in the CURRENT recursion path.
 *
 * maxSplit represents the best answer found among ALL paths.
 *
 * When we reach the end of the string:
 *
 *              idx >= str.length()
 *
 * the current partition is complete, so we update:
 *
 *              maxSplit = Math.max(maxSplit, currCount)
 *
 *
 * PRUNING / BRANCH AND BOUND:
 * ---------------------------------------------------------------
 *
 * We can optimize the recursion by avoiding branches that cannot
 * possibly produce a better answer.
 *
 * The remaining number of characters is:
 *
 *              str.length() - idx
 *
 * Since every substring must contain at least one character, the
 * absolute maximum number of additional substrings we can create
 * is equal to the number of remaining characters.
 *
 * Therefore, the BEST possible answer from the current branch is:
 *
 *              currCount + (str.length() - idx)
 *
 * Example:
 *
 *              currCount = 4
 *              remaining characters = 3
 *
 * The best possible result from this branch is:
 *
 *              4 + 3 = 7
 *
 * If maxSplit is already 7, this branch cannot improve the answer.
 *
 * So we can safely stop exploring it:
 *
 *              if (currCount + (str.length() - idx) <= maxSplit)
 *                  return;
 *
 * This is called PRUNING or BRANCH AND BOUND.
 *
 *
 * WHY IS THE PRUNING SAFE?
 * ---------------------------------------------------------------
 *
 * The calculation assumes the most optimistic situation:
 *
 *              every remaining character becomes a separate
 *              unique substring.
 *
 * In reality, the answer can only be equal to or smaller than this.
 *
 * Therefore, if even this optimistic upper bound cannot beat
 * maxSplit, there is no reason to explore the branch.
 *
 *
 * OVERALL RECURSION:
 * ---------------------------------------------------------------
 *
 *                  Start at idx
 *                       |
 *             Try every substring
 *                       |
 *              Is substring unique?
 *                  /          \
 *                NO            YES
 *                |              |
 *              skip           add to Set
 *                               |
 *                         recursive call
 *                               |
 *                          remove from Set
 *                               |
 *                         try next choice
 *
 *
 * IMPORTANT CONCEPTS USED:
 * ---------------------------------------------------------------
 *
 * 1. Recursion
 *       Solve the remaining part of the string.
 *
 * 2. Backtracking
 *       Choose -> Explore -> Unchoose.
 *
 * 3. HashSet
 *       Ensures that selected substrings are unique.
 *
 * 4. Global maximum
 *       maxSplit stores the best partition found so far.
 *
 * 5. Pruning
 *       Avoid branches that mathematically cannot improve the
 *       current maximum.
 *
 * 6. Branch and Bound
 *       Use an optimistic upper bound to decide whether a branch
 *       is worth exploring.
 */
class Solution{
    private static int maxSplit;
    public static void Solve(Set<String> check, int idx, String str, int currCount){

        if(currCount+(str.length()-idx) <= maxSplit) return;

        if(idx>=str.length()){
            maxSplit=Math.max(maxSplit, currCount);
            return;
        }
        for(int j=idx; j<str.length();j++){
            String sub = str.substring(idx, j+1);
            if(!check.contains(sub)){
                check.add(sub);
                Solve(check, j+1, str, currCount+1);
                check.remove(sub);
            }
        }
    }
    public int maxUniqueSplit(String s){
        maxSplit=Integer.MIN_VALUE;
        Set<String> check = new HashSet<>();
        Solve(check, 0, s,0);
        return maxSplit;
    }
}
public class MaxUniqueString {
    
    public static void main(String[] args) {
        String s = "ababccc";
        Solution obj = new Solution();
        int ans = obj.maxUniqueSplit(s);
        System.out.println(ans);
    }
}
