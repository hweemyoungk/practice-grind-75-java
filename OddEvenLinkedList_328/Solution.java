package OddEvenLinkedList_328;

import java.util.List;

/*Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity.



Example 1:


Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
Example 2:


Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]


Constraints:

The number of nodes in the linked list is in the range [0, 10^4].
-10^6 <= Node.val <= 10^6

[5/16/2024]9m out of 25m
100%/32%
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

    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode oddDummyHead = new ListNode();
        ListNode oddTail = oddDummyHead;
        ListNode evenDummyHead = new ListNode();
        ListNode evenTail = evenDummyHead;
        ListNode curr = head;
        boolean isOdd = true;
        while (curr != null) {
            if (isOdd) {
                oddTail.next = curr;
                oddTail = curr;
            } else {
                evenTail.next = curr;
                evenTail = curr;
            }
            isOdd = !isOdd;
            curr = curr.next;
        }
        oddTail.next = evenDummyHead.next;
        evenTail.next = null;
        return oddDummyHead.next;
    }
}
