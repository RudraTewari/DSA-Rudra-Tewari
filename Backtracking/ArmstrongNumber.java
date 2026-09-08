package Backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ArmstrongNumber {
    static List<Long> ans;
    static int range;
    public static void check(List<Integer> combination, long sum, int digit){
        long temp = sum;
        int[] freq = new int[10];
        for(int i : combination){
            freq[i]++;
        }

        for(int i=0; i < digit; i++){
            freq[(int) (temp % 10)]--;
            temp/=10;
        }

        for(int i=0;i<10;i++){
            if(freq[i]!=0) return;
        }

        if(sum >= Math.pow(10,digit-1) && sum <= range) ans.add(sum);
    }
    public static void find(int digit, int ele, int count, List<Integer> combination, long sum){
        if(count==0){
            check(combination, sum, digit);
            return;
        }

        if(ele > 9) return;
        
        combination.add(ele);
        find(digit, ele, count-1, combination, sum + (long)Math.pow(ele, digit));

        combination.remove(combination.size()-1);
        find(digit, ele+1, count, combination, sum);
    }
    public static void findArmstrongNumbers(){
        for(int digit=1; digit<=9; digit++){
            find(digit, 1, digit, new ArrayList<>(), 0);
        }
        Set<Long> set = new TreeSet<>();
        for(Long num : ans){
            set.add(num);
        }
        for(Long val : set){
            System.out.println(val);
        }
    }
    public static void main(String[] args) {
        range = 999999;
        ans = new ArrayList<>();
        findArmstrongNumbers();
    }
}
