import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

class XSumKSubarray{
/*
    INTUITION:
    -----------
    For every subarray of size k, we want to compute an "x-sum".
    But instead of summing all elements, we only consider the top x elements
    based on frequency.

    Key idea:
    - Elements that appear more frequently should contribute more.
    - If two elements have same frequency, the larger value is preferred.
    - So we prioritize:
        1. Higher frequency
        2. Larger value (if frequency ties)

    To achieve this:
    - Count how many times each number appears in the current window.
    - Sort elements based on the priority rule.
    - Pick top x elements and compute:
        xsum = Σ (frequency * value)

    ------------------------------------------------------------

    APPROACH:
    -----------
    1. Initialize:
    - Let n = nums.length
    - Create result array ans of size (n - k + 1)

    2. Iterate over all possible subarrays of size k:
    - Loop i from 0 to (n - k)

    3. For each window [i ... i+k-1]:
    a) Build a frequency map:
        - Use HashMap<value, frequency>
        - Count occurrences of each element in the window

    b) Convert map to list:
        - Store as pairs: [frequency, value]

    c) Sort the list:
        - First by frequency in descending order
        - If equal, by value in descending order

    d) Compute x-sum:
        - Take first x elements from sorted list
        - Add (frequency * value) to xsum

    e) Store result:
        - ans[i] = xsum

    4. Return ans array

*/
    public int[] findXSum(int[] nums,int k,int x)
    {
        int n=nums.length;
        int[] ans = new int[n-k+1];

        for(int i=0;i<=n-k;i++)
        {
            Map<Integer,Integer> count = new HashMap<>();
            for(int j=i;j<i+k;j++)
            {
                count.put(nums[j],count.getOrDefault(nums[j],0)+1);
            }
            List<int[]> freq = new ArrayList<>();
            for(Map.Entry<Integer,Integer> entry : count.entrySet())
            {
                freq.add(new int[] {entry.getValue(),entry.getKey()});
            }
            freq.sort((a,b)-> a[0]!=b[0] ? b[0]-a[0] : b[1]-a[1]);
            int xsum=0;
            for(int m=0;m<x&&m<freq.size();m++)
            {
                xsum += freq.get(m)[0] * freq.get(m)[1];
            }
            ans[i]=xsum;
        }
        return ans;
    }
    public static void main(String[] args)
    {
        int[] nums={1,1,2,2,3,4,2,3};
        int k=6;
        int x=2;

        XSumKSubarray obj = new XSumKSubarray();
        int[] ans = obj.findXSum(nums,k,x);
        for(int val : ans)
        {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}