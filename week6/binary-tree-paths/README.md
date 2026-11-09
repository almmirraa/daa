# Binary Tree Paths (LeetCode 257)

## 1. Problem
Given the `root` of a binary tree, return all root-to-leaf paths in any order. A leaf is a node with no children.

## 2. Approach
I implemented a recursive Depth-First Search (DFS) path accumulator:

- Pass the current path prefix `String path` and a mutable `List<String> paths` down the tree.
- Append the current node's value to `path`.
- **Base Case (Leaf check):** If both `node.left == null` and `node.right == null`, we have reached a leaf. Add the accumulated path to `paths` and return.
- **Recursive Step:** If a left child exists, recurse with `path + "->"`. If a right child exists, recurse with `path + "->"`.

### Challenges and What Did Not Work
An initial attempt checked `if (node == null) paths.add(path);`. For a node with only one child, this caused the path to be saved prematurely when the null branch was encountered, even though the other branch continued deeper. A path is only complete when reaching a true leaf (`node.left == null && node.right == null`).

## 3. Time Complexity
Time Complexity: O(n)
Every node is visited once during the traversal. In the worst case (a balanced tree), there are roughly n/2 leaves, and string concatenation along paths of length logn takes O(logn) time. Thus, the overall time complexity is O(nlogn) to O(n2) for string copying in skewed trees, with O(n) node visits.
 
## 4. Space Complexity
Space Complexity: O(n)
The recursion call stack uses O(n) memory in the worst case (skewed tree) and O(logn) in a balanced tree. Additionally, the accumulated strings and return list require O(n) space to store the path representations.

## 5. Reflection / Improvement
Using immutable Java String concatenation (path + "->") creates new string instances at each recursion level. We could optimize performance by using a single mutable StringBuilder and backtracking (deleting the appended length upon returning). However, standard string concatenation makes state management simpler and prevents backtracking bugs.
