import java.util.ArrayList;
import java.util.List;
/*
Approach: Divide & Conquer + Recursion

1. The expression can be evaluated in multiple ways because operators
   (+, -, *) can be placed at different positions.

2. For every operator in the expression:
        - Treat it as the "main" operator.
        -     Divide the expression into:
            Left  = expression before the operator
            Right = expression after the operator

3. Recursively solve both parts:
        leftAns  = Solve(left expression)
        rightAns = Solve(right expression)

    Each side may itself have multiple possible results.

4. Combine every result from the left side with every result from
    the right side using the current operator.

        Example : "2*3-4*5"

        If '*' is chosen:
            Left  = "2"
            Right = "3-4*5"

        Solve both sides recursively and combine their results.

5. Important trick:
    We don't decide the order of operations directly.
    Instead, we try EVERY operator as the point where we split
    the expression.

6. Base Case:
    If no operator is found, the expression contains only a number.
    Convert it to an integer and return it as the only possible result.

7. Why nested loops?
    Left and right expressions can each produce multiple results.

        for every left result
            for every right result
                combine them

    This generates all possible results for the current split.

8. Example : "2-1-1"

    Possible splits:

        Split at first '-' : 2 - (1-1) = 2

        Split at second '-':
            (2-1) - 1 = 0

    Result = [2, 0]

Key Idea to Remember:
---------------------
"Try every operator as a partition point,
recursively find all possible results on both sides,
then combine every left result with every right result."

This is a classic Divide & Conquer pattern:
        Choose split -> Solve left -> Solve right -> Combine

Time Complexity:
    Exponential because every operator creates different
    partitions and multiple combinations of results.

Space Complexity:
    O(n) recursion depth, excluding the space required
    to store all possible results.
*/
public class DiffWaystoAddParentheses {
    static List<Integer> Solve(String exp){
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < exp.length(); i++) {
            if(exp.charAt(i)=='+'||exp.charAt(i)=='-'||exp.charAt(i)=='*'){
                List<Integer> leftAns = Solve(exp.substring(0,i));
                List<Integer> rightAns = Solve(exp.substring(i+1,exp.length()));
                for(int val : leftAns){
                    for(int num : rightAns){
                        if(exp.charAt(i)=='+')
                            result.add(val+num);
                        else if(exp.charAt(i)=='-')
                            result.add(val-num);
                        else if(exp.charAt(i)=='*')
                            result.add(val*num);
                    }
                }
            }
        }
        if(result.isEmpty())
                result.add(Integer.parseInt(exp));
        return result;
    }
    public static void main(String[] args) {
        String exp = "22*3-4*8";

        List<Integer> res = Solve(exp);
        for(int i:res){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    
}
