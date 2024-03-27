package FloodFill_733;

import java.util.LinkedList;
import java.util.Queue;

/**
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 * <p>
 * You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 * <p>
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
 * <p>
 * Return the modified image after performing the flood fill.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 * Example 2:
 * <p>
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],[0,0,0]]
 * Explanation: The starting pixel is already colored 0, so no changes are made to the image.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], color < 2^16
 * 0 <= sr < m
 * 0 <= sc < n
 */
/*
* [3/26/2024]17m out of 20m
* 40%/93%
* O(m*n),O(m*n)*/
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) return image;
        // BFS
        int m = image.length;
        int n = image[0].length;
        boolean[][] visited = new boolean[n][m];
        int srcColor = image[sr][sc];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            image[curr[0]][curr[1]] = color;
            visited[curr[0]][curr[1]] = true;

            if (0 < curr[0]
                    && image[curr[0] - 1][curr[1]] == srcColor
                    && !visited[curr[0] - 1][curr[1]])
                queue.add(new int[]{curr[0] - 1, curr[1]});
            if (curr[0] < m - 1
                    && image[curr[0] + 1][curr[1]] == srcColor
                    && !visited[curr[0] + 1][curr[1]])
                queue.add(new int[]{curr[0] + 1, curr[1]});
            if (0 < curr[1]
                    && image[curr[0]][curr[1] - 1] == srcColor
                    && !visited[curr[0]][curr[1] - 1])
                queue.add(new int[]{curr[0], curr[1] - 1});
            if (curr[1] < n - 1
                    && image[curr[0]][curr[1] + 1] == srcColor
                    && !visited[curr[0]][curr[1] + 1])
                queue.add(new int[]{curr[0], curr[1] + 1});
        }
        return image;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().floodFill(new int[][]{new int[]{0, 0, 0}, new int[]{0, 0, 0}}, 1, 0, 2));
    }

}