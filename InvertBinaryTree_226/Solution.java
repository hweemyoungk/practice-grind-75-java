package InvertBinaryTree_226;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/*
* [3/26/2024]6m out of 15m
* 100%/48%
* O(n)/O(n/2)=O(n)*/
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root==null) return null;
        // BFS+iterative
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            // Swap
            var right=curr.right;
            curr.right=curr.left;
            curr.left=right;
            if (curr.left!=null) {
                queue.add(curr.left);
            }
            if (curr.right!=null) {
                queue.add(curr.right);
            }
        }
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}