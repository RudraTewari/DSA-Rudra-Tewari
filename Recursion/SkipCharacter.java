

public class SkipCharacter {
    public static void skipCharacter(String processed, String unProcessed,char ch){
        if(unProcessed.isEmpty()){
            System.out.print(processed);
            System.out.println();
            return;
        }
        char c = unProcessed.charAt(0);

        if(c == ch){
            skipCharacter(processed, unProcessed.substring(1), ch);
        }else{
            skipCharacter(processed+c, unProcessed.substring(1), ch);
        }

    }
    public static void main(String[] args) {
        char ch = 'a';
        String str = "baccad";
        skipCharacter("",str,ch);
    }
}
