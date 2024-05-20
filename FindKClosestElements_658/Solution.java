package FindKClosestElements_658;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b


Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]


Constraints:

1 <= k <= arr.length
1 <= arr.length <= 10^4
arr is sorted in ascending order.
-10^4 <= arr[i], x <= 10^4

[5/20/2024]9m out of 30m
(Suboptimal) 46%/81%
O(log(n) + k)/O(1) (w/o answer)
*/
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Find x
        int iRight = Arrays.binarySearch(arr, x);
        if (iRight < 0) {
            iRight = ~iRight;
        }
        int iLeft = iRight - 1;
        LinkedList<Integer> ans = new LinkedList<>();
        while (0 < k) {
            if (iLeft < 0) {
                ans.addLast(arr[iRight]);
                iRight++;
            } else if (arr.length - 1 < iRight) {
                ans.addFirst(arr[iLeft]);
                iLeft--;
            } else if (x - arr[iLeft] <= arr[iRight] - x) {
                ans.addFirst(arr[iLeft]);
                iLeft--;
            } else {
                ans.addLast(arr[iRight]);
                iRight++;
            }
            k--;
        }
        return ans;
    }
}
