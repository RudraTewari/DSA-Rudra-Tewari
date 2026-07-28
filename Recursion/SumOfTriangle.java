public class SumOfTriangle{
    public static void sumofTriangle(int[] nums){
        if(nums.length==1){
            System.out.println(nums[0]);
            return;
        }
        int[] ans = new int[nums.length-1];
        for(int i=0;i<nums.length-1;i++){
            ans[i]=nums[i]+nums[i+1];
        }
        // System.out.println("Yes1");
        sumofTriangle(ans);
        // System.out.println("Yes2");
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        int[] a = {1,2,3,4,5};
        sumofTriangle(a);
    }
}