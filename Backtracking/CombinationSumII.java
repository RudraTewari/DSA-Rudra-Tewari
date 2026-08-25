package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Approach:

    1. First, sort the array:

    Arrays.sort(nums);

    Reason:
    Sorting places duplicate elements next to each other, making it easier
    to skip duplicates and avoid generating the same combination multiple times.

    2. At every index, we have two choices: TAKE the current element or SKIP it.

    3. TAKE the current element:

    combination.add(nums[idx]);
    find(nums, target - nums[idx], idx + 1, combination);

    Reason:
    We add nums[idx] to the current combination and reduce the target.
    We move to idx + 1 because every element can be used only once.

    4. Backtrack after exploring the TAKE choice:

    combination.remove(combination.size() - 1);

    Reason:
    The combination list is shared across recursive calls, so we remove the
    previously added element to restore its previous state before exploring
    the next possible choice.

    5. Before exploring the SKIP choice, skip all duplicate values:

    while (idx < nums.length - 1 && nums[idx] == nums[idx + 1]) {
        idx++;
    }

    Reason:
    If multiple consecutive elements have the same value, skipping them one
    by one would generate identical combinations. Moving past all duplicates
    ensures every unique combination is generated only once.

    6. Explore the SKIP choice:

    find(nums, target, idx + 1, combination);

    Reason:
    After deciding not to use the current value, we move to the next distinct
    value and continue searching for other possible combinations.

    7. Base case:

    if (idx == nums.length) {
        if (target == 0) {
            ans.add(new ArrayList<>(combination));
        }
        return;
    }

    When we reach the end of the array, we check whether the target has become
    0. If yes, the current combination is valid and is added to the answer.

    8. We store a new copy using:
    new ArrayList<>(combination)

    Reason:
    The original combination list keeps changing during backtracking, so a
    separate copy is needed to preserve the valid combination.
*/
public class CombinationSumII {
    static List<List<Integer>> ans;
    static void find(int[] nums, int target, int idx, List<Integer> combination){
        if(idx == nums.length){
            if(target==0){
                ans.add( new ArrayList<>(combination));
            }
            return;
        }
        if(nums[idx] <= target){
            combination.add(nums[idx]);
            find(nums, target-nums[idx], idx+1, combination);
            combination.remove(combination.size()-1);
        }
        while(idx<nums.length-1 && nums[idx]== nums[idx+1]){
            idx++;
        }
        find(nums, target, idx+1, combination);
    }

    public static void main(String[] args) {
        int[] nums={2,5,2,1,2};
        int target = 5;

        ans = new ArrayList<>();
        Arrays.sort(nums);
        find(nums, target, 0, new ArrayList<>());

        System.out.println(ans);
    }
}
