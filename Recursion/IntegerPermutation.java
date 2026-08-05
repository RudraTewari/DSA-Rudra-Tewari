import java.util.ArrayList;
import java.util.Arrays;

public class IntegerPermutation {

    
    static void findPermutations(ArrayList<Integer> processed,int[] unProcessed){
        if(unProcessed.length==0){
            System.out.println(processed);
            return;
        }

        int current = unProcessed[0];
        int[] rem = Arrays.copyOfRange(unProcessed, 1, unProcessed.length);
        for (int index = 0; index <= processed.size(); index++) {
            processed.add(index,current);
            findPermutations(processed, rem);
            processed.remove(index);
        }
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3};

        findPermutations(new ArrayList<>(),arr);
        // int ans = findPermutationsSequence(new ArrayList<>(),arr,3);
        // System.out.println(ans);
    }
}
