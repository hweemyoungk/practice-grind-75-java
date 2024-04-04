package RottingOranges_994;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.



Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.

[4/4/2024]21m out of 30m
72%/48%
O(m*n)/O(m*n)
*/
class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int orangesRotting(int[][] grid) {
        // BFS
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        // Scan seeds
        Set<int[]> coords = new HashSet<>();
        int nFresh = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                if (grid[i][j] == 1) {
                    nFresh++;
                    continue;
                }
                addAdjacents(i, j, coords);
            }
        }
        int t = 0;
        while (!coords.isEmpty()) {
            t++;
            Set<int[]> nextCoords = new HashSet<>();
            for (var coord : coords) {
                if (grid[coord[0]][coord[1]] == 1) {
                    nFresh--;
                    grid[coord[0]][coord[1]] = 2;
                }
            }
            for (var coord : coords) {
                addAdjacents(coord[0], coord[1], nextCoords);
            }
            coords = nextCoords;
        }

        return nFresh == 0 ? t : -1;
    }

    private void addAdjacents(int i, int j, Set<int[]> coords) {
        if (0 < i && grid[i - 1][j] == 1) coords.add(new int[]{i - 1, j});
        if (i < m - 1 && grid[i + 1][j] == 1) coords.add(new int[]{i + 1, j});
        if (0 < j && grid[i][j - 1] == 1) coords.add(new int[]{i, j - 1});
        if (j < n - 1 && grid[i][j + 1] == 1) coords.add(new int[]{i, j + 1});
    }
}
