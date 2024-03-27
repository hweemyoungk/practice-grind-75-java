package ValidPalindrome_125;

import java.util.HashSet;

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * Example 2:
 *
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * Example 3:
 *
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2 * 10^5
 * s consists only of printable ASCII characters.
 */
/*
* [3/26/2024]11m out of 15m
* 100%/98%
* O(n)/O(n)
* */
class Solution {
    public boolean isPalindrome(String s) {
        var i=0;
        var j=s.length()-1;
        while (i<j) {
            char left = s.charAt(i);
            // Check uppercase
            if (65<=left && left<=90) {
                left+=32;
            }
            // 48~57, 97~122
            if (!(48<=left && left<=57) && !(97<=left && left<=122)) {
                i+=1;
                continue;
            }
            char right = s.charAt(j);
            if (65<=right && right<=90) {
                right+=32;
            }
            if (!(48<=right && right<=57) && !(97<=right && right<=122)) {
                j-=1;
                continue;
            }
            if (left!=right) {
                return false;
            }
            i+=1;
            j-=1;
        }
        return true;
    }
}