package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Approach:
We use Backtracking with a boolean[] used array to generate all possible
permutations, including when the input array contains duplicate elements.

    1. 'processed' stores the permutation currently being built.
    2. 'used[i]' tells us whether unProcessed[i] has already been used in
        the current permutation, preventing the same index from being chosen twice.

    3. For every recursive call, try every unused element:
        - Add unProcessed[idx] to processed.
        - Mark used[idx] = true.
        - Recursively generate the remaining positions.

    4. When processed.size() == unProcessed.length, one complete permutation
        is formed. We add a COPY of processed to the HashSet.

    5. Since the input may contain duplicate values, the same permutation can
        be generated through different recursive paths. The Set automatically
        stores only unique List<Integer> permutations and removes duplicates.

    6. After each recursive call, backtrack:
        - Remove the last element from processed.
        - Mark used[idx] = false.
        This allows the element to be used again in other permutation paths.

    Example for [1, 1, 2]:
    The recursion may generate:
    [1, 1, 2]
    [1, 2, 1]
    [1, 1, 2]  // Generated again using the second 1
    [1, 2, 1]  // Generated again
    [2, 1, 1]

    But the Set stores only:
    [1, 1, 2]
    [1, 2, 1]
    [2, 1, 1]

    Pattern:
    Choose -> Mark Used -> Explore -> Remove -> Mark Unused

    Note:
    new ArrayList<>(processed) is necessary to store an independent copy,
    because 'processed' keeps changing during backtracking.
*/
public class PermutationII {
    static void findPermutationswithDuplicate(List<Integer> processed,int[] unProcessed, boolean[] used, Set<List<Integer>> ans){
        if(unProcessed.length==processed.size()){
            ans.add(new ArrayList<>(processed));
            return;
        }
        for (int idx = 0; idx < used.length; idx++) {
            if( !used[idx]){
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
