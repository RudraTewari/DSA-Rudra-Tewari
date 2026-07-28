import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;

class ThreeSum{
//BRUTE FORCE APPROACH
/*    public List<List<Integer>> threeSum(int[] nums)
    {
        Set<List<Integer>> s = new HashSet<>();

        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0;i< nums.length;i++)
        {
            for(int j=i+1;j<nums.length;j++)
            {
                for(int k =j+1;k<nums.length;k++)
                {
                    if(nums[i]+nums[j]+nums[k]==0)
                    {
                        List<Integer> trip = new ArrayList<>();
                        trip.add(nums[i]);
                        trip.add(nums[j]);
                        trip.add(nums[k]);
                        Collections.sort(trip);
                        if(!s.contains(trip))
                        {
                            s.add(trip);
                            ans.add(trip);
                        }
                    }
                }
            }
        }
        return ans;
    }*/

//BETTER APPROACH USING HASHING
/*   public List<List<Integer>> threeSum(int[] nums)
    {
        List<List<Integer>> ans = new ArrayList<>();
        Set<List<Integer>> uniqueTriplets = new HashSet<>();

        for(int i=0;i<nums.length;i++)
        {
            
            Set<Integer> s = new HashSet<>();
            int target = -nums[i];

            for(int j=i+1;j< nums.length;j++)
            {
                int third = target - nums[j];
                if(s.contains(third))
                {
                    
                    List<Integer> triplets = Arrays.asList(nums[i],nums[j],third);
                    Collections.sort(triplets);

                    if(!uniqueTriplets.contains(triplets))
                    {
                        uniqueTriplets.add(triplets);
                        ans.add(triplets);
                    }
                }
                s.add(nums[j]);
            }
        }
        
        return ans;
    }*/

/*Here we use 2-pointer approach 
At first we sort the array & fix i then on the remaining array we apply two pointer approach
to find the remaining j and k value 
But in sorted array there is a chance of repeated value which will result in calculation of similar 
triplets so we used two if statements with continue
*/
    public List<List<Integer>> threeSum(int[] nums)
    {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0; i<nums.length; i++)
        {
            if(i>0 && nums[i]==nums[i-1]) continue;  //To avoid i values which are same [-4,-1,-1,0,1,2] this avoids next -1 during calculation


            int j=i+1,k=nums.length-1;
            while(j<k)
            {
                int sum = nums[i]+nums[j]+nums[k];

                if(sum<0) 
                    j++;
                else if(sum>0) 
                    k--;
                else
                {
                    ans.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                    while(j < k && nums[j]==nums[j-1]) j++;   //To avoid j value which are same 
                }
            }
        }
        return ans;
    }

    public static void main(String[] args)
    {
        int[] nums={-1,0,1,2,-1,-4};

        ThreeSum obj = new ThreeSum();

        List<List<Integer>> ans = obj.threeSum(nums);

        for (List<Integer> trip : ans) {
            System.out.println(trip);
        }

    }
}