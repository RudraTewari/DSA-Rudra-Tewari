package Backtracking;

import java.util.ArrayList;
import java.util.List;


public class Permutation {

    static void findPermutations(List<Integer> processed,int[] unProcessed, boolean[] used){
        if(unProcessed.length==processed.size()){
            System.out.println(processed);
            return;
        }
        for (int idx = 0; idx < used.length; idx++) {
            if(/*used[idx]==false*/ !used[idx]){
                processed.add(unProcessed[idx]);
                used[idx]=true;
                findPermutations(processed, unProcessed, used);
                processed.remove(processed.size()-1);
                used[idx]=false;
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        boolean[] used = {false,false,false};
        findPermutations(new ArrayList<>(),arr,used);
        // int ans = findPermutationsSequence(new ArrayList<>(),arr,3);
        // System.out.println(ans);
    }
}
