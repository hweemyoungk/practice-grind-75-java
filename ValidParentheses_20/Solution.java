package ValidParentheses_20;

import java.util.HashMap;
import java.util.Stack;

/*
* [3/26/2024]7m out of 20m
* 54%/88%
* O(n)/O(n)
* */
public class Solution {
    public boolean isValid(String s) {
        var pairs = new HashMap<Character, Character>();
        pairs.put('{', '}');
        pairs.put('(', ')');
        pairs.put('[', ']');
        var stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (pairs.containsKey(c)) {
                stack.push(c);
                continue;
            }
            // c is closing
            Character lastChar;
            try {
                lastChar = stack.pop();
            } catch (Exception e) {
                return false;
            }
            if (!pairs.get(lastChar).equals(c)) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
