import java.util.HashMap;
import java.util.Map;

class ContainsDuplicate{
    
    public boolean containsDuplicate(int[] nums)
    {
        Map<Integer,Integer>mp = new HashMap<>();
        for(int val : nums)
        {
            mp.put(val,mp.getOrDefault(val,0)+1);
        }
        for(int val : mp.values())
        {
            if(val > 1) return true;
        }
        return false;
    }
    public static void main(String[] args){
        int[] nums={1,2,3};

        ContainsDuplicate obj = new ContainsDuplicate();
        boolean ans = obj.containsDuplicate(nums);

        System.out.println(ans);
    }
}