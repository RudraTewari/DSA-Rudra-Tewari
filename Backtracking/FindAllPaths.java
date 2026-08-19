package Backtracking;

public class FindAllPaths {
    public static void findAllPaths(int[][] maze,boolean[][] visited, int r, int c, StringBuilder str){
        if(r==maze.length-1 && c==maze[0].length-1){
            System.out.println(str);
            return;
        }
        if(visited[r][c]==false){
            return;
        }
        visited[r][c] = false;
        //For going Down
        if(r<maze.length-1){
            str.append("D");
            findAllPaths(maze, visited, r+1, c, str);
            str.deleteCharAt(str.length()-1);            
        }

        //For going Right
        if(c<maze[0].length-1){
            str.append("R");
            findAllPaths(maze, visited, r, c+1, str);
            str.deleteCharAt(str.length()-1);
        }
        
        //For going Up
        if(r>0){
            str.append("U");
            findAllPaths(maze, visited, r-1, c, str);
            str.deleteCharAt(str.length()-1);
        }
        
        //For going Left
        if(c>0){
            str.append("L");
            findAllPaths(maze, visited, r, c-1, str);
            str.deleteCharAt(str.length()-1);
        }

        //Bactracking
        visited[r][c]=true;
    }
    public static void main(String[] args){
        int[][] mat = {{1,2,3},{2,3,4},{3,4,5}};
        boolean[][] visited = {
            {true, true,true},
            {true,true,true},
            {true,true,true}
        };
        StringBuilder str = new StringBuilder("");
        findAllPaths(mat,visited,0,0,str);
    }
}
