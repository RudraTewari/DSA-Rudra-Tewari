/*Core Idea

Since the array is sorted, duplicates will always be adjacent.

We are allowed to keep at most 2 occurrences of each element.

So instead of checking all previous elements, we only need to check:
👉 “Have we already added this element 2 times?”

Key Observation

If we already placed elements up to index k-1, then:

The last 2 kept elements are at:

nums[k-2] and nums[k-1]

To decide whether to include current element ele, we check:

nums[k-2] != ele

👉 Why?

If nums[k-2] == ele, it means we already have 2 copies → skip
If nums[k-2] != ele, we can safely include it */


class RemoveDuplicatesII{
    public int removeDuplicates(int[] nums)
    {
        int k=2;
        for(int ele : nums)
        {
            if(nums[k-2] != ele)
            {
                nums[k++]=ele;
            }
        }
        return k;
    }
    public static void main(String[] args)
    {
        int[] nums={0,0,1,1,1,1,2,3,3};
        RemoveDuplicatesII obj = new RemoveDuplicatesII();
        int k = obj.removeDuplicates(nums);
        System.out.println(k);
    }
}