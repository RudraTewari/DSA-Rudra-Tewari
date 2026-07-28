class SqrtX{
    /* Here we use Binary Search to solve the problem
    At first we find the mid between 1 and given n/2 then we 
    calculate the square of the mid 
    if the square is equal to n then we got the answer
    else if  square is less than n then we reduce the search space by moving lb to mid + 1
    else ub = mid - 1*/
    public int mySqrt(int n)
    {
        int lb=1,ub=n/2;
        
        while(lb<=ub)
        {
            int mid = lb+((ub-lb)/2);
            long square = (long) mid*mid;
            if(square == n)
            {
                return mid;
            }
            else if(square > n)
            {
                ub=mid-1;
            }
            else{
                lb=mid+1;
            }   
        } 
        return ub;
    }
    public static void main(String[] args)
    {
        int num=15;
        SqrtX obj = new SqrtX();
        int ans = obj.mySqrt(num);
        System.out.println(ans);
    }
}