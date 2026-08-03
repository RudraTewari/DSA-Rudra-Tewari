

public class SkipCharacter {
    public static String skipCharacter(String str, char ch,int idx){
        if(idx == str.length()){
            return "";
        }
        if(str.charAt(idx)==ch){
            return skipCharacter(str,ch,idx+1);
        }
        String ans = skipCharacter(str,ch,idx+1);
        return str.charAt(idx)+ans;
    }
    public static void main(String[] args) {
        char ch = 'b';
        String str = "baccad";
        int idx = 0;
        String ans = skipCharacter(str,ch,idx);
        System.out.println(ans);
    }
}
