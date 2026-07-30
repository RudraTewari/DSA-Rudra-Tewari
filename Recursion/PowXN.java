/*
    Exponentiation by Squaring Approach:

    1. Handle negative exponent:
        - If n is negative:
            x becomes 1/x
            n becomes positive
            Example:
            x^-3 = (1/x)^3

    2. Base Case:
        - If n == 0, return 1
        - Because any number raised to power 0 is 1

    3. Divide the problem:
        - Instead of calculating x^n directly,
            calculate x^(n/2) recursively
        - Store the result in "half"

    4. Use exponent rules:

        If n is EVEN:
        ----------------
       x^n = x^(n/2) * x^(n/2)
           = half * half

        Example:
        2^10 = (2^5)^2
            = 32 * 32
            = 1024


        If n is ODD:
        ----------------
        x^n = x * x^(n/2) * x^(n/2)
            = x * half * half

        Example:
        2^5 = 2 * (2^2)^2
            = 2 * 4 * 4
            = 32


    5. Recursion flow:

        n = 10
            |
            v
        n = 5
            |
            v
        n = 2
            |
            v
        n = 1
            |
            v
        n = 0  ---> return 1


    6. Returning phase:
        - Calculate answer while coming back from recursion

        2^0 = 1
       2^1 = 2 * 1 * 1 = 2
       2^2 = 2 * 2 = 4
       2^5 = 2 * 4 * 4 = 32
       2^10 = 32 * 32 = 1024


    7. Memory Trick:
        "HALVE while going down, SQUARE while coming up"

    - Divide exponent by 2 every recursive call
    - Square the half result
    - If exponent is odd, multiply one extra x


    8. Time Complexity:
    - Every step reduces n by half
    - Number of calls = log(n)
    - Time Complexity: O(log n)
    - Space Complexity: O(log n) due to recursion stack
*/

class PowXN{
    private static double findPower(double x, int n){
        if(n==0){
            return 1;
        }
        double half = findPower(x,n/2);

        if(n%2==0){
            half = half * half;
        }else{
            half = x * half * half;
        }
        return half;
    }
    public static double powXN(double x,int n){
        if(n<0){
            x=1/x;
            n=-n;
        }
        return findPower(x,n);
    }
    public static void main(String[] args){
        double x = 2.0;
        int n=10;

        double ans = powXN(x,n);
        System.out.println(ans);
    }
}