# Merge Two Binary Trees (LeetCode 617)

## 1. Problem
Given two binary trees `root1` and `root2`, imagine putting one over the other. Some nodes overlap while others do not. Merge the two trees into a new binary tree: if two nodes overlap, sum their node values; otherwise, use the existing non-null node.

## 2. Approach
I implemented a recursive Divide-and-Conquer preorder traversal:

- **Base Cases:**
  - If `root1 == null`, return `root2` (no merge needed, the rest of the branch comes from tree 2).
  - If `root2 == null`, return `root1` (the rest comes from tree 1).
- **Merge Current Nodes:** If both nodes exist, construct a new node with `val = root1.val + root2.val`.
- **Recurse:**
  - `merged.left = mergeTrees(root1.left, root2.left)`
  - `merged.right = mergeTrees(root1.right, root2.right)`
- Return the resulting `merged` node.

### Challenges and What Did Not Work
An early thought was to modify `root1` in-place (`root1.val += root2.val`) to avoid allocating new nodes. While faster, mutating input objects can lead to unintended side effects if the caller reuses the input trees. Constructing new nodes creates a safe, purely functional merge without corrupting existing tree references.

### Tracing with Example
- Tree 1: `[1, 3, 2, 5]`
- Tree 2: `[2, 1, 3, null, 4, null, 7]`

1. **Roots:** `1` and `2` overlap $\to$ new node value = $1 + 2 = 3$.
2. **Left branches:**
   - Nodes `3` and `1` overlap $\to$ new node value = $3 + 1 = 4$.
   - Left child of `3` is `5`, left child of `1` is `null` $\to$ returns Node(5).
   - Right child of `3` is `null`, right child of `1` is `4` $\to$ returns Node(4).
3. **Right branches:**
   - Nodes `2` and `3` overlap $\to$ new node value = $2 + 3 = 5$.
   - Left children are both `null` $\to$ returns `null`.
   - Right child of `2` is `null`, right child of `3` is `7` $\to$ returns Node(7).

Merged Output: `[3, 4, 5, 5, 4, null, 7]`.

## 3. Time Complexity
**Time Complexity: O(min(m, n))**
Let `m` and `n` be the number of nodes in `root1` and `root2` respectively. Recursion traverses only the overlapping nodes. As soon as one tree's branch is `null`, the function returns the other tree's subtree immediately in $O(1)$ without visiting its remaining descendants. Hence, the time complexity is $O(\min(m, n))$.

## 4. Space Complexity
**Space Complexity: O(min(m, n))**
The space consumed is determined by the depth of the recursion stack, which is bounded by the height of the smaller overlapping tree: $O(\min(m, n))$ in the worst case (skewed trees) and $O(\log(\min(m, n)))$ for balanced trees.

## 5. Reflection / Improvement
Instead of creating a brand new tree, we could reuse `root1` directly in-place:
```java
root1.val += root2.val;
root1.left = mergeTrees(root1.left, root2.left);
root1.right = mergeTrees(root1.right, root2.right);
return root1; 
