
class MinDeletionsBalancedString{
    /*
    APPROACH (Minimum Deletions to Make String Balanced)

    Goal:
    Make the string balanced such that all 'a' come before all 'b'.
    (i.e., no "ba" pattern should exist)

    Core Idea:
    We try every possible "split point" in the string.
    At each index i:
    - Left side [0 → i-1] should contain only 'a'
    - Right side [i → n-1] should contain only 'b'

    So at each position i, we calculate:
    1. Number of 'b' on the LEFT → these must be deleted
    2. Number of 'a' on the RIGHT → these must be deleted

    Total deletions at index i:
        deletions = countB[i] + countA[i]

    --------------------------------------------------

    Step 1: Build countB[]
    countB[i] = number of 'b' characters before index i
    → represents wrong characters on the LEFT side

    Step 2: Build countA[]
    countA[i] = number of 'a' characters after index i
    → represents wrong characters on the RIGHT side

    Step 3: Try all split points
    For each index i:
        compute countB[i] + countA[i]
        take minimum of all

    --------------------------------------------------

    Why this works:
    Instead of deleting characters arbitrarily,
    we fix a split and count how many are out of place.

*/
/*    public int minimumDeletions(String s)
    {
        int[] ACount = new int[s.length()];
        int[] BCount = new int[s.length()];

        int aCount = 0;
        for(int i=s.length()-1;i>=0;i--)
        {
            ACount[i] = aCount;
            if(s.charAt(i) =='a') 
                aCount++;
        }

        int bCount = 0;
        for(int i=0;i<s.length();i++)
        {
            BCount[i] = bCount;
            if(s.charAt(i) =='b') 
                bCount++;
        }
        int minDeletions = s.length();
        for(int i=0;i<s.length();i++)
        {
            minDeletions = Math.min(minDeletions,ACount[i] + BCount[i]);
        }
        return minDeletions;
    }
    */
    /*
    APPROACH (Greedy / Stack Simulation)

    Goal:
    Make the string balanced such that all 'a' come before all 'b'
    → no "ba" pattern should exist.

    Core Idea:
    Whenever we see a "ba" pattern, one character must be deleted.
    This solution greedily removes such violations using a stack.

    --------------------------------------------------

    How it works:

    We simulate building a valid string using a stack.

    1. Traverse the string from left → right.

    2. For each character:
    
    Case 1:
    If stack top is 'b' and current char is 'a'
    → we found a "ba" violation

    Now we have two choices:
    - Delete current 'a'
    - OR delete previous 'b'

    Greedy choice:
    → Delete the 'b' (pop from stack)

    Why?
    Because removing earlier 'b' avoids future conflicts with more 'a's.

    So:
        pop stack
        increment deletion count

    --------------------------------------------------

    Case 2:
    Otherwise:
    → safe to keep character
    → push it into stack

--------------------------------------------------------

    Final Answer:
    Total number of deletions performed.

--------------------------------------------------------

    Why this works:
    - We fix violations immediately when they occur
    - Removing 'b' early prevents multiple future "ba" conflicts
    - Equivalent to minimizing total deletions
*/
    public int minimumDeletions(String s)
    {
        int[] stack = new int[s.length()];
        int top=-1;
        int minDeletions=0;
        for(int i=0; i< s.length();i++)
        {
            if(top!=-1 && stack[top] == 'b' && s.charAt(i) == 'a')
            {
                top--;
                minDeletions++;
            }else
                stack[++top] = s.charAt(i);
        }
        return minDeletions;
    }
    public static void main(String[] args)
    {
        String s = "aababbab";
        MinDeletionsBalancedString obj = new MinDeletionsBalancedString();
        int ans = obj.minimumDeletions(s);
        System.out.println(ans);
    }
}