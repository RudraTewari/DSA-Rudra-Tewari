package Backtracking;

/*
==================== APPROACH ====================

1. Problem Understanding
------------------------
- We are given a grid where:
    - grid[r][c] > 0  -> the cell contains gold.
    - grid[r][c] == 0 -> the cell cannot be visited.
- We can start from ANY cell containing gold.
- From a cell, we can move in 4 directions:
      Up, Down, Left, Right
- We cannot visit the same cell more than once in a single path.
- We need to find the maximum amount of gold that can be collected from any valid path.

IMPORTANT OBSERVATION:
- Since we can start from any gold cell, we must try DFS from EVERY non-zero cell.
- During one DFS traversal, we must remember which cells are already part of the current path.


2. Core Intuition
-----------------
The problem is naturally a DFS + Backtracking problem.

Think of every gold cell as a possible starting point.

For a particular cell:
    1. Collect its gold.
    2. Mark it as visited.
    3. Try moving in all 4 directions.
    4. Choose the direction that gives the maximum additional gold.
    5. Restore the cell before returning so that another path/start position
       can use this cell again.

The important idea is:

    current cell's gold
          +
    best gold obtainable from any one of the 4 directions

Therefore:

    DFS(r,c) = grid[r][c] + max(
                    DFS(up),
                    DFS(left),
                    DFS(down),
                    DFS(right)
                )

The reason we take MAX instead of adding all four directions is that a path
can only continue in ONE direction at a time. We are finding the best SINGLE
path, not the total gold obtainable from all branches.


3. Algorithm / Step-by-Step Approach
------------------------------------
Step 1:
    Store the number of rows and columns in global variables m and n.

Step 2:
    Iterate through every cell of the grid.

Step 3:
    If the current cell contains gold, start DFS from that cell:

        maxGold = Math.max(maxGold, DFS(grid, i, j));

    This is necessary because the optimal path can start anywhere.

Step 4:
    Inside DFS, first check whether the current cell is invalid:

        if(r<0 || c<0 || r>=m || c>=n || grid[r][c]==0)
            return 0;

    A cell is invalid if:
        - row is outside the grid
        - column is outside the grid
        - cell contains 0
        - cell was already visited

Step 5:
    Save the current cell's gold before modifying it:

        int originalGoldValue = grid[r][c];

Step 6:
    Mark the current cell as visited:

        grid[r][c] = 0;

    We use the grid itself as the visited array.

Step 7:
    Explore all four directions:

        int upGoldValue = DFS(grid, r-1, c);
        int leftGoldValue = DFS(grid, r, c-1);
        int downGoldValue = DFS(grid, r+1, c);
        int rightGoldValue = DFS(grid, r, c+1);

Step 8:
    Find the best continuation:

        max = Math.max(
                Math.max(upGoldValue, downGoldValue),
                Math.max(leftGoldValue, rightGoldValue)
              );

Step 9:
    Restore the current cell:

        grid[r][c] = originalGoldValue;

    This is the BACKTRACKING step.

Step 10:
    Return the gold collected from the current cell plus the best
    continuation:

        return originalGoldValue + max;

Step 11:
    getMaximumGold() keeps the maximum DFS result obtained from every
    possible starting cell.


4. Recursion / Backtracking Explanation
---------------------------------------

WHAT DOES DFS(r,c) REPRESENT?

    DFS(grid, r, c)

means:

    "Starting from cell (r,c), what is the maximum amount of gold
     I can collect from this path?"

The return value is NOT the global maximum answer.

It is only the best gold obtainable from the CURRENT recursive path
starting at (r,c).

For example:

    DFS(r,c) = 10

means:

    Starting from (r,c), the best possible path can collect 10 gold.

It does NOT mean that 10 is the answer for the entire grid.


PARAMETERS:
    grid -> current grid state
    r    -> current row
    c    -> current column

BASE CASE:
    if(r<0 || c<0 || r>=m || c>=n || grid[r][c]==0)
        return 0;

This means:
    "There is no valid path from here, so this branch contributes
     0 additional gold."

RECURSIVE CALLS:

    DFS(grid, r-1, c)   -> best gold from UP
    DFS(grid, r, c-1)   -> best gold from LEFT
    DFS(grid, r+1, c)   -> best gold from DOWN
    DFS(grid, r, c+1)   -> best gold from RIGHT

Then:

    max = maximum of the four results

Finally:

    return originalGoldValue + max;

So the recursive function combines:

    CURRENT CELL
          +
    BEST VALID NEXT MOVE


BACKTRACKING:
Before recursion:

    int originalGoldValue = grid[r][c];
    grid[r][c] = 0;

After recursion:

    grid[r][c] = originalGoldValue;

This follows the classic:

    CHOOSE
      ↓
    EXPLORE
      ↓
    UNDO

pattern.

CHOOSE:
    Mark the current cell as visited.

EXPLORE:
    Recursively explore all 4 directions.

UNDO:
    Restore the original gold value.


WHY IS RESTORING IMPORTANT?

Suppose one DFS path visits:

    A -> B -> C

While exploring this path, A, B and C are temporarily marked as 0.

After DFS finishes, those cells must become usable again.

Otherwise, another starting cell or another path could incorrectly think
those cells were permanently visited.

Therefore:

    Mark visited -> Explore -> Restore

is essential.

6. Important Code Snippets
--------------------------

BASE CASE:

    if(r<0 || c<0 || r>=m || c>=n || grid[r][c]==0){
        return 0;
    }

Explanation:
- Outside the grid -> invalid
- grid[r][c] == 0 -> cannot collect gold
- grid[r][c] == 0 also represents a cell already visited in the
  current DFS path

Returning 0 means this direction provides no additional gold.


MARKING THE CELL AS VISITED:

    int originalGoldValue = grid[r][c];
    grid[r][c] = 0;

Explanation:
- Save the original value.
- Temporarily replace it with 0.
- This prevents recursive calls from coming back to this cell.

This avoids the need for a separate boolean[][] visited array.


EXPLORE ALL FOUR DIRECTIONS:

    int upGoldValue = DFS(grid, r-1, c);
    int leftGoldValue = DFS(grid, r, c-1);
    int downGoldValue = DFS(grid, r+1, c);
    int rightGoldValue = DFS(grid, r, c+1);

Explanation:
From the current cell, every possible next move must be considered.

We don't know beforehand which direction gives the maximum gold,
so all four directions are explored.


TAKE THE BEST DIRECTION:

    max = Math.max(
            Math.max(upGoldValue, downGoldValue),
            Math.max(leftGoldValue, rightGoldValue)
        );

Explanation:
Only ONE direction can be chosen as the next step of the path.

We therefore take the maximum among the four recursive results.


BACKTRACK:

    grid[r][c] = originalGoldValue;

Explanation:
The current DFS is finished using this cell.

Restore it so that:
    - another branch can use it
    - another starting position can use it

This is the "UNDO" part of backtracking.


RETURN VALUE:

    return originalGoldValue + max;

Explanation:
The current path collects:

    current cell's gold
            +
    maximum gold from the best next direction

This is the most important meaning of DFS.


OUTER LOOP:

    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            if(grid[i][j] != 0){
                maxGold = Math.max(maxGold, DFS(grid,i,j));
            }
        }
    }

Explanation:
Every non-zero cell is considered as a possible starting point.

The best result among all starting points becomes the final answer.


7. Most Important Tricks
------------------------

TRICK 1: Use the grid itself as the visited array.

Instead of:

    boolean[][] visited;

we temporarily do:

    grid[r][c] = 0;

This works because:
    - 0 already represents an unusable cell.
    - A visited gold cell can therefore temporarily behave like a 0 cell.

Remember:

    Save -> Mark -> Explore -> Restore


TRICK 2: DFS returns the BEST continuation, not the global answer.

This distinction is extremely important.

    DFS(...)
        -> best answer starting FROM THIS CELL

    maxGold
        -> best answer found FROM THE ENTIRE GRID


TRICK 3: Take MAXIMUM of the four directions.

DO NOT do:

    up + left + down + right

That would combine multiple branches into one path, which is invalid.

Correct:

    current gold + MAX(one of the four directions)


TRICK 4: Backtracking is mandatory.

Wrong:

    grid[r][c] = 0;
    explore();
    return ...;

Correct:

    int original = grid[r][c];
    grid[r][c] = 0;

    explore();

    grid[r][c] = original;


TRICK 5: The original value must be stored BEFORE modifying the grid.

Correct:

    int originalGoldValue = grid[r][c];
    grid[r][c] = 0;

If you overwrite first, you lose the value needed for restoration.


8. Dry Run
----------

Consider this small grid:

    [1, 2]
    [3, 4]

Start DFS from (0,0).

Current cell:
    grid[0][0] = 1

Save:

    originalGoldValue = 1

Mark visited:

    grid[0][0] = 0

Now explore:

    UP    -> invalid -> 0
    LEFT  -> invalid -> 0
    DOWN  -> DFS(1,0)
    RIGHT -> DFS(0,1)


Suppose DFS goes:

    (0,0) -> (1,0) -> (1,1) -> (0,1)

The collected gold is:

    1 + 3 + 4 + 2 = 10

At each cell, the recursive function returns the best continuation.

For example:

    DFS(1,1)
        = 4 + max(valid directions)

If the best continuation is 2:

    DFS(1,1) = 4 + 2
             = 6


Then:

    DFS(1,0) = 3 + 6
             = 9

Finally:

    DFS(0,0) = 1 + 9
             = 10

After DFS finishes, every temporarily modified cell is restored.

The outer loop then tries other possible starting cells as well and
updates maxGold with the best result.


IMPORTANT RECURSION FLOW:

    DFS(0,0)
        |
        +-- DFS(1,0)
        |      |
        |      +-- DFS(1,1)
        |             |
        |             +-- DFS(0,1)
        |
        +-- DFS(0,1)

Each DFS call returns the BEST possible continuation from that cell.


9. Common Mistakes
------------------

MISTAKE 1: Not marking the cell as visited.

WRONG:

    int original = grid[r][c];

    int up = DFS(grid,r-1,c);
    ...

Without marking the current cell, recursion can go:

    A -> B -> A -> B -> A -> ...

This can result in infinite recursion / stack overflow.

CORRECT:

    int original = grid[r][c];
    grid[r][c] = 0;


MISTAKE 2: Forgetting to restore the cell.

WRONG:

    grid[r][c] = 0;
    ...
    return original + max;

The cell remains permanently blocked after that DFS.

CORRECT:

    grid[r][c] = 0;
    ...
    grid[r][c] = original;


MISTAKE 3: Adding all four directions.

WRONG:

    return originalGoldValue
           + upGoldValue
           + leftGoldValue
           + downGoldValue
           + rightGoldValue;

This represents collecting gold from multiple branches simultaneously.

But the problem asks for one path.

CORRECT:

    return originalGoldValue + max;


MISTAKE 4: Treating DFS return value as the global answer.

DFS returns:

    Best gold starting from THIS cell.

maxGold stores:

    Best gold found from ANY starting cell.

These are different concepts.


MISTAKE 5: Updating the answer only once.

WRONG:

    DFS(grid,0,0);

The optimal path may start from another cell.

CORRECT:

    for every non-zero cell:
        DFS(grid,i,j);

    maxGold = maximum of all DFS results;


MISTAKE 6: Using a global variable for the current path sum.

A global variable for the current path can easily cause state-management
problems during recursion.

This implementation avoids that by making the recursive return value
represent the best result from the current cell:

    return originalGoldValue + max;

This makes the recursion cleaner.


10. Complexity
--------------

Let K be the number of non-zero cells.

In the worst case, DFS may explore many possible paths because each cell
can lead to multiple choices.

TIME COMPLEXITY:

    O(K * 4^K)

This is a common upper-bound way to describe the brute-force DFS/backtracking
search, since from each cell we may branch into up to 4 directions.

In practice, the visited marking prevents immediately going back to the
previous cell, so the actual branching is smaller than 4 at most levels.

SPACE COMPLEXITY:

    O(K)

because of the recursion stack.

At most K cells can belong to one recursive path.

No separate visited[][] array is used, because the grid itself stores
the visited state.

==================== ONE-LINE MEMORY TRICK ====================

    "Pick a cell -> Mark visited -> Try 4 directions -> Take MAX -> Restore"

*/


