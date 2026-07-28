import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
class CountCompleteSubarrays{
/*
    INTUITION (Sliding Window + Counting Trick)

    Goal:
    Count subarrays that contain ALL distinct elements present in the array.

    ------------------------------------------------------------
    STEP 1: Understand totalDistinct
    ------------------------------------------------------------
    We first compute how many unique elements exist in the whole array.
    Example: nums = [1,2,1,3]
    Distinct elements = {1,2,3} → totalDistinct = 3

    So now our task becomes:
    👉 Count subarrays that contain all 3 distinct elements.

    ------------------------------------------------------------
    STEP 2: Use Sliding Window
    ------------------------------------------------------------
    We use two pointers:
    left  → start of window
    right → end of window

    We expand the window using 'right' and track frequencies using a map.

    freq[x] = how many times x appears in current window

    countDistinct = how many UNIQUE elements are currently in the window

    ------------------------------------------------------------
    STEP 3: Expanding Window
    ------------------------------------------------------------
    For every nums[right]:
    - Add it to freq map
    - If it's appearing for the first time → increase countDistinct

    ------------------------------------------------------------
    STEP 4: When Window becomes VALID
    ------------------------------------------------------------
    Condition:
    countDistinct == totalDistinct

    This means:
    👉 Current window contains ALL distinct elements

    Now here's the KEY IDEA 👇

    ------------------------------------------------------------
    STEP 5: Counting Subarrays (VERY IMPORTANT)
    ------------------------------------------------------------
    If current window [left ... right] is valid,
    then ALL subarrays starting from 'left' and ending at:

        right, right+1, right+2, ... , n-1

    will ALSO be valid.

    WHY?
    Because adding more elements to the right
    cannot remove existing distinct elements.

    So number of such subarrays:
    👉 nums.length - right

    So we do:
    ans += nums.length - right

    ------------------------------------------------------------
    STEP 6: Shrinking Window
    ------------------------------------------------------------
    Now we try to shrink from left to find more valid windows:

    - Remove nums[left] from freq
    - If its frequency becomes 0 → we lost a distinct element
    → decrease countDistinct
    - Move left forward

    We keep shrinking while window is still valid.

    ------------------------------------------------------------
    STEP 7: Repeat
    ------------------------------------------------------------
    Continue expanding right and repeating above steps.

    ------------------------------------------------------------
    FINAL IDEA SUMMARY
    ------------------------------------------------------------
    1. Expand window until it contains all distinct elements
    2. Once valid → count ALL future extensions in one go
    3. Shrink window to find more valid starting points
    4. Repeat


    That's the core trick 🚀
*/
    public int countCompleteSubarrays(int[] nums)
    {
        Set<Integer> set = new HashSet<>();
        for(int num : nums)
            set.add(num);
        int totalDistinct = set.size();
        int left =0,ans = 0,countDistinct=0;
        Map<Integer,Integer> freq = new HashMap<>();
        for(int right = 0;right < nums.length;right++)
        {
            freq.put(nums[right],freq.getOrDefault(nums[right],0)+1);

            if(freq.get(nums[right]) == 1)
                countDistinct++;
            while(countDistinct==totalDistinct)
            {
                ans += nums.length-right;
                freq.put(nums[left],freq.get(nums[left])-1);
                if(freq.get(nums[left])==0)
                    countDistinct--;
                left++;
            }
        }
        return ans;
    }
    public static void main(String[] args)
    {
        int[] nums={1,3,1,2,2};
        CountCompleteSubarrays obj = new CountCompleteSubarrays();
        int ans = obj.countCompleteSubarrays(nums);
        System.out.println(ans);
    }
}