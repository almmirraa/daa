# Binary Tree Preorder Traversal (LeetCode 144)

## 1. Problem
Given the `root` of a binary tree, return the preorder traversal of its nodes' values as a list.

## 2. Approach
Preorder traversal follows the sequence: **Root (Current Node) -> Left Subtree -> Right Subtree**.

- I implement a recursive helper method `traverse(TreeNode node, List<Integer> result)`.
- **Base Case:** If `node == null`, return immediately.
- **Recursive Step:**
  1. Process the current node first by adding its value to the list: `result.add(node.val)`.
  2. Recursively visit the left child: `traverse(node.left, result)`.
  3. Recursively visit the right child: `traverse(node.right, result)`.

### Challenges and What Did Not Work
A common initial mistake when implementing tree traversals is confusing preorder with inorder and postorder. In preorder, visiting the current node must strictly precede visiting child subtrees. Attempting to create temporary lists inside every recursive frame caused excessive heap memory usage; passing a single shared `ArrayList` across the call stack keeps the implementation fast and memory-efficient.

## 3. Time Complexity
Time Complexity: O(n)
Let n be the number of nodes in the binary tree. The algorithm visits each node exactly once, and appending to ArrayList takes O(1) amortized time. Thus, the total time is linear: O(n).

## 4. Space Complexity
Space Complexity: O(n)
The auxiliary space is dominated by the recursive execution call stack. In the worst case (a degenerate or completely skewed tree), the recursion depth is n, requiring O(n) space. For a balanced tree, the stack depth is O(logn).

## 5. Reflection / Improvement
While the recursive method is clean, it can be written iteratively using an explicit Stack<TreeNode>. Because a stack is LIFO (Last In, First Out), we would push the right child first and then the left child to process the left subtree first. That avoids recursion limits without changing the O(n) time complexity.
