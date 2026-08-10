import java.util.ArrayDeque;
import java.util.Deque;

class BaseballGame{
    public int calPoints(String[] operations)
    {
        Deque <Integer> ans = new ArrayDeque<>();
        for(String val : operations)
        {
            if(val == "+")
            {
                int a = ans.pop();
                int sum = a + ans.peek();
                ans.push(a);
                ans.push(sum);
            }
            else if(val.equals("C"))
            {
                ans.pop();
            }
            else if(val.equals("D"))
            {
                ans.push( ans.peek()*2);
            }
            else // the integer case 
            {
                ans.push(Integer.parseInt(val)); // Because the number is a string type so converting it to integer
            }
        }
        int result=0;
        for(int val : ans)
        {
            result+=val;
        }
        return result;
    }
    public static void main(String[] args)
    {
        String[] operations = {"5","-2","4","C","D","9","+","+"};
        BaseballGame obj = new BaseballGame();
        int ans = obj.calPoints(operations);
        System.out.println(ans);
    }
}