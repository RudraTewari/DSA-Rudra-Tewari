public class CountStepstoZero{
    public static int countSteps(int n,int count){
        if(n==0){
            return count;
        }
        if((n&1)==0){
            n=n/2;            
        }else{
            n=n-1;
        }
        count++;
        return countSteps(n,count);
    }
    public static void main(String[] args){
        int n=14;
        int ans = countSteps(n,0);
        System.out.println("Answer : "+ ans);
    }
}