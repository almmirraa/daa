# Minimum Depth of Binary Tree (LeetCode 111)

## 1. Problem
Given a binary tree, find its minimum depth. The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node (a node with no children).

## 2. Approach
Unlike maximum depth, we must be careful with the definition of a leaf node:

- **Base Case:** If `root == null`, return `0`.
- **One child is null:** If a node has only one child (e.g., `root.left == null`), the current node is **not** a leaf node. We must continue searching down the non-null child (`minDepth(root.right) + 1`).
- **Both children exist:** If both children exist, take the minimum of both branches: `Math.min(minDepth(root.left), minDepth(root.right)) + 1`.

### Challenges and What Did Not Work
The most common pitfall was directly applying `Math.min(minDepth(root.left), minDepth(root.right)) + 1` unconditionally. In a skewed tree like `2 -> 3 -> null`, the left child is `null` (depth 0), which incorrectly caused `Math.min(0, depth(3))` to return 1 instead of 2. Handling null children explicitly resolved this edge case.

## 3. Time Complexity
Time Complexity: O(n)
In the worst case, the algorithm traverses all n nodes of the tree. Therefore, time complexity is linear: O(n).

## 4. Space Complexity
Space Complexity: O(n)
The space complexity depends on the recursion call stack, which is O(n) for a skewed tree and O(logn) for a balanced tree.

## 5. Reflection / Improvement
While DFS takes O(n) worst-case time, a Breadth-First Search (BFS) level-order traversal is often faster in practice. BFS visits nodes level by level and terminates immediately upon discovering the first leaf node, without having to explore the remaining branches of the tree. 
