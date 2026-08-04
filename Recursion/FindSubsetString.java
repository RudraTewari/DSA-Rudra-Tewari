import java.util.ArrayList;

public class FindSubsetString {
    // public static void findSubset(String processed,String unProcessed){
    //     if(unProcessed.isEmpty()){
    //         System.out.println(processed);
    //         return;
    //     }
    //     char ch = unProcessed.charAt(0);

    //     findSubset(processed+ch, unProcessed.substring(1));
    //     findSubset(processed, unProcessed.substring(1));
    // }
    public static ArrayList<String> findSubset(String processed,String unProcessed){
        if(unProcessed.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(processed);
            return list;
        }
        
        char ch = unProcessed.charAt(0);
        ArrayList<String> left = findSubset(processed+ch, unProcessed.substring(1));
        ArrayList<String> right = findSubset(processed, unProcessed.substring(1));
        left.addAll(right);
        return left;
    }

    public static void main(String[] args) {
        String str = "abc";
        ArrayList<String> ans = findSubset("",str);
        for(String val : ans){
            System.out.println(val);
        }
    }
}
