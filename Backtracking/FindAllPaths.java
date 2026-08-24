package Backtracking;

import java.util.Arrays;
/*
    APPROACH: BACKTRACKING (PRINT ALL PATHS WITH STEP MATRIX)

    1. Base Case:
    If we reach the destination, mark it with the current step number
    and print both the path matrix and the direction string.

    if (r == maze.length - 1 && c == maze[0].length - 1) {
        path[r][c] = step;

        for (int[] arr : path) {
            System.out.println(Arrays.toString(arr));
        }

        System.out.println(p);
        return;
    }


    2. Avoid revisiting cells:
    If the current cell is already visited in the current path,
    return immediately. This prevents cycles such as D -> U or R -> L.

    if (!visited[r][c]) {
        return;
    }


    3. Choose / Mark:
    Mark the current cell as visited and store its step number
    in the path matrix before exploring further directions.

    visited[r][c] = false;
    path[r][c] = step;


    4. Explore all possible directions:
    Try moving Down, Right, Up, and Left. For every valid move,
    recursively explore the next cell and increase the step number.

    Down
    if (r < maze.length - 1) {
        AllPathsPrint(p + "D", maze, visited,
                        r + 1, c, path, step + 1);
    }

    Right
    if (c < maze[0].length - 1) {
        AllPathsPrint(p + "R", maze, visited,
                        r, c + 1, path, step + 1);
    }

    Up
    if (r > 0) {
        AllPathsPrint(p + "U", maze, visited,
                        r - 1, c, path, step + 1);
    }

    Left
    if (c > 0) {
        AllPathsPrint(p + "L", maze, visited,
                        r, c - 1, path, step + 1);
    }


    5. Backtrack / Undo:
    After exploring all directions, undo the changes made to the
    current cell so it can be used again for another possible path.

    visited[r][c] = true;
    path[r][c] = 0;


    BACKTRACKING PATTERN:
    Mark the cell -> Explore all choices -> Undo the changes

    visited[r][c] = false;
    path[r][c] = step;

    Recursive calls for D, R, U and L

    visited[r][c] = true;
    path[r][c] = 0;

    NOTE:
    The direction string is passed as p + "D", p + "R", etc.
    Since String is immutable, a new String is created for each
    recursive call, so no manual removal of characters is required.
*/
public class FindAllPaths {
    public static void AllPathsPrint(String p, int[][] maze,boolean[][] visited, int r, int c, int[][] path, int step){
        if(r==maze.length-1 && c==maze[0].length-1){
            path[r][c]=step;
            for(int[] arr: path){
                System.out.println(Arrays.toString(arr));
            }
            System.out.println();
            System.out.println(p);
            System.out.printf("---------------\n");
            return;
        }
        if(visited[r][c]==false){
            return;
        }
        visited[r][c] = false;
        path[r][c]=step;
        //For going Down
        if(r<maze.length-1){
            AllPathsPrint(p+"D", maze, visited, r+1, c, path, step+1);        
        }

        //For going Right
        if(c<maze[0].length-1){    
            AllPathsPrint(p+"R", maze, visited, r, c+1, path, step+1);
        }
        
        //For going Up
        if(r>0){
            AllPathsPrint(p+"U", maze, visited, r-1, c, path, step+1);
        }
        
        //For going Left
        if(c>0){
            AllPathsPrint(p+"L", maze, visited, r, c-1, path, step+1);
        }

        //Bactracking
        visited[r][c]=true;
        path[r][c] = 0;
    }

    public static void main(String[] args){
        int[][] mat = {{1,2,3},{2,3,4},{3,4,5}};
        boolean[][] visited = {
            {true, true,true},
            {true,true,true},
            {true,true,true}
        };
        int[][] path ={
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        int step=1;
        AllPathsPrint("", mat, visited,0,0, path, step);
    }
}

// public static void findAllPaths(int[][] maze,boolean[][] visited, int r, int c, StringBuilder str){
    //     if(r==maze.length-1 && c==maze[0].length-1){
    //         System.out.println(str);
    //         return;
    //     }
    //     if(visited[r][c]==false){
    //         return;
    //     }
    //     visited[r][c] = false;
    //     For going Down
    //     if(r<maze.length-1){
    //         str.append("D");
    //         findAllPaths(maze, visited, r+1, c, str);
    //         str.deleteCharAt(str.length()-1);  //Backtrack         
    //     }

    //     For going Right
    //     if(c<maze[0].length-1){
    //         str.append("R");
    //         findAllPaths(maze, visited, r, c+1, str);
    //         str.deleteCharAt(str.length()-1);
    //     }
        
    //     For going Up
    //     if(r>0){
    //         str.append("U");
    //         findAllPaths(maze, visited, r-1, c, str);
    //         str.deleteCharAt(str.length()-1);
    //     }
        
    //     For going Left
    //     if(c>0){
    //         str.append("L");
    //         findAllPaths(maze, visited, r, c-1, str);
    //         str.deleteCharAt(str.length()-1);
    //     }

    //     Bactracking
    //     visited[r][c]=true;
    // }

