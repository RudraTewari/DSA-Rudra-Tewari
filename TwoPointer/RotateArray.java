class RotateArray{
    
    public void reverse(int[]nums, int lb, int ub)
    {
        while(lb<ub)
        {
            int temp=nums[lb];
            nums[lb]=nums[ub];
            nums[ub]=temp;
            lb++;
            ub--;
        }
    }
    public void rotate(int[] nums, int k)
    {
        k=k%nums.length;  //To ensure if K value is greater than nums.length then we again round it of to value less than nums.length 
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }
    public static void main(String[] args)
    {
        int[] nums={1,2,3,4,5,6,7};
        int k=9;
        RotateArray obj = new RotateArray();
        obj.rotate(nums,k);

        for(int val : nums)
        {
            System.out.print(val+" ");
        }
        System.out.printf("\n");
    }
}