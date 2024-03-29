package BackspaceStringCompare_844;

/**
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.
 * <p>
 * Note that after backspacing an empty text, the text will continue empty.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 * Example 2:
 * <p>
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".
 * Example 3:
 * <p>
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, t.length <= 200
 * s and t only contain lowercase letters and '#' characters.
 * <p>
 * <p>
 * Follow up: Can you solve it in O(n) time and O(1) space?
 */
/*
* [3/29/2024]15m out of 15m
* 100%/78%
* O(n)/O(1)
* */
class Solution {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        while (i >= 0 || j >= 0) {
            // Find valid char for s
            char cs = 0;
            int nBsS = 0;
            while (i >= 0) {
                char c = s.charAt(i);
                if (c == '#') {
                    nBsS++;
                    i--;
                    continue;
                }
                // Not bs
                if (nBsS > 0) {
                    nBsS--;
                    i--;
                    continue;
                }
                // Not bs and no nBsS
                cs = c;
                i--;
                break;
            }
            char ct = 0;
            int nBsT = 0;
            while (j >= 0) {
                char c = t.charAt(j);
                if (c == '#') {
                    nBsT++;
                    j--;
                    continue;
                }
                // Not bs
                if (nBsT > 0) {
                    nBsT--;
                    j--;
                    continue;
                }
                // Not bs and no nBsS
                ct = c;
                j--;
                break;
            }
            if (cs != ct) return false;
        }
        return true;
    }
}