class Solution{
    static int maxGold;
    static int m,n;

    public static int DFS(int[][] grid, int r, int c){
        if(r<0||c<0||r>=m||c>=n||grid[r][c]==0){
            return 0;
        }

        int originalGoldValue = grid[r][c];
        grid[r][c]=0;
        int max = 0;

        int upGoldValue = DFS(grid, r-1, c);  // Up Direction
        int leftGoldValue = DFS(grid, r, c-1);  // Left Direction
        int downGoldValue = DFS(grid, r+1, c);  // Down Direction
        int rightGoldValue = DFS(grid, r, c+1);  // Right Direction

        max = Math.max(Math.max(upGoldValue,downGoldValue),Math.max(leftGoldValue,rightGoldValue));

        // Backtrack
        grid[r][c]=originalGoldValue;

        return originalGoldValue + max;
    }
    public int getMaximumGold(int[][] grid){
        m=grid.length;
        n= grid[0].length;

        maxGold=0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] != 0){
                    maxGold = Math.max(maxGold, DFS(grid,i,j));
                }
            }
        }
        return maxGold;
    }
}
public class MaxGoldPath {
    public static void main(String[] args) {
        int[][] grid ={
            {0,6,0},
            {5,8,7},
            {0,9,0}
        };

        Solution obj = new Solution();
        int ans = obj.getMaximumGold(grid);
        System.out.println(ans);
    }
}
