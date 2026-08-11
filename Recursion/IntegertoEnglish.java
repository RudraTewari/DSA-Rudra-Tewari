import java.util.*;

public class IntegertoEnglish {
    static Map<Integer,String> belowTen = new HashMap<>();
    static Map<Integer,String> belowTwenty = new HashMap<>();
    static Map<Integer,String> belowHundred = new HashMap<>();

    static{
        belowTen.put(1,"One");
        belowTen.put(2,"Two");
        belowTen.put(3,"Three");
        belowTen.put(4,"Four");
        belowTen.put(5,"Five");
        belowTen.put(6,"Six");
        belowTen.put(7,"Seven");
        belowTen.put(8,"Eight");
        belowTen.put(9,"Nine");

        belowTwenty.put(10,"Ten");
        belowTwenty.put(11,"Eleven");
        belowTwenty.put(12,"Twelve");
        belowTwenty.put(13,"Thirteen");
        belowTwenty.put(14,"Fourteen");
        belowTwenty.put(15,"Fifteen");
        belowTwenty.put(16,"Sixteen");
        belowTwenty.put(17,"Seventeen");
        belowTwenty.put(18,"Eighteen");
        belowTwenty.put(19,"Nineteen");
        
        belowHundred.put(1, "Ten");
        belowHundred.put(2, "Twenty");
        belowHundred.put(3, "Thirty");
        belowHundred.put(4, "Forty");
        belowHundred.put(5, "Fifty");
        belowHundred.put(6, "Sixty");
        belowHundred.put(7, "Seventy");
        belowHundred.put(8, "Eighty");
        belowHundred.put(9, "Ninety");

    }
    public static String solve(int num){
        if(num<10){
            return belowTen.get(num);
        }
        if(num<20){
            return belowTwenty.get(num);
        }
        if(num<100){
            return belowHundred.get(num/10)+" "+((num%10!=0)?solve(num%10):"");
        }
        if(num<1000){
            return solve(num/100)+" Hundred"+((num%100!=0)?" "+solve(num%100):"");
        }
        if(num<1000000){
            return solve(num/1000)+" Thousand"+((num%1000!=0)?" "+solve(num%1000):"");
        }
        if(num<1000000000){
            return solve(num/1000000)+" Million"+((num%1000000!=0)?" "+solve(num%1000000):"");
        }
        return solve(num/1000000000)+" Billion"+((num%1000000000!=0)?" "+solve(num%1000000000):"");

    }
    public static String numberToWords(int num) {
        if(num==0){
            return "Zero";
        }
        return solve(num);
    }

    public static void main(String[] args) {
        int num = 11112345;

        String result = numberToWords(num);

        System.out.println(result);
    }
}