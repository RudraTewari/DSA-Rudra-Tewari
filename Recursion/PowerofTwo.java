/**
 * Checks whether a given number is a power of 2 using recursion.
 *
 * Approach:
 * 1. Base Case:
 *    - If 2^x becomes greater than the given number, the number is
 *      not a power of 2, so return false.
 *    - If 2^x equals the given number, the number is a power of 2,
 *      so return true.
 *
 * 2. Recursive Case:
 *    - Increment the exponent (x) by 1.
 *    - Recursively compare the next power of 2 with the given number
 *      until a match is found or the power exceeds the number.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(log n) due to the recursive call stack.
 */
public class PowerofTwo{
    public static boolean powerOfTwo(int n, int x){
        if(n<Math.pow(2,x)) return false;
        if(n==Math.pow(2,x)) return true;

        return powerOfTwo(n,x+1);
    }
    public static void main(String[] args){
        int n=10;
        boolean ans = powerOfTwo(n,0);
        System.out.println(ans);
    }
}