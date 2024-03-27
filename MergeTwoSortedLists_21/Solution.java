package MergeTwoSortedLists_21;

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
/*
[3/26/2024]5m out of 20m
?%/45%
O(N+M)/O(1)
* */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        var dummyHead = new ListNode();
        var curr = dummyHead;
        while (!(list1 == null && list2 == null)) {
            if (list1==null){
                curr.next=list2;
                curr=curr.next;
                list2=list2.next;
                continue;
            }
            if (list2==null){
                curr.next=list1;
                curr=curr.next;
                list1=list1.next;
                continue;
            }
            if (list1.val<list2.val){
                curr.next=list1;
                curr=curr.next;
                list1=list1.next;
                continue;
            }
            curr.next=list2;
            curr=curr.next;
            list2=list2.next;
        }
        return dummyHead.next;
    }
}
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