public class FindkthCharacterinBinaryString {/*
    Problem: Find Kth Character in Nth Binary String

    IDEA:
    -----
    The binary string is generated recursively:

        S(n) = S(n-1) + "1" + reverse(invert(S(n-1)))

    Example:
        S(1) = 0
        S(2) = 011
        S(3) = 0111001
        S(4) = 011100110110001

    We do NOT actually build the string because its length is:
        2^n - 1

    Building the entire string would take O(2^n) time.

    Instead, we recursively find the Kth character.

    ---------------------------------------------------------
    IMPORTANT OBSERVATION:
    ---------------------------------------------------------

    For S(n):

        Length = 2^n - 1

        Middle position = length / 2 + 1

    The string has three parts:

        S(n-1) + "1" + reverse(invert(S(n-1)))

                    ↑
                 middle

    Therefore, for position K:

    1. If K is in the LEFT HALF:
           K <= mid

       The character is directly inside S(n-1).

       So:
           Solve(n-1, K)


    2. If K is the MIDDLE:
           K == mid + 1

       The middle character is always '1'.

       So:
           return 1


    3. If K is in the RIGHT HALF:

       The right half is:

           reverse(invert(S(n-1)))

       We need to map K back to its corresponding position
       in the left half.

       Corresponding position:

           len - (K - 1)
           = len - K + 1

       Then we recursively find that character in S(n-1).

       BUT the right half is inverted.

       Therefore:

           0 becomes 1
           1 becomes 0

       So we return:

           1 - Solve(n-1, len-(K-1))


    ---------------------------------------------------------
    KEY PATTERN TO REMEMBER:
    ---------------------------------------------------------

        LEFT   → solve normally
        MIDDLE → always 1
        RIGHT  → map position + invert answer

        RIGHT:
            newK = len - K + 1
            answer = 1 - Solve(n-1, newK)


    BASE CASE:
    ----------
    S(1) = "0"

    Therefore, when n == 1:
        return 0


    ---------------------------------------------------------
    COMPLEXITY:
    ---------------------------------------------------------

    We reduce n by 1 in every recursive call.

    Time  : O(n)
    Space : O(n)   // recursion stack

    We avoid constructing the O(2^n)-sized string.


    ---------------------------------------------------------
    QUICK REVISION:
    ---------------------------------------------------------

    Remember these 3 things:

        1. Length = 2^n - 1

        2. Middle = 1

        3. Right side = reverse + invert
           → map position back
           → invert the answer

    This is a classic "recursive structure + position mapping"
    problem.
*/


    static int Solve(int n,int k){
        if(n==1) 
            return 0;
        int len = (int) Math.pow(2,n)-1;
        int mid = len/2;

        if(k<=mid){
            return Solve(n-1,k);
        }else if(k==mid+1){
            return 1;
        }else{
            return 1-Solve(n-1,len-(k-1));
        }
    }
    public static void main(String[] args) {
        int n=4,k=11;
        int ans = Solve(n,k);
        System.out.println(ans);
    }
    
}
