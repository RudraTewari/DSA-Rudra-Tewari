package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationII {
    static void findPermutationswithDuplicate(List<Integer> processed,int[] unProcessed, boolean[] used, Set<List<Integer>> ans){
        if(unProcessed.length==processed.size()){
            ans.add(new ArrayList<>(processed));
            return;
        }
        for (int idx = 0; idx < used.length; idx++) {
            if(!used[idx]){
                processed.add(unProcessed[idx]);
                used[idx]=true;
                findPermutationswithDuplicate(processed, unProcessed, used, ans);
                processed.remove(processed.size()-1);
                used[idx]=false;
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {1,1,2};
        boolean[] used = {false,false,false};
        Set<List<Integer>> ans = new HashSet<>();
        findPermutationswithDuplicate(new ArrayList<>(),arr,used, ans);
        System.out.println(ans);
    }
}
