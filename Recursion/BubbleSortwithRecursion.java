class BubbleSortwithRecursion{
    public static void bubbleSort(int[] arr, int r,int c){
        if(r==0){
            return;
        }
        if(c<r){
            if(arr[c]<arr[c+1]){
                //swap
                int temp=arr[c];
                arr[c]=arr[c+1];
                arr[c+1]=temp;
            }
            bubbleSort(arr,r,c+1);
        }else{
            bubbleSort(arr,r-1,0);
        }
    }
    public static void main(String[] args){
        int[] arr = {8,9,3,1};
        int r=arr.length-1;
        int c=0;
        bubbleSort(arr,r,c);
        for(int num : arr){
            System.out.print(num + " ");
        }
        System.out.println();
    }
}