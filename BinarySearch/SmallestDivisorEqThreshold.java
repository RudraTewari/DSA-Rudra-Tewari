
class SmallestDivisorEqThreshold{
    public static boolean canDo(int divisor, int[] nums,int threshold){
        int sum=0,result=0;
        for(int num : nums){
            sum += (num + divisor -1)/divisor;
            System.out.println("Result:"+sum);
        }
        System.out.println("Sum:"+sum);
        return sum <= threshold;
    }
    public int smallestDivisor(int[] nums, int threshold){
        int low = 1,high = Integer.MIN_VALUE;
        for(int val : nums){
            high = Math.max(high,val);
        }
        int ans = -1,mid=0;
        while(low<=high){
            mid=(low+high) >> 1;
            System.out.println("Mid: "+mid);
            if(canDo(mid,nums,threshold)){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }
    public static void main(String[] args){
        int[] nums={1,2,5,9};
        int threshold = 6;
        SmallestDivisorEqThreshold obj = new SmallestDivisorEqThreshold();
        int ans = obj.smallestDivisor(nums,threshold);
        System.out.println(ans);
    }
}