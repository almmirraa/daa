# Sum of Left Leaves (LeetCode 404)

## 1. Problem
Given the `root` of a binary tree, return the sum of all left leaves. A leaf is a node with no children, and a left leaf is a leaf that is the left child of another node.

## 2. Approach
A node cannot determine on its own whether it is a left child; the parent node must inspect its left child directly:

- **Base Case:** If `root == null`, return `0`.
- **Identify Left Leaf:** Check if `root.left != null` and whether that left child is a leaf (`root.left.left == null && root.left.right == null`).
  - If it is a leaf, add `root.left.val` directly to our sum.
  - If it is an internal node (has children of its own), recurse into the left subtree: `sumOfLeftLeaves(root.left)`.
- **Inspect Right Subtree:** Regardless of the left child, recurse into `sumOfLeftLeaves(root.right)` because left leaves may exist deeper inside the right subtree.

### Challenges and What Did Not Work
An early misunderstanding was adding `root.val` whenever a node reached its base case if it had no children. However, a single-node tree (just a root) or a right leaf would incorrectly be added. The distinction must be maintained: the node must be both a **leaf** AND a **left child**. Looking ahead from the parent node cleanly resolves this condition.

## 3. Time Complexity
Time Complexity: O(n)
The algorithm traverses every node in the binary tree exactly once. Constant-time checks and additions at each node produce an overall time complexity of O(n).

## 4. Space Complexity
Space Complexity: O(n)
The space consumed is bounded by the recursive call stack depth: O(n) for a completely skewed tree in the worst case, and O(logn) for a balanced tree.

## 5. Reflection / Improvement
This postorder-style recursive solution is concise and optimal. An iterative BFS using a Queue could also be used: whenever an unqueued node has a left child that is a leaf, add its value to sum, and push all other valid child nodes into the queue.
