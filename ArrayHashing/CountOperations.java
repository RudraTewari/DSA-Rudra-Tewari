import java.lang.Math;

class CountOperations{
    public int[] minOperations(String boxes)
    {
        int[] ans = new int[boxes.length()];
        
        for(int i=0;i<boxes.length();i++)
        {
            int count=0;
            for(int j=0;j<boxes.length();j++)
            {
                if(boxes.charAt(j) == '1' && i!=j)
                {
                    
                    count += Math.abs(i-j);
                }
            }           
            ans[i] = count;
        }
        return ans;
    }
    public static void main(String[] args)
    {
        String boxes = "110";
        CountOperations obj = new CountOperations();
        int[] ans = obj.minOperations(boxes);
        for(int i=0;i<ans.length;i++)
        {
            System.out.print(ans[i]+" ");
        }
        System.out.println();
    }
}