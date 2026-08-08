public class FindKthSymbolinGrammar {
    // This is correct but gives TLE for the given constraint
    // public static StringBuilder Solve(int n){
    //     if(n==0){
    //         return new StringBuilder("0");
    //     }
    //     StringBuilder str = Solve(n-1);
    //     for (int i = str.length()-1; i >= 0; i--) {
    //         if(str.charAt(i)=='0'){
    //             str.replace(i,i+1, "01");
    //         }
    //         if(str.charAt(i)=='1'){
    //             str.replace(i,i+1, "10");
    //         }
    //     }
    //     return str;
    // }
    /*
    Problem: K-th Symbol in Grammar

    Logic:
    --------
    Each row is formed from the previous row using:

        0 -> 01
        1 -> 10

    Therefore, every row has length:
        2^(n-1)

    Instead of actually constructing the whole row, we find the
    position of k recursively.

    IMPORTANT TRICK:
    ----------------
    We divide the current row into two halves.

        First half  -> comes directly from row (n-1)
        Second half -> is the inverted version of row (n-1)

    Example:

        Row 3 = 0110

                  [01] [10]
                   ↑     ↑
                first  second
                half    half

    So:

        if k <= mid:
            k lies in the first half
            -> solve the same position in row n-1

        if k > mid:
            k lies in the second half
            -> corresponding position is in row n-1
            -> but the symbol is inverted

            0 becomes 1
            1 becomes 0

            Therefore:
                1 - Solve(...)

    Why k - mid?
    -------------
    If k is in the second half, we need to convert its position
    into the corresponding position inside the first half.

        position in second half = k - mid

    Example:
        length = 8
        mid = 4
        k = 7

        Second half positions are:
            5 6 7 8

        Their corresponding positions are:
            1 2 3 4

        So:
            7 - 4 = 3


    Base Case:
    ----------
    When n == 1, the row is:

        0

    Therefore the answer is always 0.


    Example:
    --------
    Solve(4, 6)

    Row 4 has length 8
    mid = 4

    k = 6 > 4
    -> second half
    -> invert Solve(3, 2)

    Solve(3, 2):
    length = 4
    mid = 2

    k = 2 <= 2
    -> first half
    -> Solve(2, 2)

    Solve(2, 2):
    length = 2
    mid = 1

    k = 2 > 1
    -> second half
    -> invert Solve(1, 1)

    Solve(1, 1) = 0

    Now return:
        1 - 0 = 1

    So the answer is 1.


    KEY TRICKS TO REMEMBER:
    -----------------------
    1. Don't construct the entire grammar.
       The row size becomes 2^(n-1), which can become huge.

    2. Divide the row into two halves.

    3. First half:
           same value as previous row.

       Second half:
           inverted value of previous row.

    4. For the second half, convert k to its corresponding
       position using:

           k = k - mid

    5. Inversion can be done very easily using:

           1 - value

       because:
           1 - 0 = 1
           1 - 1 = 0

    6. The recursion reduces n by 1 at every call, so we only
       follow ONE path instead of generating the entire row.
*/
    static int Solve(int n, int k){
        if(n==1){
            return 0;
        }

        int len = (int) Math.pow(2,n-1);
        int mid = len/2;

        if(k<=mid){
            return Solve(n-1,k);
        }else{
            return 1-Solve(n-1,k-mid);
        }

    }
    public static void main(String[] args) {
        int n=3,k=3;
        // StringBuilder str = Solve(n-1);

        // System.out.println(str.toString());
        // System.out.println(str.charAt(2));
        int ans = Solve(n,k);
        System.out.println(ans);
    }
}
