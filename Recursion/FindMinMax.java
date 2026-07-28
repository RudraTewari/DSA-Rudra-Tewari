public class FindMinMax{
    public static int[] findMinMax(int[] arr,int index){
        if(index==0){
            int[] base = {arr[0],arr[1]};
            return base;
        }
        // Recursive Call
        int[] res = findMinMax(arr,index-1);

        //  Min Element Finding & Updating
        if(arr[index] < res[0]) 
            res[0]=arr[index];

        // Max Element Finding & Updating
        if(arr[index] > res[1]) 
            res[1]=arr[index];

        return res;
    } 

    public static void main(String[] args){
        int[] arr ={1, 4, 3, -5, -4, 8, 6};

        int[] ans = findMinMax(arr,arr.length-1);
        System.out.println("Min : "+ ans[0] + " "+"Max : "+ ans[1]);
    }
}