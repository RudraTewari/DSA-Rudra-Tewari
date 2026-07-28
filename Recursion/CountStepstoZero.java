/**
 * Counts the minimum number of steps required to reduce a given number to 0.
 *
 * Approach:
 * 1. Base Case:
 *    - If the number becomes 0, return the total step count.
 *
 * 2. Recursive Case:
 *    - Check whether the number is even or odd using the bitwise AND operator.
 *    - If the number is even:
 *        • Divide it by 2.
 *    - Otherwise:
 *        • Subtract 1 from it.
 *    - Increment the step counter after performing the operation.
 *    - Recursively repeat the process until the number becomes 0.
 *
 * Time Complexity: O(log n) in the average case.
 * Space Complexity: O(log n) due to the recursive call stack.
 */
public class CountStepstoZero{
    public static int countSteps(int n,int count){
        if(n==0){
            return count;
        }
        if((n&1)==0){
            n=n/2;            
        }else{
            n=n-1;
        }
        count++;
        return countSteps(n,count);
    }
    public static void main(String[] args){
        int n=14;
        int ans = countSteps(n,0);
        System.out.println("Answer : "+ ans);
    }
}