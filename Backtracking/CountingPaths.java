package Backtracking;

public class CountingPaths{
    public static int findPaths(int[][] mat,int row,int col,int targetRow,int targetCol){
        if(row == targetRow-1 && col==targetCol-1){
            return 1;
        }
        int downCount = 0;
        int rightCount = 0;

        if (row < targetRow-1) {
            downCount = findPaths(mat, row + 1, col, targetRow, targetCol);
        }

        if (col < targetCol-1) {
            rightCount = findPaths(mat, row, col + 1, targetRow, targetCol);
        }

        return downCount + rightCount;
    }
    public static int findPathsWithDiagonal(int[][] mat,int row,int col,int targetRow,int targetCol){
        if(row==targetRow-1 && col == targetCol-1)
            return 1;
        
        int downCount=0,diagonalCount=0,rightCount=0;
        if(row < targetRow-1 && col < targetCol-1)
            diagonalCount = findPathsWithDiagonal(mat,row+1,col+1,targetRow,targetCol);
        if(row <targetRow-1)
            downCount=findPathsWithDiagonal(mat,row+1,col,targetRow,targetCol);
        if(col<targetCol-1)
            rightCount=findPathsWithDiagonal(mat,row,col+1,targetRow,targetCol);

        return rightCount+downCount+diagonalCount;
    }

    public static void findPathsWithDiagonalII(int[][] mat,int row,int col,int targetRow,
                                                            int targetCol, StringBuilder ans){
        if(row==targetRow-1 && col == targetCol-1){
            System.out.println(ans);
            return;
        }
        if(row < targetRow-1 && col < targetCol-1){
            ans.append("Di");
            findPathsWithDiagonalII(mat,row+1,col+1,targetRow,targetCol,ans);
            ans.delete(ans.length()-2, ans.length());
        }

        if(row <targetRow-1){
            ans.append("D");
            findPathsWithDiagonalII(mat,row+1,col,targetRow,targetCol,ans);
            ans.deleteCharAt(ans.length()-1);
        }
        
        if(col<targetCol-1){
            ans.append("R");
            findPathsWithDiagonalII(mat,row,col+1,targetRow,targetCol,ans);
            ans.deleteCharAt(ans.length()-1);
        }
            
    }

    public static void findPathsII(int[][] mat,int row,int col,int targetRow,
                                                            int targetCol,StringBuilder path){
        if(row == targetRow-1 && col==targetCol-1){
            System.out.println(path);
            return;
        }
        
        if(row < targetRow-1){
            path.append("D");
            findPathsII(mat,row+1,col,targetRow,targetCol,path);
            path.deleteCharAt(path.length()-1);
        } 
        if (col<targetCol-1) {
            path.append("R");
            findPathsII(mat,row,col+1,targetRow,targetCol,path);
            path.deleteCharAt(path.length()-1);
        }
    }

    public static void pathRestrictions(int[][] mat,int row,int col,int targetRow,
                                        int targetCol, boolean[][] maze, StringBuilder path){
        if(row==targetRow-1 && col == targetCol-1){
            System.out.println(path);
            return;
        }
        if(!maze[row][col]){
            return;
        }

        if(row<targetRow-1){
            path.append("D");
            pathRestrictions(mat, row+1, col, targetRow, targetCol, maze, path);
            path.deleteCharAt(path.length()-1);
        }
        if(col < targetCol-1){
            path.append("R");
            pathRestrictions(mat, row, col+1, targetRow, targetCol, maze, path);
            path.deleteCharAt(path.length()-1);
        }
    }
    public static void main(String[] args){
        int[][] mat = {{1,2,3},{2,3,4},{3,4,5}};
        int targetRow=2;
        int targetCol=3;
        boolean[][] maze= {
            {true, true,true},
            {true,false,true},
            {true,true,true}
        };
        int pathCount = findPaths(mat,0,0,targetRow,targetCol);
        StringBuilder path = new StringBuilder("");
        findPathsII(mat,0,0,targetRow,targetCol,path);
        int pathCountwithDiagonal = findPathsWithDiagonal(mat, 0, 0, targetRow, targetCol);
        System.out.println("Answer : "+pathCount);
        System.out.println("Answer with Diagonal : "+pathCountwithDiagonal);
        StringBuilder pathI=new StringBuilder("");
        findPathsWithDiagonalII(mat, 0, 0, targetRow, targetCol, pathI);
        System.out.println("---------------------------------------------------");
        StringBuilder pathII=new StringBuilder("");
        pathRestrictions(mat, 0, 0, targetRow, targetCol, maze, pathII);
    }
}