package Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Approach:
We use Backtracking with a boolean[] used array to generate all permutations.

1. 'processed' stores the permutation currently being built.
2. 'used[i]' tells us whether unProcessed[i] has already been included
    in the current permutation. This prevents using the same element twice.

3. For every recursive call, try every element:
    - If used[idx] is false, choose unProcessed[idx].
    - Add it to processed and mark used[idx] = true.
    - Recursively generate the remaining positions.

4. When processed.size() becomes equal to unProcessed.length,
    we have formed one complete permutation, so print/store it.

5. After returning from recursion, backtrack:
    - Remove the last chosen element from processed.
    - Mark used[idx] = false.
    This makes the element available again for other permutation paths.

Example for [1, 2, 3]:
[] -> choose 1 -> [1]
                -> choose 2 -> [1,2]
                            -> choose 3 -> [1,2,3]
                            -> backtrack
                -> choose 3 -> [1,3]
                            -> choose 2 -> [1,3,2]

Pattern:
Choose -> Mark Used -> Explore -> Remove -> Mark Unused
*/
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
