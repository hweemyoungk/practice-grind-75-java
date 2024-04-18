package ConstructBinaryTreefromPreorderandInorderTraversal_105;

import java.util.HashMap;
import java.util.Map;

/*Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.



Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]


Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.

[4/14/2024]19m out of 25m
44%/99%
O(#nodes^2)/O(depth) (w/o hashmap)
69%/26%
O(#nodes)/O(#nodes) (w/ hashmap)
*/
class Solution {

    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
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
    private int[] preorder;
    // private int[] inorder;
    private final Map<Integer, Integer> inorderValIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        // this.inorder = inorder;
        for (int i = 0; i < inorder.length; i++) {
            inorderValIndexMap.put(inorder[i], i);
        }
        return buildTree(0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int iPre, int jPre, int iIn, int jIn) {
        if (jPre < iPre) return null;
        if (iPre == jPre) return new TreeNode(preorder[iPre]);
        TreeNode root = new TreeNode(preorder[iPre]);
        // Use hashmap
        // int i;
        // for (i = iIn; i <= jIn; i++) {
        //     if (inorder[i] == root.val) break;
        // }
        int i = inorderValIndexMap.get(root.val);
        // Build left
        root.left = buildTree(iPre + 1, iPre + i - iIn, iIn, i - 1);
        // Build right
        root.right = buildTree(iPre + i - iIn + 1, jPre, i + 1, jIn);
        return root;
    }
}
