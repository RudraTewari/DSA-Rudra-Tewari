/*
    APPROACH : SEARCH IN ROTATED SORTED ARRAY II (WITH DUPLICATES)

    INTUITION:
    -------------
    In a rotated sorted array, at least one half (left or right)
    will always be sorted.

    Using Binary Search:
    1. Find mid element.
    2. If target found -> return true.
    3. Determine which half is sorted.
    4. Check whether target lies inside the sorted half.
    5. Eliminate the other half.

    MAIN CHALLENGE:
    ----------------
    Duplicates can break the normal sorted-half detection.

    Example:
    nums = [1,1,1,1,3,1]

    Here:
    nums[low] == nums[mid] == nums[high]

    We cannot determine which half is sorted because
    both sides look identical.

    SOLUTION:
    -----------
    Shrink the search space:
    low++
    high--

    This removes duplicates from boundaries and helps
    restore the sorted-half property.

    ALGORITHM:
    -------------
    1. Initialize:
    low = 0
    high = n-1

    2. While low <= high:
    a) Find mid

    b) If nums[mid] == target
        -> return true

    c) If duplicates at boundaries:
            nums[low] == nums[mid] &&
            nums[mid] == nums[high]

        -> low++
        -> high--
        -> continue

    d) Check if LEFT HALF is sorted:
            nums[low] <= nums[mid]

        If target lies inside left range:
            nums[low] <= target < nums[mid]
            -> search left
        Else
            -> search right

    e) Otherwise RIGHT HALF is sorted

        If target lies inside right range:
            nums[mid] < target <= nums[high]
            -> search right
        Else
            -> search left

    3. If loop ends:
    return false

    TIME COMPLEXITY:
    ------------------
    Best/Average Case:
    O(log n)

    Worst Case:
    O(n)

    Why O(n)?
    Because duplicates may force us to shrink boundaries
    one-by-one.

    Example:
    [1,1,1,1,1,1,1]

    SPACE COMPLEXITY:
    -------------------
    O(1)
*/
class SearchRotatedArrayII{
    public boolean search(int[] nums,int target){
        int low=0,high=nums.length-1;
        int mid=0;
        while(low<=high){
            mid = low+(high-low)/2;

            if(nums[mid]==target)
                return true;

            if(nums[low] == nums[mid] && nums[mid] == nums[high])
            {
                low++;high--;
                continue;  // Shrink until the above condition fails without executing code downwards
            }
            
            if(nums[low] <= nums[mid])  // LEFT HALF SORTED
            {
                if(nums[low] <= target && target < nums[mid]){
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }else{ // RIGHT HALF SORTED
                if(nums[mid] < target && target <= nums[high])
                {
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
        }
        return false;
    }
    public static void main(String[] args){
        int[] nums={2,5,6,0,0,1,2};
        int target=9;

        SearchRotatedArrayII obj= new SearchRotatedArrayII();

        boolean ans = obj.search(nums,target);
        System.out.println(ans);
    }
}