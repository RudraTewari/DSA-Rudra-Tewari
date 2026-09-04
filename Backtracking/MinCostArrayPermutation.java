package Backtracking;

import java.util.ArrayList;
import java.util.List;


class Solution{
    static int result;
    public static void Solve(int[] nums, boolean[] visited, List<Integer> perm, int[] resultPerm, int score){
        if(perm.size() == nums.length){
            
            score += Math.abs(perm.get(perm.size()-1)-nums[perm.get(0)]);
            if(result > score){
                result = score;
                for(int i=0;i < perm.size();i++){
                    resultPerm[i]=perm.get(i);
                }
            }
            return;
        }

        for(int i=0; i<nums.length;i++){
            if(!visited[i]){
                visited[i]=true;
                perm.add(i);
                int s = perm.size();
                Solve(nums, visited, perm, resultPerm, score + Math.abs(perm.get(s-2)-nums[perm.get(s-1)]));
                visited[i]=false;
                perm.remove(perm.size()-1);
            }
        }
    }
    public int[] findPermutation(int[] nums){
        int n = nums.length;
        boolean[] visited = new boolean[n];

        visited[0]=true;
        List<Integer> perm = new ArrayList<>();
        perm.add(0);
        int[] resultPerm= new int[n];
        int score = 0;
        result=Integer.MAX_VALUE;
        Solve(nums, visited, perm, resultPerm, score);

        return resultPerm;
    }
}
public class MinCostArrayPermutation {
    public static void main(String[] args) {
        int[] nums={0,2,1};

        Solution obj = new Solution();
        int[] ans = obj.findPermutation(nums);
        for(int i : ans){
            System.out.println(i);
        }
    }
}
