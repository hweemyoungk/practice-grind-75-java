package MinimumKnightMoves_1197;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.


Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.



Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]


Constraints:

-300 <= x, y <= 300
0 <= |x| + |y| <= 300

[5/27/2024]Time up
5%/44%
81%/83% (Optimized, but suboptimal)
O(max(x,y)^2)/O(max(x,y)^2)
?/? (Editorial)
*/
class Solution {
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) return 0;
        x = Math.abs(x);
        y = Math.abs(y);
        int nStep = 0;
        int[][] directions = new int[][]{{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        List<int[]> prevs = new LinkedList<>() {{
            add(new int[]{0, 0});
        }};
        List<int[]> currs = new LinkedList<>();
        int max = Math.max(x, y);
        boolean[][] visited = new boolean[max + 3][max + 3];
        while (true) {
            nStep++;
            for (int[] prev : prevs) {
                for (int[] direction : directions) {
                    int xCurr = Math.abs(prev[0] + direction[0]);
                    int yCurr = Math.abs(prev[1] + direction[1]);
                    if (max + 3 <= xCurr || max + 3 <= yCurr) continue;
                    if (xCurr == x && yCurr == y) {
                        return nStep;
                    }
                    if (!visited[xCurr][yCurr]) {
                        visited[xCurr][yCurr] = true;
                        currs.add(new int[]{xCurr, yCurr});
                    }
                }
            }
            prevs.clear();
            prevs.addAll(currs);
            currs.clear();
        }
    }
}
