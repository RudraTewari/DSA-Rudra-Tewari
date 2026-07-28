import java.util.HashMap;

class SubArraySumK{
    public int subArraySum(int[] nums,int k)
    {
        int[] prefix = new int[nums.length];
        prefix[0]=nums[0];
        for(int i=1;i<nums.length;i++)
        {
            prefix[i]=prefix[i-1]+nums[i];
        }
        HashMap<Integer,Integer>mp = new HashMap<>();
        int count=0;
        for(int i=0;i<nums.length;i++)
        {
            if(prefix[i]==k) count++;

            if(mp.containsKey(prefix[i]-k)) count+=mp.get(prefix[i] - k);

            mp.put(prefix[i], mp.getOrDefault(prefix[i],0)+1);
        }
        return count;

    }
    public static void main(String[] args)
    {
        int[] nums={1,1,1};
        int k=2;

        SubArraySumK obj = new SubArraySumK();
        int ans = obj.subArraySum(nums,k);
        System.out.println(ans);
    }
}