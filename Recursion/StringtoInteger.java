public class StringtoInteger {

    public static void main(String[] args) {

        StringtoInteger obj = new StringtoInteger();

        // Sample Input
        String s = "   -42";

        // Function Call
        int result = obj.myAtoi(s);

        // Output
        System.out.println("Output: " + result);
    }


    public int myAtoi(String s) {

        s=s.trim();
        if(s.isEmpty()){
            return 0;
        }
        int sign=1;
        int i=0;
        if(s.charAt(i)=='-'||s.charAt(i)=='+'){
            sign = (s.charAt(i)=='-' ? -1 : 1);
            i++;
        }
        long num =0;
        while(i<s.length() && Character.isDigit(s.charAt(i))){
            int digit = s.charAt(i)-'0';

            if(num > (Integer.MAX_VALUE - digit) /10){
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            num = num * 10 + digit;
            i++;
        }
        return (int) num * sign;
    }
}