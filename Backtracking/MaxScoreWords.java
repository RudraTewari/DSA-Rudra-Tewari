package Backtracking;
/*
 * APPROACH: BACKTRACKING (CHOOSE / DON'T CHOOSE)
 *
 * We have a set of words and a limited number of letters.
 * Each word can either:
 *
 *      1. Be selected
 *      2. Not be selected
 *
 * For every word, we explore both possibilities recursively.
 *
 *
 * ------------------------------------------------------------
 * 1. REPRESENT AVAILABLE LETTERS USING FREQUENCY ARRAY
 * ------------------------------------------------------------
 *
 * Instead of repeatedly searching the letters array, we store
 * the number of available occurrences of each character.
 *
 * Example:
 *
 * letters = {'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'}
 *
 * freq['a'] = 2
 * freq['c'] = 1
 * freq['d'] = 3
 * freq['g'] = 1
 * freq['o'] = 2
 *
 * This allows us to quickly check whether a word can be formed.
 *
 *      int[] freq = new int[26];
 *
 *      for(int i = 0; i < letters.length; i++){
 *          freq[letters[i] - 'a']++;
 *      }
 *
 *
 * ------------------------------------------------------------
 * 2. AT EVERY WORD, WE HAVE TWO CHOICES
 * ------------------------------------------------------------
 *
 * Suppose we are currently considering words[idx].
 *
 * We have two possibilities:
 *
 *              words[idx]
 *                  |
 *          -----------------
 *          |               |
 *        TAKE           DON'T TAKE
 *          |               |
 *     reduce letters    keep freq
 *     add word score
 *
 * We recursively explore both branches.
 *
 *
 * ------------------------------------------------------------
 * 3. TRY TO TAKE THE CURRENT WORD
 * ------------------------------------------------------------
 *
 * We first make a copy of the frequency array.
 *
 *      int[] tempFreq = freq.clone();
 *
 * We MUST clone the array because arrays are mutable objects.
 *
 * If we directly modify freq, the changes would also affect
 * the "DON'T TAKE" branch.
 *
 * For every character in the word:
 *
 *      char ch = words[idx].charAt(j);
 *
 * We consume one occurrence of that character:
 *
 *      tempFreq[ch - 'a']--;
 *
 * And add its corresponding score:
 *
 *      tempScore += score[ch - 'a'];
 *
 *
 * ------------------------------------------------------------
 * 4. CHECK WHETHER THE WORD CAN BE FORMED
 * ------------------------------------------------------------
 *
 * After consuming a character, if its frequency becomes negative,
 * we don't have enough copies of that character.
 *
 * Example:
 *
 * Available:
 *      a = 1
 *
 * Word:
 *      "aa"
 *
 * After using the first 'a':
 *      a = 0
 *
 * After trying the second 'a':
 *      a = -1
 *
 * Therefore the word cannot be selected.
 *
 *      if(tempFreq[ch - 'a'] < 0)
 *          break;
 *
 *
 * ------------------------------------------------------------
 * 5. IF THE WORD IS VALID, RECURSE WITH IT
 * ------------------------------------------------------------
 *
 * If we successfully processed the entire word:
 *
 *      if(j == words[idx].length()){
 *
 *          Solve(
 *              idx + 1,
 *              words,
 *              letters,
 *              score,
 *              currScore + tempScore,
 *              tempFreq
 *          );
 *      }
 *
 * Notice that:
 *
 *      currScore + tempScore
 *
 * is passed because we selected this word.
 *
 * And:
 *
 *      tempFreq
 *
 * is passed because the letters used by the word are no longer
 * available for the remaining words.
 *
 *
 * ------------------------------------------------------------
 * 6. DON'T TAKE THE CURRENT WORD
 * ------------------------------------------------------------
 *
 * Regardless of whether the word was valid or not, we also
 * explore the possibility of NOT selecting it.
 *
 *      Solve(
 *          idx + 1,
 *          words,
 *          letters,
 *          score,
 *          currScore,
 *          freq
 *      );
 *
 * Here:
 *
 *      currScore remains unchanged
 *      freq remains unchanged
 *
 * because we didn't use the word or any letters from it.
 *
 *
 * ------------------------------------------------------------
 * 7. UPDATE THE MAXIMUM SCORE
 * ------------------------------------------------------------
 *
 * At every recursive call, currScore represents the score of
 * the words selected so far.
 *
 * Therefore we continuously maintain:
 *
 *      maxScore = Math.max(maxScore, currScore);
 *
 * This also allows us to update the answer at intermediate
 * states, not only when all words have been processed.
 *
 *
 * ------------------------------------------------------------
 * 8. BASE CASE
 * ------------------------------------------------------------
 *
 * When all words have been considered:
 *
 *      if(idx >= words.length)
 *          return;
 *
 * There are no more decisions to make, so we return.
 *
 *
 * ------------------------------------------------------------
 * WHY BACKTRACKING IS NEEDED
 * ------------------------------------------------------------
 *
 * The important difficulty is that selecting a word consumes
 * letters that could have been used by another word.
 *
 * Therefore, selecting the word with the highest individual
 * score does NOT necessarily produce the maximum total score.
 *
 *
 * ------------------------------------------------------------
 * WHY GREEDY FAILS
 * ------------------------------------------------------------
 *
 * A natural greedy idea would be:
 *
 *      "Choose the highest-scoring word that can currently
 *       be formed."
 *
 * But this is NOT guaranteed to work.
 *
 * Consider:
 *
 *      Available letters: a b c
 *
 *      Word A = "abc"    -> score 20
 *      Word B = "ab"     -> score 15
 *      Word C = "c"      -> score 10
 *
 * A greedy strategy chooses:
 *
 *      "abc" -> 20
 *
 * and consumes all three letters.
 *
 * Total = 20
 *
 * But if we choose:
 *
 *      "ab" -> 15
 *      "c"  -> 10
 *
 * Total = 25
 *
 * Therefore:
 *
 *      20 < 25
 *
 * The locally best decision ("abc") is NOT the globally best
 * decision.
 *
 *
 * The problem has this dependency:
 *
 *      Choosing one word
 *              ↓
 *      consumes some letters
 *              ↓
 *      changes which other words can be selected
 *              ↓
 *      changes the final score
 *
 * Because of this dependency, we cannot safely make a
 * locally optimal choice.
 *
 *
 * ------------------------------------------------------------
 * INTUITION FOR BACKTRACKING
 * ------------------------------------------------------------
 *
 * Think of every word as a YES/NO decision:
 *
 *                     word[0]
 *                    /       \
 *                 TAKE      SKIP
 *                  /           \
 *              word[1]       word[1]
 *              /    \         /    \
 *            TAKE   SKIP    TAKE   SKIP
 *             ...    ...     ...    ...
 *
 * We explore all valid combinations and keep the maximum score.
 *
 * Since every word has two choices, the worst-case number of
 * combinations is O(2^n), where n = number of words.
 *
 * For each selected word, we may scan all of its characters,
 * so the actual complexity also depends on the total length
 * of the words.
 *
 * The important idea is:
 *
 *      GREEDY:
 *          Make the best-looking decision NOW.
 *
 *      BACKTRACKING:
 *          Try both possibilities and determine which gives
 *          the best result EVENTUALLY.
 */
