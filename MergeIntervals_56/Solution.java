package MergeIntervals_56;


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/*Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.



Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


Constraints:

1 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^4

[4/8/2024]11m out of 30m
57%/50%
O(nlog(n))/O(n)
*/
class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort + Linear scan
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        LinkedList<int[]> ans = new LinkedList<>();
        int[] prev = intervals[0];
        ans.add(prev);
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (prev[1] < curr[0]) {
                ans.add(curr);
                prev = curr;
                continue;
            }
            // merge
            prev[1] = Integer.max(prev[1], curr[1]);
        }
        return ans.toArray(new int[0][0]);
    }
}
