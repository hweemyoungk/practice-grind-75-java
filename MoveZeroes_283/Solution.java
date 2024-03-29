package MoveZeroes_283;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Note that you must do this in-place without making a copy of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * <p>
 * <p>
 * Follow up: Could you minimize the total number of operations done?
 */
/*[3/29/2024]14m out of 20m
* 85%/13%
* O(n)/O(1)*/
class Solution {
    public void moveZeroes(int[] nums) {
        int z = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                z = i;
                break;
            }
        }
        if (z == -1) return;
        for (int nz = 0; nz < nums.length; nz++) {
            int num = nums[nz];
            if (num == 0 || nz < z) continue;
            // Swap
            nums[z]=num;
            nums[nz]=0;
            while (nums[z]!=0) {
                z++;
            }
        }
    }
}
