package Backtracking;
public class CountingPaths{
    public static int findPaths(int[][] mat,int row,int col,int targetRow,int targetCol){
        if(row == targetRow-1){
            return 1;
        }
        if(col==targetCol-1){
            return 1;
        }
        int downCount=findPaths(mat,row+1,col,targetRow,targetCol);
        int rightCount=findPaths(mat,row,col+1,targetRow,targetCol);
        return rightCount+downCount;
    }
    public static void main(String[] args){
        int[][] mat = {{1,2,3},{2,3,4},{3,4,5}};
        int targetRow=3;
        int targetCol=3;
        int pathCount = findPaths(mat,0,0,targetRow,targetCol);
        System.out.println("Answer : "+pathCount);
    }
}