import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FourSum{

    public List<List<Integer>> fourSum(int[] nums,int target)
    {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            if(i>0 && nums[i]==nums[i-1]) continue;
            for(int j=i+1;j<n;)
            {
                int k=j+1,l=n-1;

                while(k<l)
                {
                    long sum =(long) nums[i]+ nums[j]+ nums[k]+ nums[l];

                    if(sum<target) 
                        k++;
                    else if(sum >target)
                        l--;
                    else{
                        ans.add(Arrays.asList(nums[i],nums[j],nums[k],nums[l]));
                        k++;
                        l--;

                        while(k<l && nums[k] == nums[k-1]) k++;
                    }
                }
                j++;
                while(j<n && nums[j] == nums[j-1]) j++;
            }
        }
        return ans;
    }
    public static void main(String[] args)
    {
        int[] nums={2,2,2,2,2};
        int target=8;
        FourSum obj = new FourSum();
        List<List<Integer>> ans= obj.fourSum(nums,target);

        for(List<Integer> val : ans)
        {
            System.out.println(val);
        }
    }
}