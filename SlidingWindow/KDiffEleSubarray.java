import java.util.*;
class KDiffEleSubarray{
    public static int atMost(int[] nums,int k)
    {
        Map<Integer,Integer> freq = new HashMap<>();
        int l=0;
        int ans = 0;
        for(int r=0;r<nums.length;r++)
        {
            freq.put(nums[r],freq.getOrDefault(nums[r],0)+1);

            while(freq.size() > k)
            {
                freq.put(nums[l],freq.get(nums[l])-1);
                if(freq.get(nums[l])==0)
                    freq.remove(nums[l]);
                l++;
            }
            ans += r-l+1;
        }
        return ans;
    } 
    public int subarraysWithKDistinct(int[] nums,int k)
    {
        return atMost(nums,k) - atMost(nums,k-1);
    }
    public static void main(String[] args)
    {
        int[] nums={1,2,1,2,3};
        int k = 2;

        KDiffEleSubarray obj = new KDiffEleSubarray();
        int ans = obj.subarraysWithKDistinct(nums,k);
        System.out.println(ans);
    }
}