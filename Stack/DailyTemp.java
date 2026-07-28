class DailyTemp{
/*    public int[] dailyTemperatures(int[] temperatures)
    {
        int[] ans = new int[temperatures.length];
        for(int i=0;i<temperatures.length;i++)
        {
            int count=0;
            for(int j=i+1;j<temperatures.length;j++)
            {
                if(temperatures[i] < temperatures[j]){
                    count++;
                    ans[i]=count;
                    break;
                }else{
                    count++;
                }                 
            }
        }
        return ans;
    } */
    /*
    Goal:
    For each day, find how many days you have to wait until a warmer temperature.
    If no such day exists, return 0.

    Key Idea:
    We process the array from RIGHT → LEFT and maintain a stack of indices.
    The stack will store indices of days in such a way that their temperatures
    are in strictly decreasing order from bottom to top (Monotonic Decreasing Stack).

    Why Right to Left?
    Because for each day, we want to know the "next greater element on the right".

    Steps:
    1. Initialize:
    - stack[] to store indices
    - top = -1 (empty stack)

    2. Traverse from i = n-1 → 0:
    a) Pop all indices from stack whose temperature is <= current temperature
        → because they can never be the next warmer day for current or any previous day.
    
    b) If stack is empty:
            ans[i] = 0 (no warmer day exists)
        Else:
            ans[i] = stack[top] - i (distance to next warmer day)

    c) Push current index i into stack

    3. Return ans[]
    */ 
    public int[] dailyTemperatures(int[] temperatures)
    {
        int n = temperatures.length;
        int[] ans = new int[n];
        int[] stack = new int[n];
        int top=-1;

        for(int i=n-1;i>=0;i--)
        {
            while(top != -1 && temperatures[stack[top]] <= temperatures[i])
                top--;
            
            ans[i] = (top==-1) ? 0 : stack[top]-i;

            stack[++top] = i;
        }
        return ans;
    }
    public static void main(String[] args)
    {
        int[] temperatures={30,40,50,60};
        DailyTemp obj = new DailyTemp();
        int[] ans = obj.dailyTemperatures(temperatures);
        for(int val : ans)
        {
            System.out.print(val + " ");
        }
        System.out.printf("\n");
    }

}