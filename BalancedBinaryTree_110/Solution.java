package BalancedBinaryTree_110;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class TreeNode {
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

/**
 * Given a binary tree, determine if it is
 * height-balanced
 * .
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * Example 3:
 * <p>
 * Input: root = []
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 5000].
 * -10^4 <= Node.val <= 10^4
 */
/*
* [3/26/2024]14m out of 15m
* 33%/33%
* O(n)/O(n)
* */
class Solution {
    public boolean isBalanced(TreeNode root) {
        Tmp tmp = getDepth(root);
        return tmp.isBalanced;
    }

    private Tmp getDepth(TreeNode root) {
        if (root==null) return new Tmp(0, true);
        int depth=1;
        Tmp left = getDepth(root.left);
        if (!left.isBalanced) return new Tmp(-1, false);
        Tmp right = getDepth(root.right);
        if (!right.isBalanced) return new Tmp(-1, false);
        if (1<Math.abs(left.depth- right.depth)) return new Tmp(-1, false);
        return new Tmp(depth+Math.max(left.depth, right.depth), true);
    }

    class Tmp {
        int depth;
        boolean isBalanced;

        Tmp(int depth, boolean isBalanced) {
            this.depth = depth;
            this.isBalanced = isBalanced;
        }
    }
}