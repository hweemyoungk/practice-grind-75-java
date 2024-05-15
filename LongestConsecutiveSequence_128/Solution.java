package LongestConsecutiveSequence_128;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.



Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9


Constraints:

0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9

[5/14/2024]24m out of 30m
39%/55%
O(n)/O(n)
*/
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        int ans = 1;
        Map<Integer, int[]> leftExclusives = new HashMap<>();
        Map<Integer, int[]> rightExclusives = new HashMap<>();
        Set<Integer> seen = new HashSet<>();
        for (int n : nums) {
            if (seen.contains(n)) continue;
            seen.add(n);
            int[] fromLeft = leftExclusives.get(n);
            int[] fromRight = rightExclusives.get(n);
            if (fromRight != null && fromLeft != null) {
                // Merge
                leftExclusives.remove(n);
                rightExclusives.remove(n);
                int[] newBound = new int[]{fromRight[0], fromLeft[1]};
                leftExclusives.put(newBound[0], newBound);
                rightExclusives.put(newBound[1], newBound);
                ans = Integer.max(ans, newBound[1] - newBound[0] - 1);
                continue;
            }
            if (fromRight != null) {
                rightExclusives.remove(n);
                fromRight[1] = n + 1;
                rightExclusives.put(n + 1, fromRight);
                ans = Integer.max(ans, fromRight[1] - fromRight[0] - 1);
                continue;
            }
            if (fromLeft != null) {
                leftExclusives.remove(n);
                fromLeft[0] = n - 1;
                leftExclusives.put(n - 1, fromLeft);
                ans = Integer.max(ans, fromLeft[1] - fromLeft[0] - 1);
                continue;
            }
            // Both not found
            int[] newBound = new int[]{n - 1, n + 1};
            leftExclusives.put(n - 1, newBound);
            rightExclusives.put(n + 1, newBound);
        }
        return ans;
    }
}
