import java.util.ArrayList;
import java.util.List;
/*
    Approach:

    We are given a string containing digits from 2 to 9, where each digit
    represents a set of characters similar to a phone keypad.

    For example:
    2 -> abc
    3 -> def
    4 -> ghi
    ...
    7 -> pqrs
    8 -> tuv
    9 -> wxyz

    We use recursion to generate every possible combination.

    1. `p` represents the processed part:
    It stores the characters chosen so far to form the current combination.

    2. `up` represents the unprocessed part:
    It contains the digits that are still left to process.

    3. Base Case:
    When `up` becomes empty, it means we have selected one character
    for every digit and formed a complete combination.

    if (up.isEmpty()) {
        ans.add(p);
        return;
    }

    4. Get the current digit:

    int digit = up.charAt(0) - '0';

    We subtract '0' to convert the character digit into its integer value.

    5. Find the range of characters mapped to the current digit.

    For digits 2 to 6, every digit contains exactly 3 characters:

    2 -> indices 0 to 2  -> abc
    3 -> indices 3 to 5  -> def
    4 -> indices 6 to 8  -> ghi

    Therefore:

    start = (digit - 2) * 3;
    end = (digit - 1) * 3;

    Digits 7 and 9 contain 4 characters, so their ranges are handled
    separately to maintain the correct character mapping.

    6. Try every possible character for the current digit:

    for (int i = start; i < end; i++) {
        char ch = (char) ('a' + i);

        pad(p + ch, up.substring(1), ans);
    }

    Here, each recursive call chooses one possible character and adds it
    to the processed string `p`.

    At the same time, up.substring(1) removes the current digit because
    we have already processed it.

    7. Recursion automatically explores every possible choice.

    Example: up = "23"

    For digit 2:
        choose 'a' -> process digit 3
        choose 'b' -> process digit 3
        choose 'c' -> process digit 3

    For 'a':
        a + d -> "ad"
        a + e -> "ae"
        a + f -> "af"

    Similarly, all other branches are explored, giving:

        ad, ae, af,
        bd, be, bf,
        cd, ce, cf

    Thus, at every recursive level, we make one choice from the characters
    mapped to the current digit. Once all digits are processed, the complete
    combination is added to the answer list.
*/
public class LetterCombination {
    public static void pad(String p, String up, List<String> ans){
        if(up.isEmpty()){
            ans.add(p);
            return;
        }

        int digit = up.charAt(0)-'0';
        int start=0,end=0;

        if(digit<=6){
            start=(digit-2)*3;
            end =(digit-1)*3;
        }else if(digit==7){
            start = 15;
            end=19;
        }else if(digit == 8){
            start = 19;
            end=22;
        }else{
            start = 22;
            end=26;
        }

        for(int i=start; i<end;i++){
            char ch = (char)('a'+i);

            pad(p+ch, up.substring(1),ans);
        }
    }
    public static void main(String[] args) {
        String p ="";
        String up="1234";
        List<String> ans = new ArrayList<>();
        pad(p,up,ans);
        ans.forEach(val->{
            System.out.print(val+" ");
        });
        System.out.println();
    }
    
}
