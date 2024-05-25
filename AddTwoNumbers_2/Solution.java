package AddTwoNumbers_2;

/*You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.



Example 1:


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]


Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.

[5/25/2024]15m out of 25m
100%/76%
100%/86% (Optimized)
O(n)/O(1)
*/
class Solution {
    /**
     * Definition for singly-linked list.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // In-place
        ListNode dummyHead = new ListNode();
        ListNode prev = dummyHead;
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        int carry = 0;
        while (true) {
            if (curr1 == null && curr2 == null) {
                if (carry == 1) {
                    prev.next = new ListNode(1);
                }
                break;
            }

            int val1 = curr1 == null ? 0 : curr1.val;
            int val2 = curr2 == null ? 0 : curr2.val;
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            prev.next = curr1 != null ? curr1 : curr2;
            prev.next.val = sum % 10;

            prev = prev.next;
            curr1 = curr1 == null ? null : curr1.next;
            curr2 = curr2 == null ? null : curr2.next;
        }
        return dummyHead.next;
    }
}
