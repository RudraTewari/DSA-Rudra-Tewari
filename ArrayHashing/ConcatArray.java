
public class ConcatArray {

    public int[] getConcatenation(int[] nums)
    {
        int[] ans=new int[2*nums.length];
        for(int i=0;i<nums.length;i++)
        {
            ans[i]=nums[i];
            ans[i+nums.length]=nums[i];
        }
        return ans;
    }

    public static void main(String[] args){
        int[] nums = {1,2,1};

        ConcatArray obj = new ConcatArray();
        int[] ans = obj.getConcatenation(nums);

        for(int val : ans)
        {
            System.out.print(val+" ");
        }

    }
}
