import java.util.Map;
import java.util.HashMap;

/*
        MINIMUM WINDOW SUBSTRING (Sliding Window Approach)

        Goal:
        -----
        Find the smallest substring in 's' that contains all characters of 't'
        (including their frequencies).

        Key Idea:
        ---------
        Use a sliding window [left, right] and expand/shrink it dynamically.

        Steps:
        ------

        1. Build a frequency map (tMap) for string 't'
        → Stores required characters and their counts.

        2. Use another map (windowMap) to track frequencies inside current window.

        3. Maintain:
        - chRequired = number of unique characters in t
        - chIncluded = how many characters currently satisfy required frequency

        4. Expand window using 'right':
        - Add current character to windowMap
        - If its frequency matches tMap → increment chIncluded

        5. When window becomes VALID (chIncluded == chRequired):
        → Try to shrink from left to get minimum window

        Inside while loop:
        - Update minimum length if current window is smaller
        - Remove left character from window
        - If removal breaks required frequency → decrement chIncluded
        - Move left pointer forward

        6. Continue expanding and shrinking

        7. Return result:
        - If no valid window found → return ""
        - Else return substring using stored indices

        Important Conditions:
        ---------------------
        Expand condition:
            if(tMap.containsKey(r) && windowMap.get(r) == tMap.get(r))
                chIncluded++;

        Shrink condition:
            if(tMap.containsKey(l) && windowMap.get(l) < tMap.get(l))
                chIncluded--;

        Complexity:
        -----------
        Time: O(n)  → Each character visited at most twice
        Space: O(1) → Since character set is limited (or O(k) for map)

        Intuition:
        ----------
        - Grow window until it satisfies all requirements
        - Then shrink it to remove unnecessary characters
        - Keep track of smallest valid window

*/

class MinWindowFreq{
    public String minWindow(String s,String t)
    {
        Map<Character,Integer> tMap = new HashMap<>();
        for(char ch : t.toCharArray())
        {
            tMap.put(ch,tMap.getOrDefault(ch,0)+1);
        }
        int left=0,minLen=Integer.MAX_VALUE,chIncluded=0;
        int chRequired = tMap.size(),leftIdx=0;

        Map<Character,Integer> windowMap = new HashMap<>();

        for(int right = 0;right<s.length();right++)
        {
            char r = s.charAt(right);
            windowMap.put(r,windowMap.getOrDefault(r,0)+1);
            if(tMap.containsKey(r) && tMap.get(r).intValue() == windowMap.get(r).intValue())
                chIncluded++;
            
            while(left<=right && chIncluded == chRequired)
            {
                if((right-left+1) < minLen)
                {
                    minLen = right-left+1;
                    leftIdx = left;
                }
                char l = s.charAt(left);
                windowMap.put(l,windowMap.get(l)-1);

                if(tMap.containsKey(l) && windowMap.get(l) < tMap.get(l))
                    chIncluded--;

                left++;
            }
        }
        return (minLen == Integer.MAX_VALUE) ? "" : s.substring(leftIdx,leftIdx+minLen);
    }
    public static void main(String[] args)
    {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        MinWindowFreq obj = new MinWindowFreq();
        String ans = obj.minWindow(s,t);
        for(char ch : ans.toCharArray())
        {
            System.out.print(ch+" ");
        }
        System.out.println();
    }
}