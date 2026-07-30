class SearchRotatedArray{
    /*
Search in Rotated Sorted Array — Binary Search Logic

Key Idea:
A rotated sorted array always has at least ONE sorted half.
At any index mid:
    either left half (lb → mid) is sorted
    OR right half (mid → ub) is sorted

Step 1: Find mid
    mid = lb + (ub - lb) / 2

Step 2: Check if mid is target
    if(nums[mid] == target) → found

Step 3: Determine which half is sorted
    if(nums[lb] <= nums[mid])
        → left half is sorted
    else
        → right half is sorted

Step 4: Check if target lies inside the sorted half

    LEFT SORTED:
        nums[lb] <= target < nums[mid]
        → search left → ub = mid - 1
        else
        → search right → lb = mid + 1

    RIGHT SORTED:
        nums[mid] < target <= nums[ub]
        → search right → lb = mid + 1
        else
        → search left → ub = mid - 1

Loop until lb > ub → target not found
*/
    public int search(int[] nums,int target)
    {
        int lb=0,ub=nums.length-1;
        int mid=0;
        while(lb<=ub)
        {
            mid = lb+(ub-lb)/2;

            if(nums[mid] == target)
                return mid;
            
            if(nums[lb]<=nums[mid]) //Left Half Sorted 
            {
                if(nums[lb]<= target && target < nums[mid])
                {
                    ub=mid-1;
                }
                else{
                    lb=mid+1;
                }
            }
            else{ //Right Half Sorted
                if(nums[mid] < target && target <= nums[ub])
                {
                    lb=mid+1;
                }
                else{
                    ub=mid-1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args)
    {
        int[] nums={4,5,6,7,0,1,2};
        int target = 0;
        SearchRotatedArray obj = new SearchRotatedArray();
        int ans = obj.search(nums,target);
        System.out.println("Answer = "+ans);
    }
}