class Solution{
    static int maxScore;
    public static void Solve(int idx, String[] words, char[] letters, int[] score, int currScore, int[] freq){
        maxScore = Math.max(maxScore, currScore);

        if(idx >= words.length){
            return;
        }

        int j=0;
        int[] tempFreq = freq.clone();
        int tempScore=0;
        while(j<words[idx].length()){
            char ch = words[idx].charAt(j);

            tempFreq[ch-'a']--;

            tempScore += score[ch-'a'];

            if(tempFreq[ch-'a']<0) break;

            j++;
        }
        if(j==words[idx].length()){
            Solve(idx+1, words, letters, score, currScore+tempScore, tempFreq);
        }

        Solve(idx+1, words, letters, score, currScore, freq);
    }    
    public int maxScoreWords(String[] words, char[] letters, int[] score){
        int[] freq=new int[26];
        for(int i=0;i<letters.length;i++){
            freq[letters[i]-'a']++;
        }
        maxScore = Integer.MIN_VALUE;
        Solve(0, words, letters, score, 0, freq);
        return maxScore;
    }
}
public class MaxScoreWords {
    public static void main(String[] args) {
        String[] words = {"dog","cat","dad","good"};
        char[] letters = {'a','a','c','d','d','d','g','o','o'}; 
        int[] score = {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};

        Solution obj = new Solution();
        int ans = obj.maxScoreWords(words, letters, score);
        System.out.println(ans);
    }
}
