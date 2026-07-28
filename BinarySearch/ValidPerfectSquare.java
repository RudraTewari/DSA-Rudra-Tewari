class ValidPerfectSquare{
/*
Logic: Check if a number is a perfect square using Binary Search

1. Handle edge case:
    - If num == 1 → return true (since 1 is a perfect square).

2. Define search space:
- Square root of num lies between 1 and num/2.
- So set:
    left = 1
    right = num/2

3. Apply Binary Search on possible square root:
    - While left <= right:
        mid = middle of left and right
        prod = mid * mid

4. Compare square with target:
    - If prod == num → mid is exact square root → return true
    - If prod > num → mid is too large → search left half (right = mid - 1)
    - If prod < num → mid is too small → search right half (left = mid + 1)

5. If loop ends:
    - No integer square root found → return false
*/
    public boolean isPerfectSquare(int num)
    {
        if(num==1) return true;
        long left = 1;
        long right = num/2;
        while(left <= right)
        {
            long mid = left+(right -left)/2;
            long prod = mid*mid;
            if(prod==num)
                return true;
            else if(prod > num)
                right= mid-1;
            else 
                left=mid+1;
        }
        return false;
    }
    public static void main(String[] args)
    {
        int num=9;
        ValidPerfectSquare obj = new ValidPerfectSquare();
        boolean ans = obj.isPerfectSquare(num);
        System.out.println("Answer = "+ans);
    }
}