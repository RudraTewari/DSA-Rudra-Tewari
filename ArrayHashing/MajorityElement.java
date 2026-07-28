class MajorityElement{
    
    public int majorityElement(int[] nums)
    {
        int ans=nums[0],freq=1;
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]!=ans)
            {
                freq--;
                if(freq==0){
                    ans=nums[i];
                    freq=1;
                }  
            } 
            else
            {
                freq++;
            }           
        }
        return ans;
    }
    public static void main(String[] args)
    {
        int[] nums={3,2,3};
        MajorityElement obj = new MajorityElement();
        int ans = obj.majorityElement(nums);
        System.out.println(ans);
    }
}