package CodeChef;

import java.util.*;

public class BearCandy{
    public static void findWinner(int limakLimit, int bobLimit){
        int maxLimit = limakLimit+bobLimit;
        int limakAte=0,bobAte=0;
        for(int i=1;i<=maxLimit;i++){
            if(i%2==1){
                if((limakAte+i)>limakLimit)
                {
                    System.out.println("Bob");
                    break;
                }
                limakAte+=i;
            }else{
                if((bobAte+i)>bobLimit)
                {
                    System.out.println("Limak");
                    break;
                }
                bobAte+=i;
            }
        }
        return;
    }
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for(int i=0;i<testCases;i++){
		    int limakLimit =sc.nextInt();
		    int bobLimit =sc.nextInt();
		    
		    findWinner(limakLimit,bobLimit);
		}

	}
}
