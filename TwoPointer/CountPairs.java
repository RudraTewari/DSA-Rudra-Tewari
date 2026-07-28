import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class CountPairs{
    /*Optimization Insight:
If array is sorted and:
    nums[left] + nums[right] < target
then ALL elements between left+1 … right also form valid pairs with left
because they are <= nums[right]

So instead of checking each j individually,
we can add all pairs at once:
    count += (right - left)

Algorithm Steps:
1. Sort the array/list
2. Initialize two pointers:
       left = 0
       right = n - 1
3. While left < right:
       sum = nums[left] + nums[right]

       If sum < target:
           → all pairs (left, left+1 … right) valid
           → count += (right - left)
           → move left++ to explore next base element

       Else:
           → sum too large
           → move right-- to reduce sum

4. Continue until pointers meet

Why This Works:
Sorted order guarantees:
    nums[left] + nums[k] <= nums[left] + nums[right]
for all k in (left+1 … right)*/
    public int countPairs(List<Integer>nums,int target)
    {
        Collections.sort(nums);
        int left = 0;
        int right=nums.size()-1;
        int count=0;
        while(left < right)
        {
            if(nums.get(left) + nums.get(right)<target)
            {
                count+=(right-left);
                left++;
            }
            else{
                right--;
            }
        }
        return count;
    }
    /*
    public int countPairs(List<Integer>nums,int target)
    {
        int count=0;
        for(int i=0;i<nums.size();i++)
        {
            for(int j=1;j<nums.size();j++)
            {
                if(i<j && nums.get(i)+nums.get(j) < target)
                {
                    count++;
                }
            }
        }
        return count;
    }*/
    public static void main(String[] args)
    {
        List<Integer> nums = new ArrayList<>(Arrays.asList(-1,1,2,3,1));
        int target = 2;
        CountPairs obj = new CountPairs();
        int ans = obj.countPairs(nums,target);
        System.out.println("Answer = "+ans);
    }
}