
class MaxXORQuery{
    public int[] getMaximumXor(int[] nums,int maximumBit)
    {
        int n = nums.length;
        int[] res = new int[nums.length];
        int maxBit = (int) Math.pow(2,maximumBit);
        int maxValue = Integer.MIN_VALUE,ans=0,temp=0;
        int[] prefixXor= new int[n];
        prefixXor[0] = nums[0];
        for(int i=1;i<n;i++)
        {
            prefixXor[i] = prefixXor[i-1] ^ nums[i];
        }
        int m= n-1;
        for(int i=0;i<n;i++)
        {
            maxValue = Integer.MIN_VALUE;
            ans=0;temp=0;
            int k=0;
            while(k<maxBit)
            {
                temp = k ^ prefixXor[i];
                if(temp > maxValue)
                {
                    ans = k;
                    maxValue=temp;
                }
                k++;
            }
            res[m--]=ans;
        }
        return res;
    }
    public static void main(String[] args)
    {
        int[] nums={2,3,4,7};
        int maximumBit = 3;

        MaxXORQuery obj = new MaxXORQuery();
        int[] ans = obj.getMaximumXor(nums,maximumBit);
        for(int val : ans){
            System.out.print(val + " ");
        }
        System.out.println();
    }
}