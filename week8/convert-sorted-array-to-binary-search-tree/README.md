# 1. Problem
Given an integer array `nums` sorted in strictly ascending order, convert it into a height-balanced Binary Search Tree (where the depth of the two subtrees of every node never differs by more than 1).

# 2. Approach
If we simply inserted elements one by one from left to right into a BST, the tree would become completely skewed (like a linked list) and unbalanced. To keep the tree height-balanced, I used a **Divide-and-Conquer** approach similar to Binary Search:
1. Always pick the **middle element** of the current subarray as the root node. Because the array is sorted, this ensures roughly half of the remaining elements are smaller (go to the left) and half are larger (go to the right).
2. Recursively build the left subtree using the left half of the subarray (`left` to `mid - 1`).
3. Recursively build the right subtree using the right half of the subarray (`mid + 1` to `right`).
4. **Base Case:** When `left > right`, the subarray is empty, so return `null`.

**Written Tracing with Example:**
Suppose `nums = [-10, -3, 0, 5, 9]`:
- **Call 1 (`left = 0, right = 4`):** `mid = 2` -> Root is `nums[2] = 0`.
  - **Left Subtree (`left = 0, right = 1`):** `mid = 0` -> Node is `nums[0] = -10`.
    - Left (`0, -1`) -> `null`.
    - Right (`1, 1`) -> `mid = 1` -> Node is `nums[1] = -3`.
  - **Right Subtree (`left = 3, right = 4`):** `mid = 3` -> Node is `nums[3] = 5`.
    - Left (`3, 2`) -> `null`.
    - Right (`4, 4`) -> `mid = 4` -> Node is `nums[4] = 9`.
- **Resulting Tree:** Root `0`, left child `-10` (with right child `-3`), right child `5` (with right child `9`).

**Challenges Faced:**
At first, I thought about slicing/copying subarrays in each recursive step, which would waste memory and time. Using two index pointers (`left` and `right`) instead of copying arrays solved this issue. Also, I had to be careful with the base case condition (`left > right` instead of `left >= right`) because when `left == right`, there is still one valid element left to turn into a leaf node.

# 3. Time & Space Complexity
- **Time Complexity:** `O(n)`, where `n` is the length of the array. Every element in `nums` is visited once to create a corresponding `TreeNode` in `O(1)` time.
- **Space Complexity:** `O(log n)` for the recursion call stack (excluding the output tree itself). Because we always split the array in half, the resulting tree is guaranteed to be balanced with height `log n`.

# 4. Reflection / Improvement
- **Is there a more efficient approach?** No, `O(n)` time and `O(log n)` auxiliary stack space are already optimal because every single element in the input array must be processed and placed into a new node.
- **What would you need to change?** Nothing is needed for asymptotic improvement; passing indices `(left, right)` rather than array copies already avoids extra `O(n log n)` memory overhead.
- **What complexity could the improved solution achieve?** `O(n)` time and `O(log n)` auxiliary space is the theoretical lower bound for constructing a balanced BST from an array. 
