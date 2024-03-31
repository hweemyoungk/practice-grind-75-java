package ZeroOneMatrix_542;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two adjacent cells is 1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 * Example 2:
 * <p>
 * <p>
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * mat[i][j] is either 0 or 1.
 * There is at least one 0 in mat.
 */
/*
* [3/31/2024]15m out of 30m
* 13%/5% (...)
* O(mn)/O(mn)
* */
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        // BFS
        int m = mat.length;
        int n = mat[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] ans = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    visited[i][j] = true;
                    ans[i][j] = 0;
                    if (0 < i) queue.add(new int[]{i - 1, j});
                    if (i < m - 1) queue.add(new int[]{i + 1, j});
                    if (0 < j) queue.add(new int[]{i, j - 1});
                    if (j < n - 1) queue.add(new int[]{i, j + 1});
                }
            }
        }
        int level = 1;
        while (!queue.isEmpty()) {
            Queue<int[]> nextQueue = new LinkedList<>();
            for (var coord : queue) {
                if (visited[coord[0]][coord[1]]) continue;
                visited[coord[0]][coord[1]] = true;
                ans[coord[0]][coord[1]] = level;
                if (0 < coord[0] && !visited[coord[0] - 1][coord[1]]) nextQueue.add(new int[]{coord[0] - 1, coord[1]});
                if (coord[0] < m - 1 && !visited[coord[0] + 1][coord[1]])
                    nextQueue.add(new int[]{coord[0] + 1, coord[1]});
                if (0 < coord[1] && !visited[coord[0]][coord[1] - 1]) nextQueue.add(new int[]{coord[0], coord[1] - 1});
                if (coord[1] < n - 1 && !visited[coord[0]][coord[1] + 1])
                    nextQueue.add(new int[]{coord[0], coord[1] + 1});
            }
            queue = nextQueue;
            level+=1;
        }
        return ans;
    }
}
