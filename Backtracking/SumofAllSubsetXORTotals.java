package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class SumofAllSubsetXORTotals {
    public static int Solve(List<Integer> p,int[] nums,int idx,int sum){
        if (idx==nums.length) {
            
            int val=0;
            for(int i=0;i<p.size();i++){
                val ^= (p.get(i));
            }
            return sum+=val;
            
        }
        p.add(nums[idx]);
        int leftSum = Solve(p, nums, idx+1, sum);
        p.remove(p.size()-1);
        int rightSum = Solve(p, nums, idx+1, sum);
        return leftSum+rightSum;
    }
    public static int findSubsetsXOR(int[] nums){
        return Solve(new ArrayList<>(), nums, 0, 0);
    }
    public static void main(String[] args){
        int[] nums = {5,1,6};
        int ans = findSubsetsXOR(nums);
        System.out.println("Answer :"+ans);
    }
}
