public class DiceFace {
    public static void findCombinations(String p, int target,int faces){
        if(target==0){
            System.out.println(p);
            return;
        }

        for (int i = 1; i <= faces && i<=target; i++) {
            findCombinations(p+i, target-i, faces);
        }
    }
    public static void main(String[] args) {
        findCombinations("", 4, 6);
    }
}
