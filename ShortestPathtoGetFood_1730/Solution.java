package ShortestPathtoGetFood_1730;

import java.util.LinkedList;
import java.util.Queue;

/*You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.



Example 1:


Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
Output: 3
Explanation: It takes 3 steps to reach the food.
Example 2:


Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
Output: -1
Explanation: It is not possible to reach the food.
Example 3:


Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
Output: 6
Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[row][col] is '*', 'X', 'O', or '#'.
The grid contains exactly one '*'.

[5/6/2024]27m out of 30m
36%/94%
O(m*n)/O(m+n)
*/
class Solution {
    static class Point {
        int x;
        int y;
        int nStep;

        public Point(int x, int y, int nStep) {
            this.x = x;
            this.y = y;
            this.nStep = nStep;
        }
    }

    public int getFood(char[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        // Replace grid
        // boolean[][] visited = new boolean[m][n];
        // BFS
        Queue<Point> queue = new LinkedList<>();
        queue.add(getLocation(grid));
        final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            // point coordinates are always valid
            Point curr = queue.remove();
            char val = grid[curr.x][curr.y];
            // Obstacle (marked while waiting in the queue)
            if (val == 'X') continue;
            // Food
            if (val == '#') return curr.nStep;
            // Start or free: next
            for (int[] direction : directions) {
                int xNext = curr.x + direction[0];
                int yNext = curr.y + direction[1];
                if (!isValid(xNext, yNext, m, n)) continue;
                if (grid[xNext][yNext] == 'X') continue;
                // Valid and not visited
                queue.add(new Point(xNext, yNext, curr.nStep + 1));
            }
            // Mark visited
            grid[curr.x][curr.y] = 'X';
        }
        return -1;
    }

    private boolean isValid(int x, int y, int m, int n) {
        return !(x < 0 || x > m - 1 || y < 0 || y > n - 1);
    }

    private Point getLocation(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '*') {
                    return new Point(i, j, 0);
                }
            }
        }
        return null;
    }

}
