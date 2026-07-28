import java.util.HashMap;
import java.util.Map;

class ConfusionExam{
/*
    INTUITION:

    We are trying to find the longest substring where we can make all characters
    the same (all 'T' or all 'F') by changing at most k characters.

    Key Idea:
    Instead of actually flipping characters, we count how many flips WOULD be needed.

    In any window:
        window size = total characters in current window
        maxFreq = count of the most frequent character (either 'T' or 'F')

    So,
        flips needed = windowSize - maxFreq

    Why?
    Because we keep the majority character as it is,
    and change all other (minority) characters.

    Condition for a valid window:
        windowSize - maxFreq <= k

    Algorithm:
    1. Expand the window using 'right' pointer.
    2. Update frequency of current character.
    3. Track maxFreq (most frequent char in window).
    4. If flips needed > k, shrink window from left.
    5. Keep updating the maximum valid window length.

    Important Trick:
    We do NOT decrease maxFreq when shrinking the window.
    Even if it becomes slightly outdated, it does not affect correctness.
    It may allow a slightly larger window temporarily, but never an invalid answer.

    In short:
    We greedily expand the window and only shrink when we are forced to,
    ensuring we always maintain a valid window.
*/
    public int maxConsecutiveAnswers(String answerKey, int k)
    {
        int left = 0,maxLen = 0,maxFreq=0;
        Map<Character,Integer> freq = new HashMap<>();
        for(int right = 0; right < answerKey.length();right++)
        {
            freq.put(answerKey.charAt(right), freq.getOrDefault(answerKey.charAt(right),0)+1);
            maxFreq = Math.max(maxFreq,freq.get(answerKey.charAt(right)));
            // while(Math.min(freq.getOrDefault('T',0),freq.getOrDefault('F',0)) > k)
            while((right - left + 1) - maxFreq > k)
            {
                freq.put(answerKey.charAt(left),freq.get(answerKey.charAt(left))-1);
                left++;
            }
            maxLen = Math.max(maxLen,right-left+1);
        }
        return maxLen;
    }
    public static void main(String[] args)
    {
        String s = "TTFF";
        int k=2;
        ConfusionExam obj = new ConfusionExam();
        int ans = obj.maxConsecutiveAnswers(s,k);
        System.out.println(ans);
    }
}