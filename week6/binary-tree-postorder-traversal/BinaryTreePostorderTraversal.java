import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        // Left -> Right -> Root
        traverse(node.left, result);
        traverse(node.right, result);
        result.add(node.val);
    }
} 
