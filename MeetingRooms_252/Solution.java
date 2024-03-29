package MeetingRooms_252;

import java.util.Arrays;

/**
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 * <p>
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti < endi <= 10^6
 */
/*
* [3/28/2024]8m out of 20m
* 96%/63%
* O(nlog(n))/O(1)
* */
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length == 0) return true;
        Arrays.sort(intervals, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });
        for (int i = 1; i < intervals.length; i++) {
            // Look behind
            if (intervals[i - 1][1] <= intervals[i][0]) continue;
            return false;
        }
        return true;
    }
}
