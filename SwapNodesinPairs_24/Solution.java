package SwapNodesinPairs_24;

/*Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)



Example 1:


Input: head = [1,2,3,4]
Output: [2,1,4,3]
Example 2:

Input: head = []
Output: []
Example 3:

Input: head = [1]
Output: [1]


Constraints:

The number of nodes in the list is in the range [0, 100].
0 <= Node.val <= 100

[5/14/2024]10m out of 25m
100%/93%
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

    public ListNode swapPairs(ListNode head) {
        // prevTail
        // nextHead
        // Dummy head
        ListNode dummyHead = new ListNode(0, head);
        ListNode prevTail = dummyHead;
        while (true) {
            ListNode first = prevTail.next;
            if (first == null) {
                break;
            }
            if (first.next == null) {
                break;
            }
            ListNode nextHead = first.next.next;
            prevTail.next = first.next;
            first.next.next = first;
            first.next = nextHead;
            prevTail = first;
        }
        return dummyHead.next;
    }
}
