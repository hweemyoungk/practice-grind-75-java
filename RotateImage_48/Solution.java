package RotateImage_48;

/*You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]


Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000

[6/8/2024]23m out of 25m
100%/80%
O(n^2)/O(1)
*/
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            // Swap top and right
            for (int j = 0; j < n - 1 - 2 * i; j++) {
                swap(matrix, i, i + j, i + j, n - 1 - i);
            }
            // Swap left and top
            for (int j = 0; j < n - 1 - 2 * i; j++) {
                swap(matrix, n - 1 - i - j, i, i, i + j);
            }
            // Swap bottom and left
            for (int j = 0; j < n - 1 - 2 * i; j++) {
                swap(matrix, n - 1 - i, n - 1 - i - j, n - 1 - i - j, i);
            }
        }
    }

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int memory = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = memory;
    }
}
