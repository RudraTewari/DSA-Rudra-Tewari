package Backtracking;

import java.util.List;
import java.util.ArrayList;

class Solution{
    static int count;
    public static void Solve(List<Integer> p, int[] up, int idx, int k){
        if(idx>=up.length){
            if(p.size()==0) return;

            if(p.size()==1){
                count++;
            }else{
                boolean flag=true;
                for(int i=0;i<p.size();i++){
                    for(int j=i+1;j<p.size();j++){
                        if(Math.abs(p.get(i)-p.get(j))==k)
                            flag=false;
                    }
                    if(!flag)
                        break;
                }
                if(flag)
                    count++;
            }
            return;
        }
        p.add(up[idx]);
        Solve(p, up, idx+1, k);
        p.remove(p.size()-1);
        Solve(p, up, idx+1, k);
    }
    public int beautifulSubsets(int[] nums, int k){
        count=0;
        Solve(new ArrayList<>(), nums, 0, k);
        return count;
    }
}
public class BeautifulSubsets {
    public static void main(String[] args) {
        int[] nums = {3,4,2};
        Solution obj = new Solution();
        int ans = obj.beautifulSubsets(nums, 1);
        System.out.println(ans);
    }
}
