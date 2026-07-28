public class LinearSearch{
    public static int linearSearch(int[] arr,int target,int index){
        if(arr[index]==target)
            return index;
        if(index == arr.length-1)
            return -1;
        return linearSearch(arr,target,index+1);
    }
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6,7};
        int target = 0;
        int ans = linearSearch(arr,target,0);
        System.out.println("Index = "+ans);
    }
}