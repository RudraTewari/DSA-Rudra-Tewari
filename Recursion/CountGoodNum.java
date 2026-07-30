/*
========================================
Idea
========================================
A good number is formed such that:
    1. Digits at even indices (0, 2, 4, ...) are even.
        Possible choices = {0, 2, 4, 6, 8} = 5 choices.

    2. Digits at odd indices (1, 3, 5, ...) are prime.
        Possible choices = {2, 3, 5, 7} = 4 choices.

    If the length of the number is n:
    - Number of even positions = (n + 1) / 2
    - Number of odd positions  = n / 2

    Therefore, the total number of good numbers is:
        5^((n + 1) / 2) × 4^(n / 2)

    Since n can be as large as 10^15, directly computing these powers is impossible.
    Also, the answer can be extremely large, so all calculations are performed
    modulo 1,000,000,007.

========================================
Approach
========================================
    1. Count the number of even and odd positions.
    2. Compute:
            5^evenPositions mod M
            4^oddPositions mod M
        using Binary Exponentiation (Fast Power).
    3. Multiply both results and take modulo M.

    Binary Exponentiation (findPower):

    1. Base Case:
        if (exp == 0)
            return 1;

    2. Divide the problem into half:
        long half = findPower(n, exp / 2);

    3. Square the half answer:
        long res = (half * half) % M;

    4. If the exponent is odd, multiply one extra 'n':
        if (exp % 2 == 1)
            res = (res * n) % M;

    5. Return the final answer:
        return res;

    Why does this work?

    Even exponent:
    n^8
    = (n^4)^2
    = ((n^2)^2)^2

    Odd exponent:
    n^9
    = n × (n^4)^2

    Thus, instead of multiplying n 'exp' times, we repeatedly divide the exponent
    by 2, making the algorithm run in O(log exp) time.
========================================
Time Complexity
========================================
    O(log n)

========================================
Space Complexity
========================================
    O(log n)   // Recursive call stack
*/
public class CountGoodNum {
    static long M = 1000000007;
    public long findPower(int n,long exp){
        if(exp==0){
            return 1;
        }
        long half = findPower(n,exp/2);

        long res = (half * half)%M;

        if(exp%2==1){
            res=(res*n)%M;
        }
        return res;
    }

    public int countGoodNumbers(long n) {
        return (int) ((findPower(5,(n+1)/2) * findPower(4,n/2)) % M);
    }


    public static void main(String[] args) {

        CountGoodNum solution = new CountGoodNum();

        // Sample Input
        long n = 4;

        // Function Call
        int result = solution.countGoodNumbers(n);

        // Output
        System.out.println("Output: " + result);

        /*
         * Sample Input:
         * n = 4
         *
         * Expected Output:
         * 400
         */
    }
}