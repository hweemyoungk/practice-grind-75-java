package LongestRepeatingCharacterReplacement_424;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/*You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.



Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.


Constraints:

1 <= s.length <= 10^5
s consists of only uppercase English letters.
0 <= k <= s.length

[5/21/2024]Time up
9%/11% (...)
O(nlog(n))/O(26)
83%/52%
O(n)/O(26)

... AND HAPPY BIRTHDAY!
*/
class Solution {
    public int characterReplacement(String s, int k) {
        int iLeft = 0;
        int iRight = 0;
        int[] charToCount = new int[26];
        charToCount[s.charAt(0) - 'A']++;
        int currMaxCount = 1;
        int ans = 1;
        while (iRight++ < s.length() - 1) {
            char newChar = s.charAt(iRight);
            currMaxCount = Integer.max(currMaxCount, ++charToCount[newChar - 'A']);
            int othersCount = iRight - iLeft + 1 - currMaxCount;
            while (k < othersCount) {
                // Forward iLeft
                char charToRemove = s.charAt(iLeft++);
                if (charToCount[charToRemove - 'A']-- == currMaxCount) {
                    // Find currMaxCount - O(1)
                    currMaxCount = getMax(charToCount);
                    othersCount = iRight - iLeft + 1 - currMaxCount;
                    continue;
                }
                othersCount--;
            }
            ans = Integer.max(ans, iRight - iLeft + 1);
        }
        return ans;
    }

    private int getMax(int[] arr) {
        int max = arr[0];
        for (int n : arr) {
            max = Integer.max(max, n);
        }
        return max;
    }
}