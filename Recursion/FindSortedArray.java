/**
 * Checks whether an array is sorted in ascending order using recursion.
 *
 * Approach:
 * 1. Base Case:
 *    - If the recursion reaches index 0, all previous elements have been
 *      verified, so return true.
 *
 * 2. Recursive Case:
 *    - Compare the current element with its previous element.
 *    - If the current element is smaller than the previous one,
 *      the array is not sorted, so return false.
 *    - Otherwise, recursively check the remaining portion of the array
 *      by moving to the previous index.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) due to the recursive call stack.
 */
public class FindSortedArray{
    public static boolean checkSortedArray(int[] arr,int index){
        if(index == 0){
            return true;
        }
        if(arr[index]<arr[index-1]){
            return false;
        }       
        return checkSortedArray(arr,index-1);
    }
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};

        boolean ans = checkSortedArray(arr,arr.length-1);
        System.out.println(ans);
    }
}