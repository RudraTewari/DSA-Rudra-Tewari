package Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
    We need to count the number of subsets whose bitwise OR is equal to
    the maximum possible OR among all subsets.

    For every element, we have exactly two choices:

    1. Include the current element in the subset.
    2. Exclude the current element from the subset.

    This gives us a binary recursion tree containing all possible subsets.

    At every recursive call, `idx` tells us which element we are currently
    considering, while `p` stores the elements selected so far.



    p.add(nums[idx]);
    Solve(nums, p, idx + 1);

    p.remove(p.size() - 1);

    Solve(nums, p, idx + 1);



    The first recursive call includes `nums[idx]`.
    After returning from it, we remove the element to restore the previous
    state. This is called backtracking.

    The second recursive call excludes `nums[idx]`.

    When `idx` reaches the end of the array, we have constructed one complete
    subset. We calculate the bitwise OR of all elements in that subset.



    if (idx >= nums.length) {
        int value = 0;

        for (Integer integer : p) {
            value |= integer;
        }



    Now compare the OR value of the current subset with `maxORValue`.

    If the current OR is greater than the previous maximum:

    Update `maxORValue`.
    Reset `maxCount` because this is a new maximum.
    The current subset should then be counted.

    If the current OR is equal to `maxORValue`, increment `maxCount`
    because another subset has produced the same maximum OR.



    if (maxORValue < value) {
        maxORValue = value;
        maxCount = 0;
    }

    if (maxORValue == value) {
        maxCount++;
    }



    Initially, `maxORValue` is set to Integer.MIN_VALUE so that the OR value
    of the first subset is guaranteed to become the initial maximum.

    `maxCount` keeps track of how many subsets produce the maximum OR value.

    Example:
    nums = [3, 1]

    Subsets and their OR values:

    []       -> 0
    [3]      -> 3
    [1]      -> 1
    [3,1]    -> 3

    Maximum OR = 3
    Number of subsets having OR = 3 -> 2

    Therefore, the answer is 2.

    The recursion explores all `2^n` possible subsets.
    For each subset, we may iterate through up to `n` elements to calculate
    its OR.

    Time Complexity: O(n * 2^n)
    Space Complexity: O(n) for the recursion stack and current subset.
*/

class Solution{
    static int maxORValue;
    static int maxCount;
    public static void Solve(int[] nums, List<Integer> p, int idx){
        
        if(idx >= nums.length){
            int value = 0;
            for (Integer integer : p) {
                value |= integer;
            }
            if(maxORValue < value){
                maxORValue=value;
                maxCount = 0;
            }
            if(maxORValue == value){
                maxCount += 1;
            }
            return;
        }

        p.add(nums[idx]);
        Solve(nums, p, idx+1);
        p.remove(p.size()-1);

        Solve(nums, p, idx+1);
    }
    public int countMaxOrSubsets(int[] nums){
        maxORValue=Integer.MIN_VALUE;
        maxCount=0;

        Solve(nums, new ArrayList<>(), 0);
        return maxCount;
    }
}
public class MaximumORSubsetCount{
    public static void main(String[] args){
        int[] nums = {3,2,1,5};

        Solution obj = new Solution();
        int ans = obj.countMaxOrSubsets(nums);

        System.out.println(ans);
    }
}