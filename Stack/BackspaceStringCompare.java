import java.util.ArrayDeque;
import java.util.Deque;

class BackspaceStringCompare{
/*
    This method compares two strings after simulating backspace operations.

    1. Create two stacks (using Deque) to simulate typing for both strings.

    2. Traverse string 's':
    - If the current character is '#':
            → remove the top character from the stack (if not empty), simulating backspace
    - Else:
            → push the character onto the stack

    3. Traverse string 't' with the same logic:
    - '#' → pop from stack if possible
    - character → push into stack

    4. After processing both strings:
    - Each stack now contains the final characters after applying backspaces

    5. Convert both stacks into strings:
    - Repeatedly remove elements from the stack (from top to bottom)
    - Append them into StringBuilder objects

    6. Compare the resulting strings:
    - If both are equal → return true
    - Otherwise → return false

Overall idea:
Simulate typing with backspaces using stacks, then compare final results.
*/

    public boolean backspaceCompare(String s,String t)
    {
        Deque<Character> sStack = new ArrayDeque<>();
        Deque<Character> tStack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                if(!sStack.isEmpty()) 
                    sStack.pop();
            } else {
                sStack.push(s.charAt(i));
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '#') {
                if(!tStack.isEmpty())
                    tStack.pop();
            } else {
                tStack.push(t.charAt(i));
            }
        }
        StringBuilder sSB = new StringBuilder();
        StringBuilder tSB = new StringBuilder();
        while (!sStack.isEmpty()) {
            char top = sStack.removeFirst();
            sSB.append(top);
        }
        while (!tStack.isEmpty()) {
            char top = tStack.removeFirst();
            tSB.append(top);
        }
        if (sSB.toString().equals(tSB.toString()))
            return true;
        else
            return false;
    }

    public static void main(String[] args){
        String s = "ab##";
        String t = "c#d#";
        
        BackspaceStringCompare obj = new BackspaceStringCompare();
        boolean ans = obj.backspaceCompare(s,t);
        System.out.println(ans);
    }
    

}