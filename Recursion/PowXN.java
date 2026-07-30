/*
    Exponentiation by Squaring Approach:

    1. Handle negative exponent:
        - If n is negative:
            x becomes 1/x
            n becomes positive
            Example:
            x^-3 = (1/x)^3

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

    If n is EVEN:
--------------------------------------
        x^n = x^(n/2) * x^(n/2)
           = half * half

        Example:
        2^10 = (2^5)^2
            = 32 * 32
            = 1024


    If n is ODD:
--------------------------------------
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


    8. Time Complexity:Thus, instead of multiplying n 'exp' times, we repeatedly divide the exponent
    by 2, making the algorithm run in O(log exp) time.
    
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

        double result = 1;
        result = half * half;
        if(n%2==1){
            result = result * x;
        }
        
        return result;
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