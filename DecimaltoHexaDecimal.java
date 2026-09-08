import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DecimaltoHexaDecimal {
    static Map<Integer, Character> finder = new HashMap<>();
    static{
        finder.put(0,'0');
        finder.put(1,'1');
        finder.put(2,'2');
        finder.put(3,'3');
        finder.put(4,'4');
        finder.put(5,'5');
        finder.put(6,'6');
        finder.put(7,'7');
        finder.put(8,'8');
        finder.put(9,'9');
        finder.put(10,'A');
        finder.put(11,'B');
        finder.put(12,'C');
        finder.put(13,'D');
        finder.put(14,'E');
        finder.put(15,'F');
    }

    public static String findHexaDecimal(int q, int rem){
        if(q==0){
            return Character.toString(finder.get(rem));
        }

        return findHexaDecimal(q/16, q%16) + Character.toString(finder.get(rem));
    } 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Enter Digit:");
        int num = sc.nextInt();
        String ans = findHexaDecimal(num/16, num%16);
        System.out.println(ans);
    }
}
