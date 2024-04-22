package KthSmallestElementinaBST_230;

/*230. Kth Smallest Element in a BST
Solved
Medium
Topics
Companies
Hint
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.



Example 1:


Input: root = [3,1,4,null,2], k = 1
Output: 1
Example 2:


Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3


Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 10^4
0 <= Node.val <= 10^4


Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?

[4/20/2024]9m out of 25m
100%/94%
O(log(n)+k ~ n+k)/O(log(n)+k ~ n+k)
*/
class Solution {
    /**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int k;
    private int ans;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        // Inorder
        inorder(root);
        return ans;
    }

    private boolean inorder(TreeNode root) {
        if (root == null) return false;
        if (inorder(root.left)) return true;
        // Self
        if (--k == 0) {
            ans = root.val;
            return true;
        }
        return inorder(root.right);
    }
}
