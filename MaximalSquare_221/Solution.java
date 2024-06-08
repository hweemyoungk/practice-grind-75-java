package MaximalSquare_221;

/*Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.



Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
Example 2:


Input: matrix = [["0","1"],["1","0"]]
Output: 1
Example 3:

Input: matrix = [["0"]]
Output: 0


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.

[6/8/2024]11m out of 30m
25%/83%
O(m*n)/O(m*n)
*/
class Solution {
    private Integer[][] values;
    private int m;
    private int n;

    // DFS
    public int maximalSquare(char[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        values = new Integer[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Integer.max(ans, dfs(i, j, matrix));
            }
        }
        return ans * ans;
    }

    private int dfs(int i, int j, char[][] matrix) {
        if (m == i || n == j) {
            // Out of range
            return 0;
        }
        if (values[i][j] != null) {
            // Already visited
            return values[i][j];
        }
        if (matrix[i][j] == '0') {
            values[i][j] = 0;
            return 0;
        }
        // matrix[i][j] == '1'
        values[i][j] = 1 + Integer.min(dfs(i + 1, j, matrix), Integer.min(dfs(i + 1, j + 1, matrix), dfs(i, j + 1, matrix)));
        return values[i][j];
    }
}
