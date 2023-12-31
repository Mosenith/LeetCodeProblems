import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
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
        // 0,1,2
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        System.out.println(binaryTreePaths2(root));
    }

    // ***************** 1st Method ******************
    // Approach 1: Use list to store root.val
    // In dfs(), if root = null -> return
    // Check if root.left = null & root.right = null -> add root.val to list
    // Use outputUtil() to output the result format
    // Runtime  : 3ms         -> + 70.07%
    // Memory   : 42.56MB     -> + 15.49%
    static List<String> res = new ArrayList<>();
    public static List<String> binaryTreePaths(TreeNode root) {
        List<Integer> ls = new ArrayList<>();

        dfs(root,ls);
        return res;
    }

    private static void dfs(TreeNode root, List<Integer> ls) {
        if(root == null) return;
        if(root.left == null && root.right == null) {
            ls.add(root.val);
            outputUtil(ls);
            return;
        }

        ls.add(root.val);
        dfs(root.left, new ArrayList<>(ls));
        dfs(root.right,new ArrayList<>(ls));
    }

    private static void outputUtil(List<Integer> ls) {
        String s = ls.get(0) + "";
        for(int i=1; i<ls.size(); i++) {
            s += "->";
            if(i == ls.size()-1) {
                s += ls.get(i) + "";
            } else {
                s += ls.get(i);
            }
        }
        res.add(s.toString());
    }
    //  ***************** End of 1st Method ******************

    // ***************** 2nd Method ******************
    // Approach 2: Use StringBuilder to add to list<String> when both side of root = null
    // Otherwise, append ("->") and recursion of root.left, then root.right
    // Runtime  : 1ms         -> + 99.86%
    // Memory   : 42.20MB     -> + 33.19%
    public static List<String> binaryTreePaths2(TreeNode root) {
        List<String> ans = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        preorder(root, ans, s);

        return ans;
    }

    private static void preorder(TreeNode root, List<String> ans, StringBuilder s) {
        if(root == null) {
            return;
        }

        s.append(root.val);
        if(root.left == null && root.right == null) {
            ans.add(s.toString());
        } else {
            s.append("->");

            preorder(root.left,ans,new StringBuilder(s));
            preorder(root.right,ans,new StringBuilder(s));
        }
    }
    //  ***************** End of 2nd Method ******************
}
