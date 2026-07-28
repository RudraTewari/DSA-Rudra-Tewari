import java.util.*;

class CountGoodSubarrays{
    /*
INTUITION & APPROACH (VERY IMPORTANT PATTERN)

Problem Type:
→ Count number of subarrays where some "threshold condition" is satisfied
→ Here: number of subarrays having at least k "good pairs"

-----------------------------------------
🔹 What is a "good pair"?
-----------------------------------------
For any number x:
If it appears f times in a subarray,
then number of pairs = fC2

But instead of recomputing combinations,
we maintain it incrementally:

👉 When adding nums[r]:
   existing freq = f
   new pairs formed = f
   (because this element pairs with all previous f occurrences)

👉 So:
   pairCount += map.get(nums[r])

-----------------------------------------
🔹 Core Sliding Window Idea
-----------------------------------------
We maintain a window [l ... r]

Goal:
→ Find subarrays where pairCount >= k

-----------------------------------------
🔹 Key Observation (CRUCIAL)
-----------------------------------------
Once a window [l ... r] becomes VALID (pairCount >= k),

👉 ANY extension to the right will ALSO be valid

Example:
[l ... r] is valid
→ [l ... r+1], [l ... r+2], ... all valid

So instead of checking all,
we directly count:

👉 count += (n - r)

-----------------------------------------
🔹 Window Expansion
-----------------------------------------
For every r:
1. Add nums[r]
2. Update pairCount using frequency map

-----------------------------------------
🔹 Window Shrinking
-----------------------------------------
While pairCount >= k:
1. Count all valid subarrays starting at l
   → count += (n - r)

2. Remove nums[l] from window

IMPORTANT:
When removing nums[l]:
- Its current freq = f
- After removal → freq becomes (f-1)

Pairs lost = (f - 1)

So:
pairCount -= map.get(nums[l]) AFTER decrement

-----------------------------------------
🔹 Why While Loop?
-----------------------------------------
Because:
We want to count ALL valid starting points

Each time we shrink:
→ we generate new valid subarrays

-----------------------------------------
🔹 Pattern Recognition
-----------------------------------------
This is NOT normal "atMost" pattern

This is:
👉 "Count subarrays where condition >= k"

Template:
1. Expand window
2. When condition satisfied:
    → count += (n - r)
3. Shrink to find more valid starts



*/
    public long countGood(int[] nums,int k)
    {
        Map<Integer,Integer> map = new HashMap<>();
        int l=0,pairCount=0;
        long count=0;
        for(int r=0;r<nums.length;r++)
        {
            if(map.containsKey(nums[r])==true)
            {
                pairCount+=map.get(nums[r]);
            }
            map.put(nums[r],map.getOrDefault(nums[r],0)+1);

            while(pairCount >= k)
            {
                count += nums.length-r;
                map.put(nums[l],map.get(nums[l])-1);
                pairCount -= map.get(nums[l]);
                l++;
            }
        }
        return count;
    }
    public static void main(String[] args)
    {
        int[] nums={3,1,4,3,2,2,4};
        int k=2;
        CountGoodSubarrays obj = new CountGoodSubarrays();
        long ans = obj.countGood(nums,k);
        System.out.println(ans);
    }
}