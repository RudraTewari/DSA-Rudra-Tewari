class SingleElement{
    /*Key Observations:
1. Array is sorted.
2. All elements appear in pairs except one.
3. Before the single element:
    pairs start at even index → (0,1), (2,3), (4,5) ...
4. After the single element:
    pairs shift → (1,2), (3,4), (5,6) ...

Binary Search Idea:
- Use index parity to detect which side the single element lies on.

Steps:
1. Set left = 0, right = n - 1.

2. While left < right:
    mid = middle index

3. Ensure mid points to first index of a pair:
    If mid is odd → mid--  (move to even index)

4. Compare pair:
    If nums[mid] == nums[mid + 1]
            → valid pair on left side
            → single element lies on right
            → left = mid + 2
    Else
            → pair broken
            → single element lies on left (including mid)
            → right = mid

5. When loop ends:
    left == right → index of single element

6. Return nums[left]
*/
    public int singleNonDuplicate(int[] nums)
    {
        int left=0,right=nums.length-1;
        while(left < right)
        {
            int mid = left+(right-left)/2;
            if(mid%2 == 1) 
                mid--;

            if(nums[mid]==nums[mid+1]) 
                left=mid+2;
            else 
                right=mid;
        }
        return nums[left];
    } 
    public static void main(String[] args)
    {
        int[] nums={1,1,2,3,3,4,4,8,8};
        SingleElement obj=new SingleElement();
        int ans = obj.singleNonDuplicate(nums);
        System.out.println("Answer="+ans);
    }
}