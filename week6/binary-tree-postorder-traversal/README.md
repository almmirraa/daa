# Binary Tree Postorder Traversal (LeetCode 145)

## 1. Problem
Given the `root` of a binary tree, return the postorder traversal of its nodes' values as a list.

## 2. Approach
Postorder traversal processes child subtrees completely before processing the parent node: **Left Subtree -> Right Subtree -> Current Node (Root)**.

- **Base Case:** If `node == null`, return immediately.
- **Recursive Step:**
  1. Recursively traverse the left child: `traverse(node.left, result)`.
  2. Recursively traverse the right child: `traverse(node.right, result)`.
  3. Append the current node's value: `result.add(node.val)`.

### Challenges and What Did Not Work
Postorder traversal is naturally recursive. Writing an iterative solution for postorder is noticeably trickier than preorder or inorder because a node can only be processed after both its left and right subtrees have been visited. Handling this iteratively requires tracking the previously visited node to avoid revisiting subtrees. The recursive DFS strategy handles this call ordering cleanly and without state tracking bugs.

## 3. Time Complexity
Time Complexity: O(n)
Every node is visited a constant number of times during the traversal. Performing O(1) operations per node across all n nodes yields an overall time complexity of O(n).

## 4. Space Complexity
Space Complexity: O(n)
The auxiliary space required corresponds to the recursion stack. In the worst case of a skewed tree, the recursion depth is n, giving O(n) space complexity. For a balanced tree, the stack space is O(logn).

## 5. Reflection / Improvement
Postorder traversal is the foundation for bottom-up tree calculations, such as deleting a tree or computing directory sizes. An iterative variation can be implemented using two stacks or a single stack with a LinkedList.addFirst() reverse-preorder trick.
