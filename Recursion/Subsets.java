import java.util.List;
import java.util.ArrayList;

public class Subsets {
    public static List<List<Integer>> findSubsets(ArrayList<Integer> processed,int[] unProcessed,int idx){
        if(idx == unProcessed.length){
            List<List<Integer>> list = new ArrayList<>();
            list.add(new ArrayList<>(processed));
            return list;
        }
        processed.add(unProcessed[idx]);
        List<List<Integer>> left = findSubsets(processed, unProcessed, idx+1);

        //Backtrack
        processed.remove(processed.size()-1);

        List<List<Integer>> right = findSubsets(processed, unProcessed, idx+1);

        left.addAll(right);
        return left;
        
    }
    public static void main(String[] args) {
        int[] nums={1,2,3};

        List<List<Integer>> ans = findSubsets(new ArrayList<>(),nums,0);
        System.out.println(ans);
    }
}
