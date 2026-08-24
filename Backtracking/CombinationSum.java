package Backtracking;

import java.util.ArrayList;
import java.util.List;

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