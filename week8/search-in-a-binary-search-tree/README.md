# 1. Problem
We are given the root of a Binary Search Tree (BST) and an integer `val`. We need to find the node whose value equals `val` and return the subtree rooted at that node. If no such node exists in the tree, we return `null`.

# 2. Approach
Instead of checking every node in the tree like in a normal binary tree, I used the main property of a Binary Search Tree: all values in the left subtree are smaller than the current node, and all values in the right subtree are larger.

My recursive solution works like this:
1. **Base Case 1:** If the current node `root` is `null`, we reached the end of a branch without finding the number, so return `null`.
2. **Base Case 2:** If `root.val == val`, we found the target node, so return `root`.
3. **Recursive Step:** If `val < root.val`, search only in the left child (`searchBST(root.left, val)`). Otherwise, search only in the right child (`searchBST(root.right, val)`).

**Written Tracing with Example:**
Suppose the BST is `[4, 2, 7, 1, 3]` and `val = 2`:
- **Step 1 (Root = 4):** Compare `val (2)` with `4`. Since `2 < 4`, we ignore the right subtree (`7`) and move left to node `2`.
- **Step 2 (Node = 2):** Compare `val (2)` with `2`. They match (`2 == 2`), so we return node `2` (along with its children `1` and `3`).

**Challenges Faced:**
At first, I considered traversing both `root.left` and `root.right` just like in standard binary tree problems. However, I realized that would waste time checking half of the tree unnecessarily. Another small mistake I had to watch out for was forgetting the `root == null` base case first, which would cause a `NullPointerException` when trying to access `root.val`.

# 3. Time & Space Complexity
- **Time Complexity:** `O(h)`, where `h` is the height of the tree. At each step, we go down one level and eliminate half of the remaining tree. In a balanced BST, this is `O(log n)`. In the worst case (a completely skewed tree that looks like a linked list), `h = n`, so the time complexity becomes `O(n)`.
- **Space Complexity:** `O(h)` due to the recursive call stack. Each recursive call adds a frame to the stack until the node or `null` is reached (`O(log n)` for a balanced tree, `O(n)` in the worst case).

# 4. Reflection / Improvement
- **Is there a more efficient approach?** In terms of time complexity, `O(h)` is already optimal because we must follow a path from the root down to the target node. However, we can improve the **space complexity**.
- **What would you need to change?** Instead of recursion, we can use a `while (root != null)` loop to move `root = root.left` or `root = root.right` iteratively.
- **What complexity could the improved solution achieve?** An iterative solution keeps the same `O(h)` time complexity while reducing auxiliary space complexity from `O(h)` down to `O(1)`.
