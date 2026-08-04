import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
// import java.util.HashSet;
import java.util.List;


public class SubsetsII {
    // public static void findSubsets(ArrayList<Integer>processed,int[] unProcessed, int idx,HashSet<ArrayList<Integer>> ans){
    //     if(idx == unProcessed.length){
    //         ans.add(new ArrayList<>(processed));
    //         return;
    //     }
    //     processed.add(unProcessed[idx]);
    //     findSubsets(processed, unProcessed, idx+1, ans);

    //     processed.remove(processed.size()-1);
    //     findSubsets(processed, unProcessed, idx+1, ans);
    // }
    // public static void main(String[] args) {
    //     int[] nums={2,2,2};
    //     HashSet<ArrayList<Integer>> ans = new HashSet<>();
    //     findSubsets(new ArrayList<>(),nums,0,ans);
    //     System.out.println(ans);
    // }

// ----------------------APPROACH------------------------------------
/*

    1. Sort the array so that duplicate elements become adjacent.
    This allows us to skip all duplicates together.

    Arrays.sort(nums);

    ------------------------------------------------------------

    2. At every index, make the INCLUDE choice by adding the
    current element to the subset.

    processed.add(unProcessed[idx]);

    ------------------------------------------------------------

    3. Recurse after including the current element.

    findSubsets(processed, unProcessed, idx + 1, ans);

    ------------------------------------------------------------

    4. Backtrack by removing the last added element so that
    the subset returns to its previous state before exploring
    another choice.

    processed.remove(processed.size() - 1);

    ------------------------------------------------------------

    5. Before taking the EXCLUDE choice, skip all consecutive
    duplicate elements.

    while (idx < unProcessed.length - 1 &&
            unProcessed[idx] == unProcessed[idx + 1]) {
        idx++;
    }

    Example:
    nums = [1, 2, 2, 2, 3]

    If we exclude the first 2, we also skip the remaining 2's.
    Otherwise, excluding each 2 separately would generate the
    same subsets multiple times.

    ------------------------------------------------------------

    6. Take the EXCLUDE choice after skipping duplicates.

    findSubsets(processed, unProcessed, idx + 1, ans);

    ------------------------------------------------------------

    7. Base Case:
    When all elements have been processed, store a copy of the
    current subset.

    if (idx == unProcessed.length) {
        ans.add(new ArrayList<>(processed));
        return;
    }

    ------------------------------------------------------------

    Time Complexity: O(2^n)
    - Each unique subset is generated once.
    - Duplicate branches are skipped.

    Space Complexity: O(n)
    - O(n) recursion stack.
    - O(n) extra space for the current subset.
    - Output space is not included.
*/
    public static void findSubsets(ArrayList<Integer>processed,int[] unProcessed, int idx,List<List<Integer>> ans){
        if(idx == unProcessed.length){
            ans.add(new ArrayList<>(processed));
            return;
        }
        processed.add(unProcessed[idx]);
        findSubsets(processed, unProcessed, idx+1, ans);

        processed.remove(processed.size()-1);

        while(idx<unProcessed.length-1 && unProcessed[idx]==unProcessed[idx+1])
            idx++;
        findSubsets(processed, unProcessed, idx+1, ans);
    }
    public static void main(String[] args) {
        int[] nums={2,2,2};
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        findSubsets(new ArrayList<>(),nums,0,ans);
        System.out.println(ans);
    }
}
