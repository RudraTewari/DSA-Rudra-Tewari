import java.lang.Math;

class MostWater{
    public int maxArea(int[] height)
    {
        int lb=0, ub=height.length-1, ans=Integer.MIN_VALUE, result;
        int length, breadth;
        while(lb<ub)
        {
            length = Math.min(height[lb],height[ub]);
            breadth = ub-lb;
            result = length * breadth;

            ans = Math.max(ans,result);

            if(height[lb] < height[ub]) lb++;
            else ub--;

            
        }
        return ans;
    }
    public static void main(String[] args)
    {
        int[] height={1,8,6,2,5,4,8,3,7};

        MostWater obj = new MostWater();
        int ans = obj.maxArea(height);

        System.out.println(ans);
    }
}