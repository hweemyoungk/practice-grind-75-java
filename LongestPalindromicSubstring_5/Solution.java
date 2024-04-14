package LongestPalindromicSubstring_5;

/*Given a string s, return the longest
palindromic

substring
 in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.

[4/14/2024]13m out of 25m
71%/64%
O(len(s) + sum(len(palindrome)))/O(1)
*/
class Solution {
    public String longestPalindrome(String s) {
        String ans = s.substring(0, 1);
        // Single
        for (int i = 1; i < s.length(); i++) {
            char center = s.charAt(i);
            int j = i;
            int k = i;
            // Expand
            while (0 < j && k < s.length() - 1) {
                j--;
                k++;
                if (s.charAt(j) != s.charAt(k)) {
                    j++;
                    k--;
                    break;
                }
            }
            if (ans.length() < k - j + 1) {
                ans = s.substring(j, k + 1);
            }
        }
        // Double
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) continue;
            int j = i;
            int k = i + 1;
            // Expand
            while (0 < j && k < s.length() - 1) {
                j--;
                k++;
                if (s.charAt(j) != s.charAt(k)) {
                    j++;
                    k--;
                    break;
                }
            }
            if (ans.length() < k - j + 1) {
                ans = s.substring(j, k + 1);
            }
        }
        return ans;
    }
}
