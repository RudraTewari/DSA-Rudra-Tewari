package Backtracking;

import java.util.ArrayList;
import java.util.List;
/*
Approach:

    1. At each index, we have two choices: TAKE the current number or SKIP it.

    2. If we take nums[idx], we add it to the current combination:
    
    combination.add(nums[idx]);

    We stay at the same index because the same number can be used multiple times:
    
    findCombinationSum(ans, nums, idx, target - nums[idx], combination);

    3. After returning from recursion, we remove the last element:

    combination.remove(combination.size() - 1);

    Reason: This is the backtracking step. It restores the combination to its
    previous state before exploring other possibilities.

    4. If we skip the current number, we move to the next index:

    findCombinationSum(ans, nums, idx + 1, target, combination);

    Reason: Moving forward ensures we explore combinations using the remaining
    numbers without generating different orders of the same combination.

    5. When idx reaches nums.length, no more numbers are available. If target == 0,
    the current combination is valid, so we store a copy of it:

    if (target == 0) {
        ans.add(new ArrayList<>(combination));
    }

    Reason: A new ArrayList is created because the combination list keeps changing
    during backtracking, and we need to preserve the current valid combination.
*/
public class CombinationSum{
        public static void findCombinationSum(List<List<Integer>>ans, int[] nums, int idx, int target, List<Integer> combination){
            if(idx == nums.length){
                if(target==0){
                    ans.add(new ArrayList<>(combination));
                }
                return;
            }
            if(nums[idx] <= target){
                combination.add(nums[idx]);
                findCombinationSum(ans,nums,idx,target-nums[idx], combination);

                combination.remove(combination.size()-1);
            }
            findCombinationSum(ans, nums, idx+1, target, combination);
        }
        
    public static void main(String[] args) {
        int[] arr = {2,3,6,7};
        int target=7;

        List<List<Integer>> ans = new ArrayList<>();
        
        findCombinationSum(ans, arr, 0, target, new ArrayList<>());
        System.out.println(ans);
    }
}