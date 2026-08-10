// import java.util.Map;
// import java.util.HashMap;

class MissingNumber
{
/*    public int missingNumber(int[] nums)
    {
        Map<Integer,Boolean> present = new HashMap<>();
        for(int val : nums)
        {
            present.put(val,true);
        }
        int ans=0;
        for(int i=0;i<=nums.length;i++)
        {
            if(!present.containsKey(i))
                ans = i;
        }
        return ans;
    }
*/
    public int missingNumber(int[] nums)
    {
        int totalSum=0;
        for(int i=0;i<=nums.length;i++)
        {
            totalSum+=i;
        }
        int sum=0;
        for(int i=0;i<nums.length;i++)
        {
            sum+=nums[i];
        }
        return totalSum-sum;
    }
    public static void main(String[] args)
    {
        int[] nums={0,1,2};
        MissingNumber obj = new MissingNumber();
        int ans = obj.missingNumber(nums);
        System.out.println("Answer : "+ans);
    }
}