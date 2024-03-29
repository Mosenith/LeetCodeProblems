import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class EvenOddTree {
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
        printTreeNode(root.left);
        printTreeNode(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(7);

        System.out.println(isEvenOddTree(root));
    }

    // ***************** 1st Method ******************
    // Approach 1: Use queues and start with even-level,
    // then change after end of inner loop
    // Runtime  : 9ms           -> + 99.20%
    // Memory   : 65.52MB       -> + 70.60%
    // odd-level => even nodes, strictly decreasing
    // even-level => odd nodes, strictly increasing
    public static boolean isEvenOddTree(TreeNode root) {
        boolean even = true;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()) {
            // start with even level
            int prev = (even) ? 0 : 1000001;
            for(int i=q.size(); i>0; i--) {
                TreeNode tmp = q.pollFirst();
                // even-level condition
                if(even && (tmp.val%2==0 || prev >= tmp.val)) {
                    return false;
                }
                // odd-level condition
                if(!even && (tmp.val%2==1 || prev <= tmp.val)) {
                    return false;
                }
                prev = tmp.val;
                if(tmp.left != null) {
                    q.offer(tmp.left);
                }
                if(tmp.right != null) {
                    q.offer(tmp.right);
                }
            }
            even = !even;
        }
        return true;
    }
    //  ***************** End of 1st Method ******************
}
