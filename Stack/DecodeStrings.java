
import java.util.ArrayDeque;
import java.util.Deque;

class DecodeStrings{
    public String decodeString(String s)
    {
        Deque<String> stack = new ArrayDeque<>();
        String currString="";
        int currNum=0;
        for(int i=0;i<s.length();i++)
        {
            if(Character.isDigit(s.charAt(i)))
                currNum = currNum *10 + (s.charAt(i)-'0');
            else if(s.charAt(i) == '[')
            {
                stack.push(currString);
                stack.push(Integer.toString(currNum));
                currNum=0;
                currString="";
            }
            else if(s.charAt(i) ==']')
            {
                String num = stack.pop();
                String prevString = stack.pop();
                currString = prevString + currString.repeat(Integer.parseInt(num));
            }else{                
                currString += s.charAt(i);
            }
        }
        return currString;
    }
    public static void main(String[] args)
    {
        String s = "3[a2[bc]]";
        DecodeStrings obj = new DecodeStrings();
        String ans = obj.decodeString(s);
        System.out.println(ans);
    }
}