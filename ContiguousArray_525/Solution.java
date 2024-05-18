package ContiguousArray_525;

import java.util.HashMap;
import java.util.Map;

/*Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.



Example 1:

Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
Example 2:

Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.


Constraints:

1 <= nums.length <= 10^5
nums[i] is either 0 or 1.

[5/18/2024]14m out of 30m
5%/77%
O(n)/O(~n)
(Optimized)17m out of 30m
96%/39%
O(n)/O(~n)
*/
class Solution {
    public int findMaxLength(int[] nums) {
        int currDiff = 0; // #0 - #1
        int ans = 0;
        Map<Integer, Integer> diffFirstIndexMap = new HashMap<>() {{
            put(0, -1);
        }};
        Integer firstIndex;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == 0) {
                currDiff++;
            } else {
                currDiff--;
            }
            firstIndex = diffFirstIndexMap.get(currDiff);
            if (firstIndex == null) {
                diffFirstIndexMap.put(currDiff, j);
                continue;
            }
            ans = Integer.max(ans, j - firstIndex);
        }
        return ans;
    }
}
