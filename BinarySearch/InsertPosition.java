class InsertPosition{
        /*
        LOGIC:

        This is a Binary Search approach to find the "Lower Bound" of target.

        👉 Lower Bound = First index where element >= target

        WHY we need this?
        - If target exists → return its index
        - If target does NOT exist → return position where it should be inserted
        to maintain sorted order

        ------------------------------------------------------

        APPROACH:

        1. Initialize:
        start = 0
        end = n - 1
        ans = n (default case when target is greater than all elements)

        2. Run Binary Search:
        while (start <= end):

            mid = middle index

            CASE 1: nums[mid] >= target
                → This could be a valid answer
                → Store it in 'ans'
                → But we try to find a smaller index on LEFT
                → So move: end = mid - 1

            CASE 2: nums[mid] < target
                → mid cannot be the answer
                → Move RIGHT: start = mid + 1

        3. After loop ends:
        'ans' contains the smallest index where nums[index] >= target

        ------------------------------------------------------

        EDGE CASES:

        - target smaller than all elements → ans = 0
        - target exists in array → returns its first occurrence
        - target lies between elements → returns correct insert position
        - target greater than all elements → ans remains n

        ------------------------------------------------------

        TIME COMPLEXITY: O(log n)
        SPACE COMPLEXITY: O(1)
        */

    public int searchInsert(int[] nums,int target)
    {
        int start=0,end=nums.length-1;
        int mid=0,ans=nums.length;

        while(start <= end)
        {
            mid = start + ((end-start)/2);

            if(nums[mid]>=target){
                ans=mid;
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return ans;
    }
    public static void main(String[] args)
    {
        int[] arr={1,2,3};
        int target=4;
        InsertPosition obj = new InsertPosition();
        int ans = obj.searchInsert(arr,target);
        System.out.println(ans);
    }
}