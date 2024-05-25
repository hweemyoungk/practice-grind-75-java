package GenerateParentheses_22;

import java.util.LinkedList;
import java.util.List;

/*Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]


Constraints:

1 <= n <= 8

[5/25/2024]13m out of 25m
100%/85%
O(Well, check this out... https://leetcode.com/problems/generate-parentheses/editorial/)/O(n w/o ans)
*/
class Solution {
    final List<String> ans = new LinkedList<>();

    public List<String> generateParenthesis(int n) {
        char[] charSeq = new char[2 * n];
        recursive(charSeq, 0, n, 0);
        return ans;
    }

    private void recursive(char[] charSeq, int iCurr, int remainingOpenings, int openCloseBalance) {
        if (iCurr == charSeq.length) {
            ans.add(String.valueOf(charSeq));
            return;
        }
        // Backtrack
        if (remainingOpenings != 0) {
            // Can open
            charSeq[iCurr] = '(';
            recursive(charSeq, iCurr + 1, remainingOpenings - 1, openCloseBalance + 1);
        }
        if (0 < openCloseBalance) {
            // Can close
            charSeq[iCurr] = ')';
            recursive(charSeq, iCurr + 1, remainingOpenings, openCloseBalance - 1);
        }
    }
}
