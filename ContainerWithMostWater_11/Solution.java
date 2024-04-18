package ContainerWithMostWater_11;

import java.util.*;

/*11. Container With Most Water
Solved
Medium
Topics
Companies
Hint
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.



Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:

Input: height = [1,1]
Output: 1


Constraints:

n == height.length
2 <= n <= 10^5
0 <= height[i] <= 10^4

[4/17/2024]Time up
11%/87%
O(n~n^2)
*/
class Solution {
    public int maxArea(int[] height) {
        int dist = 0;
        Map<Integer, List<Integer>> heightIdxMap = new TreeMap<>((a, b) -> b - a);
        for (int i = 0; i < height.length; i++) {
            List<Integer> indices = heightIdxMap.get(height[i]);
            if (indices == null) {
                List<Integer> list = new LinkedList<>();
                list.add(i);
                heightIdxMap.put(height[i], list);
                continue;
            }
            indices.add(i);
        }
        int ans = 0;
        List<Map.Entry<Integer, List<Integer>>> entries = new ArrayList<>(heightIdxMap.entrySet());
        for (int i = 0; i < entries.size(); i++) {
            Map.Entry<Integer, List<Integer>> currHeightIndices = entries.get(i);
            Integer currHeight = currHeightIndices.getKey();
            List<Integer> currIndices = currHeightIndices.getValue();
            for (var currIndex : currIndices) {
                for (int j = 0; j < currIndex - dist; j++) {
                    if (height[j] < currHeight) continue;
                    int w = currIndex - j;
                    if (ans < w * currHeight) {
                        dist = w;
                        ans = w * currHeight;
                    }
                }
                for (int j = height.length - 1; j > currIndex + dist; j--) {
                    if (height[j] < currHeight) continue;
                    int w = j - currIndex;
                    if (ans < w * currHeight) {
                        dist = w;
                        ans = w * currHeight;
                    }
                }
            }
        }
        return ans;
    }
}
