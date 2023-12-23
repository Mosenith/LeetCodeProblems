import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {
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
    public static void printTreeNode(TreeNode root) {
        if(root == null) {
            return;
        }
        System.out.println(root.val);
        System.out.println("Left ==> ");
        printTreeNode(root.left);

        System.out.println("Right ==> ");
        printTreeNode(root.right);
    }

    public static void main(String[] args) {
        // 0,1,2
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);

        System.out.println(preorderTraversal(root));
    }

    // ***************** 1st Method ******************
    // Approach 1: Recursive -> root-left-right
    // Runtime  : 0ms         -> + 100.00%
    // Memory   : 41.93MB     -> + 10.42%
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ls = new ArrayList<>();
        preorder(root,ls);
        return ls;
    }

    private static void preorder(TreeNode root, List<Integer> ls) {
        if(root == null) return;

        ls.add(root.val);
        preorder(root.left, ls);
        preorder(root.right, ls);
    }
    // ***************** End of 1st Method ******************

}
