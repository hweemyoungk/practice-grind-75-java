package InsertInterval_57;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * <p>
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * <p>
 * Return intervals after the insertion.
 * <p>
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^5
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 10^5
 */
/*
* [3/31/2024]Time up
* 99%/70%
* O(n)/O(n)*/
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Find insertion for start
        var list = new ArrayList<int[]>();
        int i;
        for (i = 0; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (curr[1] < newInterval[0]) {
                list.add(curr);
                continue;
            }
            break;
        }
        if (i == intervals.length) {
            list.add(newInterval);
            return list.toArray(new int[0][0]);
        }
        if (intervals[i][0] <= newInterval[0]) {
            int start = intervals[i][0];
            // Should be merged with curr
            // Find insertion for end
            int j;
            for (j = i; j < intervals.length; j++) {
                int[] curr = intervals[j];
                if (newInterval[1] <= curr[1]) {
                    break;
                }
            }
            if (j == intervals.length) {
                // Merge rest all
                list.add(new int[]{start, Math.max(intervals[intervals.length - 1][1], newInterval[1])});
                return list.toArray(new int[0][0]);
            }
            if (j == i) {
                // intervals[i] includes newInterval
                return intervals;
            }
            if (newInterval[1] < intervals[j][0]) {
                // Includes ~[j-1]
                list.add(new int[]{start, newInterval[1]});
                for (j = j; j < intervals.length; j++) {
                    list.add(intervals[j]);
                }
                return list.toArray(new int[0][0]);
            }
            // Merge ~[j]
            list.add(new int[]{start, intervals[j][1]});
            for (j = j + 1; j < intervals.length; j++) {
                list.add(intervals[j]);
            }
            return list.toArray(new int[0][0]);
        } else {
            // newInterval[0] < intervals[i][0]
            // Can be inserted prior to curr
            int start = newInterval[0];
            // Find insertion for end
            int j;
            for (j = i; j < intervals.length; j++) {
                int[] curr = intervals[j];
                if (newInterval[1] <= curr[1]) {
                    break;
                }
            }
            if (j == intervals.length) {
                // Merge rest all
                list.add(new int[]{start, Math.max(intervals[intervals.length - 1][1], newInterval[1])});
                return list.toArray(new int[0][0]);
            }
//            if (j == i) {
//                // intervals[i][0] changes to newInterval[0]
//                intervals[i][0] = newInterval[0];
//                return intervals;
//            }
            if (newInterval[1] < intervals[j][0]) {
                // Includes ~[j-1]
                list.add(new int[]{start, newInterval[1]});
                for (j = j; j < intervals.length; j++) {
                    list.add(intervals[j]);
                }
                return list.toArray(new int[0][0]);
            }
            // Merge ~[j]
            list.add(new int[]{start, intervals[j][1]});
            for (j = j + 1; j < intervals.length; j++) {
                list.add(intervals[j]);
            }
            return list.toArray(new int[0][0]);
        }
    }
}
