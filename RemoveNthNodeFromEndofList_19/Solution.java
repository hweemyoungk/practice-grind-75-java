package RemoveNthNodeFromEndofList_19;

/*Given the head of a linked list, remove the nth node from the end of the list and return its head.



Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]


Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz


Follow up: Could you do this in one pass?

[5/6/2024]13m out of 20m
100%/80%
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        ListNode curr = head;
        ListNode targetPrev = null;
        while (curr != null) {
            n--;
            if (targetPrev != null) targetPrev = targetPrev.next;
            if (n == 0) targetPrev = dummyHead;
            curr = curr.next;
        }

        if (targetPrev.next == head) {
            return head.next;
        }
        targetPrev.next = targetPrev.next.next;
        return head;
    }
}
