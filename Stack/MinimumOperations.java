
class MinimumOperations{
    public int minNumberOperations(int[] target){
        int minOperations=target[0];
        for(int i=1;i<target.length-1;i++)
        {
            if (target[i] > target[i - 1]) {
                minOperations += target[i] - target[i-1];
            }
            
        }
        return minOperations;
    }
    public static void main(String[] args)
    {
        int[] target = {3,1,1,2};
        MinimumOperations obj = new MinimumOperations();
        int ans = obj.minNumberOperations(target);
        System.out.println(ans);
    }
}