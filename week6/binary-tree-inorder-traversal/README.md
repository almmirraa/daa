# Binary Tree Inorder Traversal (LeetCode 94)

## 1. Problem
Given the root of a binary tree, return the inorder traversal of its nodes' values as a list.

## 2. Approach
Inorder traversal strictly follows the order: **Left Subtree -> Current Node (Root) -> Right Subtree**.

- I implement a recursive helper function `traverse(TreeNode node, List<Integer> result)`.
- **Base Case:** If `node == null`, return immediately.
- **Recursive Step:**
  1. Recursively traverse the left child: `traverse(node.left, result)`.
  2. Visit and record the current node's value: `result.add(node.val)`.
  3. Recursively traverse the right child: `traverse(node.right, result)`.

### Challenges and What Did Not Work
Initially, I attempted to create and return a new `ArrayList` within every recursive call and concatenate them using `addAll()`. While functionally correct, creating multiple intermediate lists generated high memory overhead and degraded performance. Refactoring to pass a single shared `List<Integer>` through the recursion tree resolved the issue.

## 3. Time Complexity
Time Complexity: O(n)
Let n be the total number of nodes in the binary tree. Every node is visited exactly once, and adding an element to ArrayList takes O(1) amortized time. Thus, the total time complexity scales linearly: O(n).

## 4. Space Complexity
Space Complexity: O(n)
The auxiliary space is determined by the recursion call stack. In the worst case (a completely skewed tree like a linked list), the recursion depth is n, requiring O(n) stack space. In a balanced tree, it requires O(logn) space. Excluding the output list, the worst-case space complexity is O(n).

## 5. Reflection / Improvement
The recursive approach is straightforward and concise. An alternative is an iterative approach using an explicit Stack<TreeNode> to simulate the call stack. While the asymptotic time and space complexities remain O(n), an iterative version avoids the risk of a StackOverflowError on extremely deep trees.
