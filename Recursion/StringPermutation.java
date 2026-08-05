import java.util.ArrayList;
import java.util.List;

public class StringPermutation {
    static void findPermutations(String p, String up,List<String> ans){
        if(up.isEmpty()){
            ans.add(p);
            return;
        }

        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String first = p.substring(0,i);
            String second = p.substring(i,p.length());
            findPermutations(first+ch+second, up.substring(1),ans);
        }
    }
    public static void main(String[] args) {
        List<String> ans = new ArrayList<>();
        findPermutations("","ABC",ans);
        ans.forEach(str -> System.out.println(str));
    }
}
