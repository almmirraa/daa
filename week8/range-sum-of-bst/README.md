# 1. Problem
Given the root of a Binary Search Tree and two integers `low` and `high`, we need to calculate and return the sum of values of all nodes that fall within the inclusive range `[low, high]`.

# 2. Approach
My first instinct was to traverse the whole tree and check if `low <= root.val <= high` at every node. However, because this is a BST, we can prune (skip) entire branches that cannot possibly contain valid numbers:
1. **Base Case:** If `root == null`, return `0`.
2. **Prune Left:** If `root.val < low`, then `root` and everything in its left subtree are smaller than `low`. We only search `root.right`.
3. **Prune Right:** If `root.val > high`, then `root` and everything in its right subtree are larger than `high`. We only search `root.left`.
4. **In Range:** If `low <= root.val <= high`, we add `root.val` to the sum of valid nodes from both `root.left` and `root.right`.

**Written Tracing with Example:**
Suppose the BST is `[10, 5, 15, 3, 7, null, 18]`, `low = 7`, `high = 15`:
- **Node 10:** `10` is inside `[7, 15]`. Result = `10 + left(5) + right(15)`.
- **Left child (Node 5):** `5 < 7` (`root.val < low`), so we skip its left child (`3`) completely and only check its right child (`7`).
  - **Node 7:** `7` is inside `[7, 15]`. Both children are `null` (`0`). Returns `7`.
- **Right child (Node 15):** `15` is inside `[7, 15]`. Left is `null` (`0`), right child is `18`.
  - **Node 18:** `18 > 15` (`root.val > high`), so we skip its right side and check left (`null` -> `0`).
- **Final Sum:** `10 + 7 + 15 = 32`.

**Challenges Faced:**
Initially, I wrote a standard DFS that checked every single node without the `root.val < low` and `root.val > high` conditions. While that worked and passed tests, it didn't take advantage of the BST ordering property. Adding the pruning checks made the logic much cleaner and faster on large trees.

# 3. Time & Space Complexity
- **Time Complexity:** `O(n)` in the worst case, where `n` is the total number of nodes in the tree. If all node values fall inside `[low, high]`, we must visit every node once. On average, pruning skips branches outside the range, making it much faster.
- **Space Complexity:** `O(h)` for the recursion call stack, where `h` is the height of the tree (`O(log n)` for a balanced tree and `O(n)` for a skewed tree).

# 4. Reflection / Improvement
- **Is there a more efficient approach?** The worst-case time complexity cannot be better than `O(n)` because in the worst case every element in the tree belongs to the range and must be summed.
- **What would you need to change?** To avoid potential recursion stack overflow on very deep skewed trees, we could rewrite the algorithm iteratively using an explicit `Stack` or `Queue` while keeping the exact same pruning conditions.
- **What complexity could the improved solution achieve?** It would still have `O(n)` worst-case time complexity and `O(h)` (or `O(w)` for BFS) auxiliary memory, without relying on system call stack depth.
