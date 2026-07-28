/**
 * Prints the Sum Triangle of an array using recursion.
 *
 * Approach:
 * 1. Base Case:
 *    - If the array contains only one element, print that element
 *      and return.
 *
 * 2. Recursive Case:
 *    - Create a new array of size (n - 1).
 *    - Store the sum of every pair of adjacent elements from the
 *      current array into the new array.
 *    - Recursively generate the Sum Triangle for the new array.
 *    - After the recursive call returns, print the current array.
 *      This prints the triangle from top to bottom due to recursion
 *      unwinding.
 *
 * Time Complexity: O(n²)
 * Space Complexity: O(n²) (includes recursive call stack and
 * arrays created at each level)
 */
public class SumOfTriangle{
    public static void sumofTriangle(int[] nums){
        if(nums.length==1){
            System.out.println(nums[0]);
            return;
        }
        int[] ans = new int[nums.length-1];
        for(int i=0;i<nums.length-1;i++){
            ans[i]=nums[i]+nums[i+1];
        }
        // System.out.println("Yes1");
        sumofTriangle(ans);
        // System.out.println("Yes2");
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        int[] a = {1,2,3,4,5};
        sumofTriangle(a);
    }
}