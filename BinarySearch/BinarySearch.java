import java.util.Arrays;

class BinarySearch{
    public int binarySearch(int[] arr,int target)
    {
        Arrays.sort(arr);
        int lb=0,ub=arr.length-1;
        while(lb <= ub)
        {
            int mid = lb+((ub-lb)/2);
            if(arr[mid] == target)
            {
                return mid+1;
            }
            else if(target > arr[mid])
            {
                lb=mid+1;
            }
            else
                ub=mid+1;
        }
        return -1;
    }
    public static void main(String[] args)
    {
        int[] arr = {6,4,5,8,10,9,11};
        int target = 11;
        BinarySearch obj = new BinarySearch();
        int ans = obj.binarySearch(arr,target);
        if(ans == -1)
            System.out.println("Target not found");
        else
            System.out.println("Target Found at "+ans);
    }
}