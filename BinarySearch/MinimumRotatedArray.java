class MinimumRotatedArray{
    /*Logic:
    1. Maintain a search window [left, right].
    2. If the current window is already sorted (nums[left] < nums[right]),
    then nums[left] is the minimum in this range(we check using Math.min(nums[left],res)).
    3. Otherwise compute mid and update result with nums[mid].
    4. If left half is sorted (nums[mid] >= nums[left]),
    then minimum lies in right half → move left = mid + 1.
    5. Else minimum lies in left half → move right = mid - 1.
    */

    public int findMin(int[] nums)
    {
        int res=Integer.MAX_VALUE;
        int left=0,right=nums.length-1;
        int mid;
        while(left<=right)
        {
            if(nums[left]<nums[right])
            {
                res=Math.min(nums[left],res);
                break;
            }
            mid = left+(right-left)/2;
            res = Math.min(nums[mid],res);
            if(nums[mid] >= nums[left])
            {
                left=mid+1;
            }
            else{
                right=mid-1;
            }
        }
        return res;
    }
    public static void main(String[] args)
    {
        int[] nums={4,5,6,7,0,1,2,3};
        MinimumRotatedArray obj = new MinimumRotatedArray();
        int ans = obj.findMin(nums);
        System.out.println("Answer = "+ans);
    }
}