import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
class ContainDuplicate2{
    public boolean containsDuplicate(int[] nums, int k)
    {
        Map<Integer,Integer> mp = new HashMap<>();
        int n= nums.length;
        for(int i=0; i<n; i++)
        {
            if(mp.containsKey(nums[i]))
            {
                int ans = Math.abs(mp.get(nums[i])-i);

                if(ans<=k) return true;
            }
            mp.put(nums[i],i);
        }
        return false;
    }
    public static void main(String[] args)
    {
        int[] nums={1,1,1,1};
        int k=3;
        ContainDuplicate2 obj = new ContainDuplicate2();
        boolean ans = obj.containsDuplicate(nums,k);
        System.out.println(ans);
    }
}