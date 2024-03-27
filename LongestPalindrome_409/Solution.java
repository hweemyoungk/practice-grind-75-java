package LongestPalindrome_409;

/**
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 * <p>
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 * Example 2:
 * <p>
 * Input: s = "a"
 * Output: 1
 * Explanation: The longest palindrome that can be built is "a", whose length is 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 2000
 * s consists of lowercase and/or uppercase English letters only.
 */
/*
* [3/27/2024]7m out of 20m
* 74%/91%
* O(n)/O(1)*/
class Solution {
    public int longestPalindrome(String s) {
        int[] counter = new int[46];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < 97) {
                // Uppercase
                c -= 42;
            } else {
                c -= 97;
            }
            counter[c] += 1;
        }
        int length = 0;
        boolean hasOdd = false;
        for (int i = 0; i < 46; i++) {
            int count = counter[i];
            if ((count & 1) == 1) {
                hasOdd = true;
                length += (count - 1);
                continue;
            }
            length += count;
        }
        return hasOdd ? length + 1 : length;
    }
}