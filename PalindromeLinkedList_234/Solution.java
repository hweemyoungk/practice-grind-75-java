package PalindromeLinkedList_234;

/**
 * Given the head of a singly linked list, return true if it is a
 * palindrome
 * or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,2,1]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 10^5].
 * 0 <= Node.val <= 9
 * <p>
 * <p>
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
/*
* [3/29/2024]12m out of 20m
* 99%/52%
* O(n)/O(1)
* */
class Solution {
    public boolean isPalindrome(ListNode head) {
        // Dummy head
        ListNode midPrev = new ListNode();
        midPrev.next = head;
        ListNode mid = head;
        while (head != null) {
            head = head.next;
            if (head == null) {
                // Odd
                mid = mid.next;
                break;
            }
            ListNode midNext = mid.next;
            mid.next = midPrev;
            midPrev = mid;
            mid = midNext;
            head = head.next;
        }
        while (mid != null) {
            if (mid.val != midPrev.val) return false;
            mid=mid.next;
            midPrev=midPrev.next;
        }
        return true;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class ListNode {
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
