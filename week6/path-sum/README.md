# Path Sum (LeetCode 112)

## 1. Problem
Given the root of a binary tree and an integer `targetSum`, return `true` if the tree has a root-to-leaf path such that adding up all the values along the path equals `targetSum`. Return `false` otherwise.

## 2. Approach
I implemented a recursive backtracking-like subtraction approach:

- If `root == null`, return `false`.
- If the current node is a leaf (`root.left == null && root.right == null`), check if `root.val == targetSum`.
- Otherwise, subtract `root.val` from `targetSum` and recursively call `hasPathSum` on the left child or the right child using the logical OR operator (`||`).

### Challenges and What Did Not Work
An early mistake was checking `targetSum == 0` for empty trees or non-leaf nodes. A path must strictly end at a **leaf node** (a node with no children). Checking `targetSum == 0` when reaching `null` failed cases where a parent node had only one child, incorrectly counting an incomplete path. The check must be performed explicitly when both `left` and `right` are `null`.

### Tracing with Example
- Tree: `[5, 4, 8, 11, null, 13, 4, 7, 2]`, `targetSum = 22`
- Root: `5` to remaining = $22 - 5 = 17$
- Left child: `4` to remaining = $17 - 4 = 13$
- Left child: `11` to remaining = $13 - 11 = 2$
- Leaf check at node `2`: `root.val == 2` matches `remainingSum == 2`. Returns `true`.

The recursion short-circuits via `||` and propagates `true` back to the root.

## 3. Time Complexity
**Time Complexity: O(n)**
In the worst case, every node in the binary tree is visited once before determining whether a valid path exists. Thus, the time complexity is $O(n)$.

## 4. Space Complexity
**Space Complexity: O(n)**
The memory is bounded by the recursion stack depth. In the worst case of a skewed tree, it requires $O(n)$ space; in a balanced tree, it takes $O(\log n)$ space.

## 5. Reflection / Improvement
This recursive DFS solution is clean and optimal. It can also be solved iteratively using two stacks (one for nodes and one for remaining path sums), which eliminates the recursive call stack. However, the recursive method is compact, intuitive, and easy to trace. 
