package SortList_148;

/*Given the head of a linked list, return the list after sorting it in ascending order.



Example 1:


Input: head = [4,2,1,3]
Output: [1,2,3,4]
Example 2:


Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:

Input: head = []
Output: []


Constraints:

The number of nodes in the list is in the range [0, 5 * 10^4].
-10^5 <= Node.val <= 10^5


Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?

[4/18/2024]Time up
11%/27%
O(nlog(n))/O(1)
*/
class Solution {
    /**
     * Definition for singly-linked list.
     */
    private static class ListNode {
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

    public ListNode sortList(ListNode head) {
        int l = 0;
        var curr = head;
        while (curr != null) {
            l++;
            curr = curr.next;
        }
        int window = 1;
        ListNode dummyHead;
        while (true) {
            window <<= 1;
            dummyHead = new ListNode(0, head);
            var prevTail = dummyHead;
            var nextHead = head;
            while (nextHead != null) {
                var pNextHead = prevTail;
                var pm = prevTail;
                var h = nextHead;
                var m = nextHead;
                // Update nextHead
                for (int i = 0; i < window; i++) {
                    pNextHead = pNextHead.next;
                    nextHead = nextHead.next;
                    if (nextHead == null) break;
                }
                int size = window / 2;
                for (int i = 0; i < size; i++) {
                    pm = pm.next;
                    m = m.next;
                    if (m == null) break;
                }
                // Break links
                pm.next = null;
                pNextHead.next = null;
                prevTail.next = mergeSort(h, m);
                // Update prevTail
                for (int i = 0; i < window; i++) {
                    prevTail = prevTail.next;
                    if (prevTail == null) break;
                }
                if (prevTail != null) {
                    prevTail.next = nextHead;
                }
            }
            head = dummyHead.next;
            if (window >= l) break;
        }
        return dummyHead.next;
    }

    private ListNode mergeSort(ListNode h1, ListNode h2) {
        var dummyHead = new ListNode();
        var last = dummyHead;
        while (h1 != null || h2 != null) {
            if (h2 == null) {
                last.next = h1;
                h1 = h1.next;
            } else if (h1 == null) {
                last.next = h2;
                h2 = h2.next;
            } else if (h1.val <= h2.val) {
                last.next = h1;
                h1 = h1.next;
            } else {
                last.next = h2;
                h2 = h2.next;
            }
            last = last.next;
        }
        return dummyHead.next;
    }
}
