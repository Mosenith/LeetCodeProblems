import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class PathSum {
    public static class TreeNode {
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

    public static void main(String[] args) {
//        root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        int target = 22;

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);

        System.out.println(hasPathSum(root2,1));
    }

    // ***************** 1st Method ******************
    // Approach 1: Make use of DFS/BFS & target -= root.val
    // if target=0 && left = null && right = null => true
    // Otherwise, keep recursive of dfs(root.left) || dfs(root.right)
    // Base Case, if root = null => false
    // Runtime  : 0ms            -> + 100.00%
    // Memory   : 42.91MB        -> + 84.04%
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        return helper(root, targetSum);
    }

    private static boolean helper(TreeNode root, int target) {
        if(root == null) return false;

        target -= root.val;
        if(target == 0 && root.left == null && root.right == null) {
            return true;
        }

        return helper(root.left, target) || helper(root.right, target);
    }
    // ***************** End of 1st Method ******************
}
