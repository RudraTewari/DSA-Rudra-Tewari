import java.util.ArrayList;
import java.util.List;

public class LetterCombination {
    public static void pad(String p, String up, List<String> ans){
        if(up.isEmpty()){
            ans.add(p);
            return;
        }

        int digit = up.charAt(0)-'0';
        int start=0,end=0;

        if(digit<=6){
            start=(digit-2)*3;
            end =(digit-1)*3;
        }else if(digit==7){
            start = 15;
            end=19;
        }else if(digit == 8){
            start = 19;
            end=22;
        }else{
            start = 22;
            end=26;
        }

        for(int i=start; i<end;i++){
            char ch = (char)('a'+i);

            pad(p+ch, up.substring(1),ans);
        }
    }
    public static void main(String[] args) {
        String p ="";
        String up="1234";
        List<String> ans = new ArrayList<>();
        pad(p,up,ans);
        ans.forEach(val->{
            System.out.print(val+" ");
        });
        System.out.println();
    }
    
}
