# Maximum Depth of Binary Tree (LeetCode 104)

## 1. Problem
Given the root of a binary tree, find its maximum depth. The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

## 2. Approach
I implemented a postorder Depth-First Search (DFS) recursive strategy:

- **Base Case:** If `root == null`, the depth is `0`.
- **Divide:** Recursively compute the maximum depth of the left subtree (`leftDepth = maxDepth(root.left)`) and right subtree (`rightDepth = maxDepth(root.right)`).
- **Combine:** The depth of the current subtree is the maximum of both child depths plus `1` for the current node: `Math.max(leftDepth, rightDepth) + 1`.

### Challenges and What Did Not Work
An early mistake was attempting to maintain a global counter that incremented on each step, which led to incorrect results because sibling branches kept accumulating counts from each other. Switching to a divide-and-conquer strategy where each subtree returns its own localized height cleanly solved the problem.

## 3. Time Complexity
Time Complexity: O(n)
Where n is the number of nodes in the binary tree. The algorithm visits every node exactly once to calculate the subtree height, yielding linear time complexity O(n).

## 4. Space Complexity
Space Complexity: O(n)
The memory consumed corresponds to the recursive call stack. In the worst-case scenario (completely skewed tree), the recursion depth is O(n). For a balanced tree, the space complexity is O(logn).

## 5. Reflection / Improvement
This DFS recursion is optimal. Alternatively, a Breadth-First Search (BFS) level-order traversal using a Queue can compute the depth by counting the number of levels. BFS uses O(w) space where w is the maximum width of the tree, which is beneficial for very deep and narrow trees.
