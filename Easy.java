
class Easy{
    public static int findMin(int[] nums,int target){
        int lb=0,ub=nums.length-1;
        int ans = 0;
        while(lb<=ub)
        {
            int mid = lb+(ub-lb)/2;

            if(nums[mid] >= target){
                ans=mid;
                ub=mid-1;
            }else{
                lb=mid+1;
            }
            
        }
        return ans;
    }
    public static void main(String[] args)
    {
        int[] nums={1,2,3};
        int target=0;
        // Easy obj = new Easy();
        int ans = findMin(nums,target);
        System.out.println(ans);
    }
}