package EvaluateReversePolishNotation_150;

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * <p>
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * <p>
 * Note that:
 * <p>
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 * <p>
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 * <p>
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
/*
* [4/1/2024]22m out of 30m
* 74%/5%
* 74%/99% (optimized)
* O(n)/O(n)
* Q. What about using a stack?
* */
class Solution {
    public int evalRPN(String[] tokens) {
        ListNode dummyHead = new ListNode(null, null, null);
        ListNode prev = dummyHead;
        for (int i = 0; i < tokens.length; i++) {
            ListNode curr = new ListNode(tokens[i], null, null);
            prev.next = curr;
            curr.prev = prev;
            prev = curr;
        }
        ListNode curr = dummyHead.next;
        while (true) {
            if (curr.val.equals("+")) {
                // ListNode[] prevPair = getPrevPair(curr);
                curr.val = String.valueOf(Integer.parseInt(curr.prev.prev.val) + Integer.parseInt(curr.prev.val));
                //curr.prev = prevPair[0].prev;
                curr.prev = curr.prev.prev.prev;
            } else if (curr.val.equals("-")) {
                // ListNode[] prevPair = getPrevPair(curr);
                curr.val = String.valueOf(Integer.parseInt(curr.prev.prev.val) - Integer.parseInt(curr.prev.val));
                //curr.prev = prevPair[0].prev;
                curr.prev = curr.prev.prev.prev;
            } else if (curr.val.equals("*")) {
                // ListNode[] prevPair = getPrevPair(curr);
                curr.val = String.valueOf(Integer.parseInt(curr.prev.prev.val) * Integer.parseInt(curr.prev.val));
                //curr.prev = prevPair[0].prev;
                curr.prev = curr.prev.prev.prev;
            } else if (curr.val.equals("/")) {
                // ListNode[] prevPair = getPrevPair(curr);
                curr.val = String.valueOf(Integer.parseInt(curr.prev.prev.val) / Integer.parseInt(curr.prev.val));
                //curr.prev = prevPair[0].prev;
                curr.prev = curr.prev.prev.prev;
            } else {
                // number
                // NO-OP
            }
            if (curr.next==null) break;
            curr = curr.next;
        }
        return Integer.parseInt(curr.val);
    }

//    private ListNode[] getPrevPair(ListNode curr) {
//        ListNode ptr = curr.prev;
//        while (ptr.val == null) {
//            ptr = ptr.prev;
//        }
//        ListNode latter = ptr;
//        ptr = ptr.prev;
//        while (ptr.val == null) {
//            ptr = ptr.prev;
//        }
//        ListNode former = ptr;
//        return new ListNode[]{former, latter};
//    }
}

class ListNode {
    ListNode prev;
    ListNode next;
    String val;

    public ListNode(String val, ListNode prev, ListNode next) {
        this.prev = prev;
        this.next = next;
        this.val = val;
    }
}
