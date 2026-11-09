# Invert Binary Tree (LeetCode 226)

## 1. Problem
Given the `root` of a binary tree, invert the tree (create its mirror reflection) and return its root.

## 2. Approach
Inverting a binary tree requires swapping the left and right child pointers for every node in the tree from top to bottom.

- **Base Case:** If `root == null`, return `null`.
- **Recursive Step:**
  1. Temporarily store `root.left` in a variable `temp`.
  2. Assign `root.left = invertTree(root.right)`.
  3. Assign `root.right = invertTree(temp)`.
  4. Return `root`.
  
### Challenges and What Did Not Work
A critical bug occurred when swapping without saving the original left child:
```java
root.left = invertTree(root.right);
root.right = invertTree(root.left); // Bug: root.left has already been overwritten!
Because root.left was updated to the inverted right subtree first, calling invertTree(root.left) on the second line inverted the newly assigned right subtree again, duplicating it and losing the original left subtree entirely. 
Storing root.left in a temporary reference before the first assignment resolved the issue.

## 3. Time Complexity
Time Complexity: O(n)
Since each node in the tree is visited once to swap its children, the time complexity is proportional to the number of nodes n, yielding O(n).

## 4. Space Complexity
Space Complexity: O(n)
The space complexity depends on the recursive call stack. In the worst case (a completely skewed tree), the recursion depth is O(n). In a balanced tree with height logn, the space complexity is O(logn).

## 5. Reflection / Improvement
The problem can also be solved iteratively using Breadth-First Search (BFS) with a Queue. We enqueue the root, and while the queue is not empty, dequeue each node, swap its left and right pointers, and enqueue any non-null children. This avoids recursive call stack limits.
