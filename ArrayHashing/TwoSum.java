import java.util.HashMap;
import java.util.Map;

class TwoSum{

//Best approach using Hash Map
/*Here we use an unordered map 
At first we traverse through the array then we subtract the current value from target
to see whether the answer to subtraction exists, if it exist we got the answer.
We use .containsKey() func on Hashmap to check if the subtraction answer exist
If the answer to subtraction doesn't exist in map we save the current value in map 
for future reference as loop proceeds along with its index as second value
after finding answer we return current index and also the second value of substraction answer*/

    public int[] twoSum(int[] nums,int target)
    {
        int[] ans = new int[2];

        Map <Integer,Integer> mp = new HashMap<>();

        for(int i=0;i<nums.length;i++)
        {
            int val = target-nums[i];

            if(mp.containsKey(val))
            {
                ans[0]=i;
                ans[1] = mp.get(val);
            }
            mp.put(nums[i],i);
        }
        return ans;

    }
    public static void main(String[] args)
    {
        int[] nums={2,7,11,15};
        int target = 9;

        TwoSum obj = new TwoSum();

        int[] ans = obj.twoSum(nums,target);

        System.out.println("Answer :"+ans[0] +" "+ ans[1]);
    }
}