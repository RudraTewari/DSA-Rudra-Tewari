import java.util.ArrayDeque;
import java.util.Deque;

class ValidParentheses{
    public boolean isValid(String s)
    {
        s=s.trim();
        Deque <Character> st = new ArrayDeque<>();

        for(int i =0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch=='('||ch=='{'||ch=='[')
            {
                st.push(ch);
            }
            else{
                if(st.isEmpty()) return false;

                char top=st.pop();

                if(ch==')' && top != '('||ch=='}' && top != '{'||ch==']' && top != '[')
                    return false;
            }
        }
        return true;
    }
    public static void main(String[] args)
    {
        String str = "{[])}";
        ValidParentheses obj = new ValidParentheses();
        boolean ans = obj.isValid(str);
        System.out.println(ans);
    }
}