# Merge Two Sorted Lists (LeetCode 21)

## 1. Problem
I have two sorted linked lists, `list1` and `list2`. I need to combine them into one single sorted list using the existing nodes and return its head.

## 2. Approach
I solved this iteratively using two pointers and a dummy node.

First, I create a `dummy` node to easily track the start of the new list without extra checks. Then, I use a `current` pointer to build the chain.
Inside a `while` loop, I compare the current nodes of `list1` and `list2`:
- If `list1.val <= list2.val`, I connect `list1` to `current.next` and move `list1` forward.
- Otherwise, I connect `list2` to `current.next` and move `list2` forward.
- After each step, I advance `current`.

When one list becomes empty, I just attach whatever is left of the other list directly to `current.next`. Finally, I return `dummy.next`.

### Tracing with Example
- Input: `list1 = [1, 2, 4]`, `list2 = [1, 3, 4]`
- Initial setup: `dummy = [0]`, `current = dummy`

| Step | list1 | list2 | Compare | Action | Merged list |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Start | `1 -> 2 -> 4` | `1 -> 3 -> 4` | - | - | `0` |
| 1 | `1 -> 2 -> 4` | `1 -> 3 -> 4` | `1 <= 1` | Take from list1 | `0 -> 1` |
| 2 | `2 -> 4` | `1 -> 3 -> 4` | `2 > 1` | Take from list2 | `0 -> 1 -> 1` |
| 3 | `2 -> 4` | `3 -> 4` | `2 <= 3` | Take from list1 | `0 -> 1 -> 1 -> 2` |
| 4 | `4` | `3 -> 4` | `4 > 3` | Take from list2 | `0 -> 1 -> 1 -> 2 -> 3` |
| 5 | `4` | `4` | `4 <= 4` | Take from list1 | `0 -> 1 -> 1 -> 2 -> 3 -> 4` |
| End | `null` | `4` | `list1 == null` | Attach remaining list2 | `0 -> 1 -> 1 -> 2 -> 3 -> 4 -> 4` |

Output: `dummy.next` -> `[1, 1, 2, 3, 4, 4]`.

## 3. Time Complexity
**Time Complexity: O(n + m)**
Here, `n` is the number of nodes in `list1` and `m` is the number of nodes in `list2`. In each step, we look at and connect one node. In the worst case, we check all nodes from both lists. So the time complexity grows linearly with the total number of nodes, which is O(n + m).

## 4. Space Complexity
**Space Complexity: O(1)**
I only use a couple of pointer variables (`dummy` and `current`) and rewire the existing nodes. No new lists or recursive calls are made, so extra memory is constant, O(1).

## 5. Reflection / Improvement
This approach is already optimal. We can also solve this recursively, but recursion uses extra call stack space O(n + m). The iterative method is better because it keeps memory usage at O(1).
