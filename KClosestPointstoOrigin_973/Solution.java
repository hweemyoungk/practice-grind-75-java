package KClosestPointstoOrigin_973;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 * <p>
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * <p>
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 * <p>
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= points.length <= 10^4
 * -10^4 <= xi, yi <= 10^4
 */
/*
* [4/1/2024]30m out of 30m
* 93%/14%
* O(n~n2)/O(n)
* */
class Solution {
    private int k;
    private int remaining;
    private int[][] ans;

    public int[][] kClosest(int[][] points, int k) {
        ans = new int[k][2];
        this.k = k;
        remaining = k;
        LinkedList<Point> newPoints = new LinkedList<>();
        for (var point : points) {
            newPoints.add(new Point(point[0], point[1], Math.pow(point[0], 2) + Math.pow(point[1], 2)));
        }
        while (remaining != 0) {
            if (newPoints.size() == remaining) {
                for (var point : newPoints) {
                    ans[k - remaining] = new int[]{point.x, point.y};
                    remaining--;
                }
                break;
            }
            newPoints = quickselect(newPoints);
        }
        return ans;
    }

    private LinkedList<Point> quickselect(LinkedList<Point> points) {
        int iPivot = ThreadLocalRandom.current().nextInt(points.size());
        Point pivot = points.get(iPivot);
        LinkedList<Point> less = new LinkedList<>();
        LinkedList<Point> moreOrEqual = new LinkedList<>();
        for (var curr :
                points) {
            if (curr.dist < pivot.dist) {
                less.add(curr);
            } else {
                moreOrEqual.add(curr);
            }
        }
        if (less.size() <= remaining) {
            for (var point : less) {
                ans[k - remaining] = new int[]{point.x, point.y};
                remaining--;
            }
            return moreOrEqual;
        }
        return less;
    }
}

class Point {
    int x;
    int y;
    double dist;

    public Point(int x, int y, double dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}