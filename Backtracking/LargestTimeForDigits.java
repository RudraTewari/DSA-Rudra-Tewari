package Backtracking;

class Solution{
    static int result;
    public static boolean Solve(int[] nums, boolean[] visited, StringBuilder ans){
        if(ans.length()==4) return true;

        
        for(int i=3;i >= 0;i--){
            if(visited[i]) continue;

            ans.append(nums[i]);

            int len = ans.length();

            // Checking the Hour
            if(len == 2){
                int hour = Integer.parseInt(ans.substring(0, 2));

                if(hour > 23){
                    ans.deleteCharAt(ans.length()-1);
                    continue;
                }
            }


            //checking the Minute
            if (len == 4) {
                int minute = Integer.parseInt(ans.substring(2, 4));

                if (minute > 59) {
                    ans.deleteCharAt(ans.length() - 1);
                    continue;
                }
            }

            visited[i]=true;
            if(Solve(nums, visited, ans))
                return true;
            visited[i]=false;
            ans.deleteCharAt(ans.length()-1);
        }
        return false;
        
    }
    public String largestTimeFromDigits(int[] nums){
        result = Integer.MAX_VALUE;

        boolean[] visited = new boolean[nums.length];
        StringBuilder ans = new StringBuilder();
        

        return Solve(nums, visited, ans) ? ans.substring(0,2) +":"+ans.substring(2,4) : "";
    }
}
public class LargestTimeForDigits {
    public static void main(String[] args) {
        int[] nums = {0,0,0,0};

        Solution obj = new Solution();
        String ans = obj.largestTimeFromDigits(nums);

        System.out.println(ans);
    }
}
