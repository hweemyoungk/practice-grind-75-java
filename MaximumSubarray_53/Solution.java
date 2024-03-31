package MaximumSubarray_53;

/**
 * Given an integer array nums, find the
 * subarray
 * with the largest sum, and return its sum.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
/*
* [3/31/2024]16m out of 20m
* (Linear)
* 99%/29%
* O(n)/O(1)
* (Divide and Conquer)
* 8%/78%
* O(nlog(n))/O(log(n))
* */
class Solution {
    public int maxSubArray(int[] nums) {
        // Divide and conquer
        return maxSubArray(nums, 0, nums.length - 1);
    }

    private int maxSubArray(int[] nums, int lo, int hi) {
        int mid = (lo + hi + 1) / 2;
        int max = nums[mid];
        int maxIncludingMid = nums[mid];
        if (lo <= mid - 1) {
            max = Math.max(max, maxSubArray(nums, lo, mid - 1));
            int leftSum = 0;
            int leftMax = nums[mid - 1];
            for (int i = mid - 1; i >= lo; i--) {
                leftSum += nums[i];
                leftMax = Math.max(leftMax, leftSum);
            }
            if (leftMax>0) {
                maxIncludingMid+=leftMax;
            }
        }
        if (mid + 1 <= hi) {
            max = Math.max(max, maxSubArray(nums, mid + 1, hi));
            int rightSum = 0;
            int rightMax = nums[mid + 1];
            for (int i = mid + 1; i <= hi; i++) {
                rightSum += nums[i];
                rightMax = Math.max(rightMax, rightSum);
            }
            if (rightMax>0) {
                maxIncludingMid+=rightMax;
            }
        }
        max = Math.max(max, maxIncludingMid);
        return max;
    }
//    public int maxSubArray(int[] nums) {
//        int max = nums[0];
//        int sum = 0;
//        int ptr = 0;
//        while (ptr < nums.length) {
//            int num = nums[ptr];
//            sum += num;
//            max = Math.max(max, sum);
//            if (sum <= 0) {
//                // Discard subarray
//                sum = 0;
//            }
//            ptr++;
//        }
//        return max;
//    }
}
