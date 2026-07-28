
class BinarySubarraysEqk{
/*
    ==================== CORE LOGIC ====================

    Problem: Count subarrays with EXACT sum = goal

    Sliding window cannot directly count "exactly K",
    because the window condition is not monotonic.

    👉 So we convert:
    exact(goal) = atMost(goal) - atMost(goal - 1)

    ----------------------------------------------------

    ==================== atMost(goal) ====================

    Goal: Count subarrays with sum ≤ goal

    We use a sliding window [left ... right]:

    - Expand window by moving 'right'
    - Keep adding elements to currSum
    - If currSum > goal → shrink from left
    - When valid → count all subarrays ending at 'right'

    ----------------------------------------------------

    WHY THIS WORKS:

    At every index 'right', once the window is valid:

    All subarrays ending at 'right' are valid:
        [left ... right]
        [left+1 ... right]
        ...
        [right ... right]

    Total = (right - left + 1)

    So:
        ans += (right - left + 1)

    ----------------------------------------------------

    ==================== GENERIC TEMPLATE ====================

    Use this for ANY "at most K" type problem:

        int left = 0, ans = 0;

        for (int right = 0; right < n; right++) {

            // 1. include current element
            add(nums[right]);

            // 2. shrink until valid
            while (condition > k) {
                remove(nums[left]);
                left++;
            }

            // 3. count valid subarrays
            ans += (right - left + 1);
        }

    ----------------------------------------------------

    WHAT CHANGES PER PROBLEM?

    - "condition" can be:
        • sum
        • number of distinct elements
        • count of odd numbers
        • number of zeros, etc.

    - add() / remove() logic changes accordingly

    ----------------------------------------------------

    ==================== EDGE CASE ====================

    If goal < 0:
    → return 0

    Reason:
    Sliding window cannot handle negative constraints
    (e.g., sum ≤ -1 is impossible for non-negative arrays)

    ----------------------------------------------------

    ==================== FINAL FLOW ====================

    1. Write atMost(k)
    2. Answer = atMost(k) - atMost(k - 1)

    ----------------------------------------------------

    TIME:  O(n)
    SPACE: O(1)
*/
    public int atMost(int[] nums,int goal)
    {
        if(goal < 0) return 0;
        int left = 0,ans = 0,currSum=0;
        for(int right=0;right<nums.length;right++)
        {
            currSum += nums[right];

            while(currSum > goal)
            {
                currSum -= nums[left];
                left++;
            }
            ans += (right-left+1);
        }
        return ans;
    }
    public int numSubarraysWithSum(int[] nums,int goal)
    {
        return atMost(nums,goal) - atMost(nums,goal-1);
    }
    public static void main(String[] args)
    {
        int[] nums = {0,0,0,0,0};
        int goal = 0;

        BinarySubarraysEqk obj = new BinarySubarraysEqk();
        int ans = obj.numSubarraysWithSum(nums,goal);
        System.out.println(ans);
    }
}