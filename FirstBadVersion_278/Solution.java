package FirstBadVersion_278;

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 * Example 2:
 * <p>
 * Input: n = 1, bad = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= bad <= n <= 2^31 - 1
 */
/*
* [3/27/2024]13m out of 20m
* 97%/50%
* O(log(n))/O(1)
* */
public abstract class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        // Left-most binary search
        int lo = 1;
        int hi = n;
        while (lo < hi) {
            // int mid=(lo+hi)/2;
            // Overflow
            int mid = lo + (hi - lo) / 2;
            if (isBadVersion(mid)) {
                hi = mid;
                continue;
            }
            lo = mid + 1;
        }
        return lo;
    }
}

abstract class VersionControl {
    abstract boolean isBadVersion(int i);
}