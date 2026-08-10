public class FindkthChinStringGameII {
    public static char kthCharacter(long k,int[] operations){
        if(k==1){
            return 'a';
        }
        long len=1;
        int operationType=-1;

        for(int i=0;i<operations.length;i++){
            len*=2;
            if(len>=k){
                operationType=operations[i];
                break;
            }
        }
        long newk = (long) k-len/2;
        char ch = kthCharacter(newk, operations);
        if(operationType==0){
            return ch;
        }else{
            return (ch=='z') ? 'a' : (char)(ch+1);
        }
    }
    public static void main(String[] args) {
        long k = 7;
        int[] operations={0,1,0,1};

        System.out.println(kthCharacter(k,operations));
    }
}
