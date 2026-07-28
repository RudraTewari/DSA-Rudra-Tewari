/**
 * Finds the minimum and maximum elements in an array using recursion.
 *
 * Approach:
 * 1. Base Case:
 *    - When the recursion reaches index 0, initialize an array containing:
 *        • Minimum = arr[0]
 *        • Maximum = arr[1]
 *    - Return this array as the initial result.
 *
 * 2. Recursive Case:
 *    - Recursively find the minimum and maximum for elements from
 *      index 0 to (index - 1).
 *    - Compare the current element with the stored minimum:
 *        • If smaller, update the minimum.
 *    - Compare the current element with the stored maximum:
 *        • If larger, update the maximum.
 *    - Return the updated result array.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) due to the recursive call stack.
 */
public class FindMinMax{
    public static int[] findMinMax(int[] arr,int index){
        if(index==0){
            int[] base = {arr[0],arr[1]};
            return base;
        }
        // Recursive Call
        int[] res = findMinMax(arr,index-1);

        //  Min Element Finding & Updating
        if(arr[index] < res[0]) 
            res[0]=arr[index];

        // Max Element Finding & Updating
        if(arr[index] > res[1]) 
            res[1]=arr[index];

        return res;
    } 

    public static void main(String[] args){
        int[] arr ={1, 4, 3, -5, -4, 8, 6};

        int[] ans = findMinMax(arr,arr.length-1);
        System.out.println("Min : "+ ans[0] + " "+"Max : "+ ans[1]);
    }
}