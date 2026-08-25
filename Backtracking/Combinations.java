package Backtracking;

import java.util.ArrayList;
import java.util.List;
/*
    Approach:

    1. We need to generate all combinations of size `count` using numbers from
    `ele` to `n`.

    2. At every number, we have two choices: TAKE the current element or SKIP it.

    3. TAKE the current element:

    combination.add(ele);
    find(n, ele + 1, count - 1, combination);

    Reason:
    We include the current element in our combination, move to the next number,
    and decrease `count` because one required element has been selected.

    4. Backtrack after exploring the TAKE choice:

    combination.remove(combination.size() - 1);

    Reason:
    The same combination list is used by all recursive calls, so we remove the
    last added element to restore the previous state before exploring the
    SKIP choice.

    5. SKIP the current element:

    find(n, ele + 1, count, combination);

    Reason:
    We do not include the current element, so `count` remains unchanged.
    We simply move to the next number to explore other possible combinations.

    6. Base case when count becomes 0:

    if (count == 0) {
        ans.add(new ArrayList<>(combination));
        return;
    }

    Reason:
    We have successfully selected the required number of elements, so the
    current combination is complete. We store a copy because `combination`
    will continue to change during backtracking.

    7. If the current element becomes greater than `n`:

    if (ele > n) return;

    Reason:
    No more numbers are available to choose from, so we stop exploring
    the current recursive path.

    Overall, this recursion explores every possible TAKE/SKIP decision and
    generates all unique combinations of the required size.
*/
public class Combinations {
    static List<List<Integer>> ans;

    public static void find(int n, int ele,int count, List<Integer> combination){
        
        if(count==0){
            ans.add(new ArrayList<>(combination));
            return;
        }
        if(ele>n) return;
        combination.add(ele);
        find(n, ele+1,count-1, combination);
        //Backtrack
        combination.remove(combination.size()-1);
        find(n, ele+1, count, combination);
    }
    public static void main(String[] args) {
        int n = 4;
        int k=2;
        
        ans= new ArrayList<>();
        find(n, 1, k, new ArrayList<>());
        System.out.println(ans);
    }
}
