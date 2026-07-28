class RemoveElement{
    public int removeElement(int[] nums,int val)
    {
        int count=0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]!=val)
            {
                nums[count]=nums[i];
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args){
        int[] nums ={0,1,2,2,3,0,4,2};
        int val = 2;

        RemoveElement obj = new RemoveElement();
        int ans = obj.removeElement(nums,val);
        System.out.println(ans);
    }
}