package SpiralMatrix_54;

import java.util.LinkedList;
import java.util.List;

/*Given an m x n matrix, return all elements of the matrix in spiral order.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:


Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100

[4/11/2024]16m out of 25m
100%/54%
O(m*n)/O(1 w/o ans)
*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new LinkedList<>();
        boolean horizontal = true;
        boolean increasing = true;
        int left = 0;
        int right = matrix[0].length - 1;
        int lower = 0;
        int upper = matrix.length - 1;
        int[] coord = new int[]{0, 0};
        while (left <= right && lower <= upper) {
            if (horizontal) {
                int[] row = matrix[coord[0]];
                if (increasing) {
                    for (int i = left; i <= right; i++) {
                        ans.add(row[i]);
                    }
                    lower++;
                    coord[0] = lower;
                    coord[1] = right;
                } else {
                    // Decreasing
                    for (int i = right; i >= left; i--) {
                        ans.add(row[i]);
                    }
                    upper--;
                    coord[0] = upper;
                    coord[1] = left;
                }
                horizontal = false;
            } else {
                // Vertical
                if (increasing) {
                    for (int i = lower; i <= upper; i++) {
                        ans.add(matrix[i][right]);
                    }
                    right--;
                    coord[0] = upper;
                    coord[1] = right;
                    increasing = false;
                } else {
                    for (int i = upper; i >= lower; i--) {
                        ans.add(matrix[i][left]);
                    }
                    left++;
                    coord[0] = lower;
                    coord[1] = left;
                    increasing = true;
                }
                horizontal = true;
            }
        }
        return ans;
    }
}
