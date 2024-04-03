package NumberofIslands_200;


import java.util.LinkedList;
import java.util.Queue;

/*Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.

[4/3/2024]12m out of 25m
6%/93%
O(m*n + 4*#lands = m*n)/O(m*n) (BFS)
*/
class Solution {
    public int numIslands(char[][] grid) {
        // DP(memoization) + BFS
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;
                if (grid[i][j] == '0') {
                    visited[i][j] = true;
                    continue;
                }
                // (i,j) is new land
                ans++;
                // Propagate
                queue.add(new int[]{i, j});
                while (!queue.isEmpty()) {
                    int[] curr = queue.remove();
                    if (visited[curr[0]][curr[1]]) continue;
                    visited[curr[0]][curr[1]] = true;
                    if (grid[curr[0]][curr[1]] == '0') continue;
                    // curr is propagated new land
                    if (0 < curr[0] && !visited[curr[0] - 1][curr[1]]) queue.add(new int[]{curr[0] - 1, curr[1]});
                    if (curr[0] < m - 1 && !visited[curr[0] + 1][curr[1]]) queue.add(new int[]{curr[0] + 1, curr[1]});
                    if (0 < curr[1] && !visited[curr[0]][curr[1] - 1]) queue.add(new int[]{curr[0], curr[1] - 1});
                    if (curr[1] < n - 1 && !visited[curr[0]][curr[1] + 1]) queue.add(new int[]{curr[0], curr[1] + 1});
                }
            }
        }
        return ans;
    }
}
