/* Here the logic should be to check whether I can make the entire array even or odd
By tracking the previous parity*/ 


class UniformParityArrayII{
    public boolean check(int[] nums,int target)
    {
        if(nums[0] & 1 != target) return false;
        for(int i=1;i<nums.length;i++)
        {
            int curr = nums[i] & 1;
            int diff = (nums[i] - nums[i-1]) & 1;
            if(curr==target || diff==target)
                continue;
            else
                return false;
        }
        return true;
    }
    public boolean uniformArray(int[] nums){
        return check(nums,0) || check(nums,1);
    }
    public static void main(String[] args)
    {
        int[] nums={1,4,7};
        UniformParityArrayII obj = new UniformParityArrayII();
        boolean ans = obj.uniformArray(nums);
        System.out.println("Answer :" + ans);
    }
}