/*
    Problem: Length of Longest Substring Without Repeating Characters

    Approach: Sliding Window + HashSet

    Idea:
    We use a sliding window defined by two pointers:
    - windowStart → start of the current substring
    - windowEnd → end of the current substring

    We also maintain a HashSet to keep track of unique characters
    in the current window.

    Steps:

    1. Initialize:
        - maxLen = Integer.MIN_VALUE (to track maximum length found)
        - windowStart = 0, windowEnd = 0
        - HashSet<Character> set → stores unique characters in current window

    2. Expand the window:
        - Iterate while windowEnd < s.length()
        - Pick the current character → ch = s.charAt(windowEnd)

    3. Handle duplicates:
        - If 'ch' already exists in the set, it means we have a duplicate
            in the current window.
        - To fix this, shrink the window from the left:
            while(windowStart < windowEnd && set.contains(ch)):
                - remove s.charAt(windowStart) from set
                - increment windowStart
        - This ensures the window becomes valid again (all unique characters)

    4. Add current character:
        - Add 'ch' to the set after removing duplicates

    5. Update maximum length:
        - Current window size = windowEnd - windowStart + 1
        - Update maxLen using Math.max()

    6. Move forward:
        - Increment windowEnd to expand the window further

    7. Edge case:
        - If string is empty, maxLen remains Integer.MIN_VALUE
        - Return 0 in that case

    Key Insight:
        - The window always contains unique characters
        - Each character is added and removed at most once → O(n) time complexity

    Complexity:
        - Time: O(n)
        - Space: O(min(n, charset size)) ~ O(128) for ASCII

*/
// import java.util.Set;
// import java.util.HashSet;
// class LongestSubStringWithoutRepeatCh{
//     public int lengthOfLongestSubstring(String s)
//     {
//         int maxLen = Integer.MIN_VALUE;
//         int windowStart=0, windowEnd = 0;
//         Set <Character> set = new HashSet<>();
//         while(windowEnd < s.length())
//         {
//             char ch = s.charAt(windowEnd);
//             if(set.contains(ch))
//             {
//                 while(windowStart < windowEnd && set.contains(ch))
//                 {
//                     set.remove(s.charAt(windowStart));
//                     windowStart++;
//                 }
//             }
//             set.add(ch);
//             maxLen=Math.max(maxLen,windowEnd-windowStart+1);
//             // System.out.println(maxLen);
//             windowEnd++;
//         }
//         return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
//     }
    public static void main(String[] args){
        String str = "abcabcbb";
        LongestSubStringWithoutRepeatCh obj = new LongestSubStringWithoutRepeatCh();
        int ans = obj.lengthOfLongestSubstring(str);
        System.out.println(ans);
    }
}