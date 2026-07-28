class NiceSubarrays{
/*
    INTUITION:
    -----------
    We need to count subarrays that contain exactly k odd numbers.

    Key idea:
    - Instead of checking every subarray (which is costly), we use a sliding window.
    - We expand the window using 'right' and keep track of how many odd numbers
    are inside the current window using 'countOdd'.

    Important observation:
    - When the window contains exactly k odd numbers, there can be MULTIPLE
    valid subarrays ending at the current 'right'.
    - These subarrays differ based on how many even numbers we can skip from the left.

    This is where 'temp' comes in:
    - 'temp' stores how many valid starting points exist for the current 'right'.
    - Each time we move 'left' forward while still maintaining k odd numbers,
    we discover a new valid subarray.

    ------------------------------------------------------------

    APPROACH:
    -----------
    1. Initialize:
    - left = 0 (start of window)
    - countOdd = 0 (number of odd elements in current window)
    - temp = 0 (number of valid subarrays ending at current right)
    - ans = 0 (final answer)

    2. Traverse array using 'right' pointer:
    - If nums[right] is odd:
            increment countOdd
            reset temp = 0 (new odd changes valid window structure)

    3. When countOdd == k:
    - Move 'left' forward to count all valid subarrays:
            while(countOdd == k):
                temp++  → found a valid subarray
                if nums[left] is odd:
                    decrement countOdd
                move left forward

    4. Add temp to answer:
    - ans += temp
    - This adds all valid subarrays ending at current 'right'

    5. Return ans

    ------------------------------------------------------------

    WHY TEMP WORKS:
    ----------------
    - temp counts how many valid starting indices exist for current 'right'
    - Each increment of temp corresponds to one valid subarray
    - This avoids recomputing all subarrays explicitly

    ------------------------------------------------------------

    TIME COMPLEXITY:
    ----------------
    - O(n) → each element is processed at most twice

    SPACE COMPLEXITY:
    -----------------
    - O(1)

    ------------------------------------------------------------

    NOTE:
    ------
    This is an optimized sliding window approach.
    Alternative approach:
        exactly(k) = atMost(k) - atMost(k-1)
*/
    public int numberOfSubarrays(int[] nums,int k)
    {
        int left =0;
        int temp=0;
        int countOdd=0;
        int ans=0;
        for(int right = 0;right<nums.length;right++){
            if((nums[right]&1) == 1){
                countOdd++;
                temp=0;
            }
            
            while(countOdd == k)
            {
                temp++;
                if((nums[left] & 1) == 1)
                    countOdd--;

                left++;
            }
            ans += temp;
        } 
        return ans;
    }
    public static void main(String[] args)
    {
        int[] nums={2,2,2,1,2,2,1,2,2,2};
        int k=2;
        NiceSubarrays obj = new NiceSubarrays();
        int ans = obj.numberOfSubarrays(nums,k);
        System.out.println(ans);
    }
}