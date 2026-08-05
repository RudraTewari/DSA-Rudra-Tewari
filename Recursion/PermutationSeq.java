import java.util.ArrayList;
/*
    Approach (Factorial Number System / Factoradic):

    1. Every position in the permutation can be determined without generating
    all permutations.
    - For n numbers, there are (n - 1)! permutations starting with each
        possible first digit.

    2. Store all numbers from 1 to n in a list.
    - This list represents the digits that are still available to use.

    3. Convert the given permutation number to 0-based indexing.
    - k = k - 1
    - This makes block calculations easier.

    4. At each recursive step:
    - Compute the index of the required digit:
            index = k / fact
    - Pick the number at this index and append it to the answer.
    - Remove the chosen number from the available list.

    5. Update values for the remaining positions:
    - k = k % fact
        (position inside the current factorial block)
    - fact = fact / remainingNumbers
        (new block size becomes (remainingCount - 1)!)

    6. Repeat the process until no numbers remain.

    Example:
    n = 4, k = 9
    Available = [1,2,3,4]
    Initial fact = 3! = 6

    k = 8 (0-based)
    index = 8 / 6 = 1  -> choose 2
    Remaining = [1,3,4]

    k = 8 % 6 = 2
    fact = 6 / 3 = 2

    index = 2 / 2 = 1  -> choose 3
    Remaining = [1,4]

    k = 2 % 2 = 0
    fact = 2 / 2 = 1

    index = 0 / 1 = 0  -> choose 1
    Remaining = [4]

    index = 0 / 1 = 0  -> choose 4

    Answer = "2314"

    Time Complexity:
    - O(n²)
    (Removing an element from an ArrayList takes O(n), and this is done n times.)

    Space Complexity:
    - O(n)
    (ArrayList stores remaining numbers and recursion depth is at most n.)
*/
public class PermutationSeq {
    static void findPermutation(ArrayList<Integer> numbers, int k, int fact, StringBuilder ans){
        if(numbers.isEmpty()){
            return;
        }
        int idx = k/fact;
        ans.append(numbers.get(idx));

        numbers.remove(idx);
        if(numbers.isEmpty()){
            return;
        }
        k%=fact;
        fact /= numbers.size();
        findPermutation(numbers,k,fact,ans);

    }
    public static String getPermutation(int n, int k) {
        ArrayList<Integer> numbers = new ArrayList<>();
        int fact=1;
        for(int i=1;i<n;i++){
            fact *= i;
            numbers.add(i);
        }
        numbers.add(n);
        
        StringBuilder ans = new StringBuilder();
        findPermutation(numbers,k-1,fact,ans);
        return ans.toString();
    }

    public static void main(String[] args) {
        int n=4,k=9;
        String ans = getPermutation(n,k);
        System.out.println(ans);
    }
}
