import java.util.Deque;
import java.util.ArrayDeque;
/*
    1. We use a Deque to store indices of elements.
    - The deque will always store indices in decreasing order of values.
    - This ensures the front of the deque always has the maximum element of the window.

    2. First, process the first window (0 → k-1):
    - For each element:
        a) Remove all indices from the back whose values are smaller than current element
            (they can never be maximum in future windows).
        b) Add current index to the back of deque.
    - After processing, the front of deque contains index of maximum element.
    - Store it in result.

    3. Now process remaining windows:
    For each new index i (start of window shifts from i-1 → i):

    a) Remove elements from front if they are out of the current window
        (i.e., index <= i-1).

    b) Remove all elements from back whose values are smaller than the new element
        (nums[i + k - 1]), because they are useless now.

    c) Add the new element index (i + k - 1) to the deque.

    d) The front of deque always contains the maximum element for current window.
        Store it in result.

    4. Return the result array.

    Time Complexity: O(n)
    - Each element is added and removed at most once.

    Space Complexity: O(k)
    - Deque stores at most k elements.
*/
class SlidingWindowMaximum{
    public int[] maxSlidingWindow(int[] nums,int k)
    {
        Deque<Integer> deque = new ArrayDeque<>();
        int n= nums.length;
        int[] res = new int[n-k+1];

        for(int i=0;i<k;i++)
        {
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i])
            {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        res[0] = nums[deque.peekFirst()];

        for(int idx=1;idx<n-k+1;idx++)
        {
            if(!deque.isEmpty() && deque.peekFirst() <= (idx-1))
            {
                deque.removeFirst();
            }            
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[idx+k-1])
            {
                deque.removeLast();
            }
            deque.addLast(idx+k-1);
            res[idx] = nums[deque.peekFirst()];
        }
        return res;
    }
    public static void main(String[] args)
    {
        int[] nums={1,3,-1,-3,5,3,6,7};
        int k=3;
        SlidingWindowMaximum obj = new SlidingWindowMaximum();
        int[] ans = obj.maxSlidingWindow(nums,k);
        for(int val : ans)
        {
            System.out.print(val+" ");
        }
        System.out.println();
    }
}