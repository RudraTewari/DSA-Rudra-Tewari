package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/*
    Approach: Backtracking + HashSet

    We need to find all subsequences of length at least 2 such that the elements
    are in non-decreasing order.

    We use a list 'p' to store the current subsequence and recursively decide
    whether to include or exclude every element.

    1. Store valid subsequences:
    At any point, if the current subsequence contains at least 2 elements,
    it is a valid answer:

        if (p.size() >= 2) {
            ans.add(new ArrayList<>(p));
        }

    We create a new ArrayList because 'p' is modified during backtracking.
    A HashSet is used to automatically remove duplicate subsequences caused
    by duplicate elements in the input array.

    2. Base Case:
    When we have processed all elements, there are no more choices left:

        if (idx == up.length) return;

    3. Include the current element:
    We can include up[idx] only if it maintains the non-decreasing order.

    If the subsequence is empty, we can always add the current element.
    Otherwise, the current element must be greater than or equal to the
    last selected element:

        if (p.isEmpty() || p.get(p.size() - 1) <= up[idx]) {

            p.add(up[idx]);                 // Choose
            Solve(p, up, idx + 1, ans);     // Explore

            p.remove(p.size() - 1);         // Backtrack
        }

    4. Exclude the current element:
    We can also skip the current element and continue with the remaining array:

        Solve(p, up, idx + 1, ans);

    By exploring both choices at every index, we generate all possible subsequences.
    The inclusion condition ensures that only non-decreasing subsequences are formed,
    and the HashSet removes duplicate subsequences.

    Example: nums = [4, 6, 7, 7]

    One recursion path:
        [] -> [4] -> [4, 6] -> [4, 6, 7]

    Another path may skip 6:
        [] -> [4] -> [4, 7]

    Because both 7s can create identical subsequences, the HashSet ensures that
    each valid subsequence appears only once in the final answer.
*/


public class NonDecreasingSubsequence {
    public static void Solve(List<Integer> p, int[] up, int idx,Set<List<Integer>> ans){
        
        if(p.size()>=2){
            ans.add(new ArrayList<>(p));
        }
        if(idx == up.length) return;

        
        if(p.isEmpty() || p.get(p.size()-1) <= up[idx]){
            p.add(up[idx]);
            Solve(p, up, idx+1, ans);
            p.remove(p.size()-1);
        }
        Solve(p, up, idx+1, ans);

    }
    public List<List<Integer>> findSubsequence(int[] nums){
        Set<List<Integer>> ans = new HashSet<>();
        Solve(new ArrayList<>(), nums, 0, ans);
        return new ArrayList<>(ans);
    }
    public static void main(String[] args) {
        int[] nums={4,6,7,7};
        NonDecreasingSubsequence obj = new NonDecreasingSubsequence();
        List<List<Integer>> ans = obj.findSubsequence(nums);

        System.out.println(ans);
    }
}
