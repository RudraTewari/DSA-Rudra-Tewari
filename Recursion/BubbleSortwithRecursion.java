/*
    Approach:
    1. Use recursion to simulate the two nested loops of Bubble Sort.
    2. The parameter 'r' represents the last index of the current unsorted portion
        of the array.
    3. The parameter 'c' represents the current index being compared in the current pass.
    4. Compare adjacent elements arr[c] and arr[c + 1].
    5. If the left element is smaller than the right element, swap them.
        - This implementation sorts the array in descending order because
            larger elements are moved toward the beginning.
    6. Continue increasing 'c' recursively until the end of the current pass.
    7. When one pass is complete (c == r), recursively start the next pass by
        decreasing 'r' and resetting 'c' to 0.
    8. Stop the recursion when r becomes 0, meaning the entire array is sorted.
    
    Logic:
    - 'r' behaves like the outer loop of iterative Bubble Sort.
    - 'c' behaves like the inner loop.
    - Each recursive call either:
        • compares the next adjacent pair (c + 1), or
        • starts the next pass (r - 1).
    - Since the largest values are repeatedly swapped toward the front,
        the array is sorted in descending order.
    - Time Complexity: O(n²)
    - Space Complexity: O(n) due to recursive call stack.
*/
class BubbleSortwithRecursion{
    
    public static void bubbleSort(int[] arr, int r,int c){
        if(r==0){
            return;
        }
        if(c<r){
            if(arr[c]<arr[c+1]){
                //swap
                int temp=arr[c];
                arr[c]=arr[c+1];
                arr[c+1]=temp;
            }
            bubbleSort(arr,r,c+1);
        }else{
            bubbleSort(arr,r-1,0);
        }
    }
    public static void main(String[] args){
        int[] arr = {8,9,3,1};
        int r=arr.length-1;
        int c=0;
        bubbleSort(arr,r,c);
        for(int num : arr){
            System.out.print(num + " ");
        }
        System.out.println();
    }
}