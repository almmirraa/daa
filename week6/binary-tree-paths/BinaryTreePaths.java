import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root != null) {
            buildPaths(root, "", paths);
        }
        return paths;
    }

    private void buildPaths(TreeNode node, String path, List<String> paths) {
        path += Integer.toString(node.val);

        // Если дошли до листа, сохраняем путь целиком
        if (node.left == null && node.right == null) {
            paths.add(path);
            return;
        }

        if (node.left != null) {
            buildPaths(node.left, path + "->", paths);
        }
        if (node.right != null) {
            buildPaths(node.right, path + "->", paths);
        }
    }
} 
