package Backtracking;

import java.util.HashSet;
import java.util.Set;

class Solution {

    static Set<String> ans;  //Stores all unique sequences generated during backtracking
    static int totalCount;
    public static void Solve(StringBuilder p, String tiles, boolean[] used) {

        ans.add(p.toString());  //Add the current sequence to the Set; duplicates are automatically removed
        for (int i = 0; i < tiles.length(); i++) { // Try choosing every tile one by one

            if (used[i]) continue;  //If this tile is already used in the current sequence, skip it

            used[i] = true;  //Mark the current tile as used
            p.append(tiles.charAt(i));  //Add the current tile's character to the sequence

            Solve(p, tiles, used); // Recursively try adding more characters

            used[i] = false; // Backtrack: mark the current tile as unused again
            p.deleteCharAt(p.length() - 1); // Backtrack: remove the last character from the sequence
        }
    }
    public static void SolveAlternative(int[] freq){
        totalCount++;
        for(int i=0;i<26;i++){
            if(freq[i]==0) continue;
            freq[i]--;
            SolveAlternative(freq);
            freq[i]++;
        }
    }

    public int numTilePossibilities(String tiles) {

        ans = new HashSet<>(); // Create a HashSet to store only unique sequences
        boolean[] used = new boolean[tiles.length()]; // Tracks whether each tile is currently being used
        StringBuilder sb = new StringBuilder(); // Creates an initially empty sequence
        Solve(sb, tiles, used);  //Start the backtracking process
        return ans.size() - 1;// Remove the empty string and return the number of non-empty sequences
    } 
    public int numTilePossibilitiesII(String tiles) {
        int[] freq= new int[26];
        totalCount=0;
        for(int i=0;i<tiles.length();i++){
            freq[tiles.charAt(i)-'A']++;
        }
        SolveAlternative(freq);
        return totalCount-1;
    }
}
public class LetterTile {
    public static void main(String[] args) {
        String tiles = "AAB";

        Solution obj = new Solution();
        int ans = obj.numTilePossibilities(tiles);
        int ansI = obj.numTilePossibilitiesII(tiles);
        System.out.println("Answer : "+ans);
        System.out.println("Answer 1 : "+ansI);

    }
}
