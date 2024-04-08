package LowestCommonAncestorofaBinaryTree_236;

/*Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”



Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1


Constraints:

The number of nodes in the tree is in the range [2, 10^5].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q
p and q will exist in the tree.

[4/8/2024]Time up
72%/48% (Preorder)
100%/56% (Inorder)
O(n)/O(log(n)~n)
*/
class Solution {
    private TreeNode p;
    private TreeNode q;
    private TreeNode ans = null;
    private int nFound;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.p = p;
        this.q = q;
        // DFS
        //preorder(root);
        inorder(root);

        return ans;
    }

    private boolean inorder(TreeNode root) {
        if (root == null) return false;
        boolean leftFound = inorder(root.left);
        if (ans != null) return false;
        boolean selfFound = p.equals(root) || q.equals(root);
        if (leftFound && selfFound) {
            ans = root;
            return false;
        }
        boolean rightFound = inorder(root.right);
        if (ans != null) return false;
        if (rightFound && (leftFound || selfFound)) {
            ans = root;
            return false;
        }
        return leftFound || selfFound || rightFound;
    }

    private boolean preorder(TreeNode root) {
        if (root == null) return false;
        boolean selfFound = false;
        if (p.equals(root) || q.equals(root)) {
            selfFound = true;
            nFound++;
            // circuit break
            if (nFound == 2) return true;
        }

        if (nFound == 2) return true;
        // nFound < 2
        boolean leftFound = false;
        if (preorder(root.left)) {
            // Left found
            leftFound = true;
            if (selfFound) {
                ans = root;
                return true;
            }
        }

        if (nFound == 2) return true;
        // nFound < 2
        boolean rightFound = false;
        if (preorder(root.right)) {
            // Right found
            rightFound = true;
            if (selfFound) {
                ans = root;
                return true;
            }
        }

        if (leftFound && rightFound) {
            ans = root;
            return true;
        }
        return leftFound || rightFound || selfFound;
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
