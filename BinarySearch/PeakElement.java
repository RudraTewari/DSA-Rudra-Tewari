class PeakElement{
    /*
Goal: Find index of a peak element (element greater than neighbors)

Key property:
A peak always exists in the array.
We can locate it by checking the slope between nums[mid] and nums[mid+1].

Core idea:
Compare nums[mid] with nums[mid+1] to know which side contains a peak.

------------------------------------------------

Step-by-step logic:

1. Initialize search range:
    left = 0
    right = n - 1

2. While left < right:
    mid = middle index

3. Check slope at mid:
    if nums[mid] < nums[mid+1]
            → ascending slope
            → peak must be on RIGHT side
            → left = mid + 1

    else
            → descending slope OR peak at mid
            → peak is on LEFT side (including mid)
            → right = mid

4. Loop stops when:
    left == right
    → search space reduced to single index
    → that index is a peak

5. Return left (or right)

------------------------------------------------

Why this works:

If mid < mid+1:
    we are climbing → peak ahead

If mid > mid+1:
    we are descending → peak behind or at mid

Binary search keeps moving toward the side that must contain a peak.

------------------------------------------------

Mental model:

ascending  ↗ → go right
descending ↘ → go left
peak       ^ → captured by convergence

We are basically finding the point where slope changes
from rising to falling.

------------------------------------------------

Time complexity: O(log n)
Space: O(1)
*/

    public int findPeakElement(int[] nums)
    {
        int left=0,right=nums.length-1;
        
        while(left<right)
        {
            int mid=left+(right-left)/2;
            if(nums[mid] < nums[mid+1])
                left = mid+1;
            else
                right = mid;
        }
        return left;
    }
    public static void main(String[] args)
    {
        int[] nums={1,2};
        PeakElement obj = new PeakElement();
        int ans = obj.findPeakElement(nums);
        System.out.println("Answer="+ans);
    }
}