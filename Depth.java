package queue;

import java.util.*;

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

public class Depth {
    public int findmax(TreeNode treeRoot) {
        if (treeRoot == null) {
            return 0;
        }
        int leftdepth = findmax(treeRoot.left);
        int rightSubtreeDepth = findmax(treeRoot.right);
        return Math.max(leftdepth, rightSubtreeDepth) + 1;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String input = sc.nextLine().trim();
        input = input.substring(1, input.length() - 1); // remove [ ]
        String[] values = input.split(",");
        
        TreeNode root = buildTree(values);
        Depth treeSolver = new Depth();
        int maxTreeDepth = treeSolver.findmax(root);
        System.out.println( maxTreeDepth);
        sc.close();
    }
    
    private static TreeNode buildTree(String[] values) {
        if (values.length == 0 || values[0].trim().equals("null")) return null;
        
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(values[0].trim()));
        queue.offer(root);
        
        for (int i = 1; i < values.length; i += 2) {
            TreeNode current = queue.poll();
            if (current != null) {
                String leftVal = values[i].trim();
                if (!leftVal.equals("null")) {
                    current.left = new TreeNode(Integer.parseInt(leftVal));
                    queue.offer(current.left);
                }
                
                if (i + 1 < values.length) {
                    String rightVal = values[i + 1].trim();
                    if (!rightVal.equals("null")) {
                        current.right = new TreeNode(Integer.parseInt(rightVal));
                        queue.offer(current.right);
                    }
                }
            }
        }
        return root;
    }
}
