package LongestSubstringWithoutRepeatingCharacters_3;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest
 * substring
 * without repeating characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 */
/*
* [4/1/2024]11m out of 30m
* 21%/81%
* O(n)/O(m) where n==s.length() and m is num of unique chars
* */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l = s.length();
        if (l == 0) return 0;
        int lo = 0;
        int hi = 1;
        int max = 1;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        charIndexMap.put(s.charAt(0), 0);
        while (hi < l) {
            char curr = s.charAt(hi);
            Integer iPrev = charIndexMap.get(curr);
            if (iPrev != null && lo <= iPrev) {
                // Move lo
                lo = iPrev+1;
            }
            // Set or replace map
            charIndexMap.put(curr, hi);
            max = Math.max(max, hi-lo+1);
            hi++;
        }
        return max;
    }
}
