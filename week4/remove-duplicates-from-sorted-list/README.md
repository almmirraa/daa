# Remove Duplicates from Sorted List (LeetCode 83)

## 1. Problem
Given the head of a sorted singly linked list, I need to remove all duplicate numbers so that each distinct element appears only once. The order of elements must stay sorted, and I need to return the modified list head.

## 2. Approach
Because the input list is already sorted, duplicate numbers are always placed right next to each other. 

- I use a pointer `current` initialized to `head`.
- I iterate through the list using a `while` loop as long as `current` and `current.next` are not `null`.
- In each step, I compare `current.val` with `current.next.val`:
  - If they are equal, `current.next` is a duplicate. I skip it by pointing `current.next = current.next.next`. Crucially, I keep `current` at the same node for another check because the new neighbor could also be identical.
  - If they are distinct, I move forward: `current = current.next`.
- When the end of the list is reached, I return `head`.

### Challenges and What Did Not Work
My initial draft moved the pointer forward on every single step (`current = current.next`) regardless of whether a duplicate was removed. This failed test cases with multiple repeating values like `[1, 1, 1]`. Skipping one node and immediately advancing left the third duplicate intact. I fixed this by ensuring `current` only advances when `current.val != current.next.val`.

### Tracing with Example
- Input: `head = [1, 1, 2, 3, 3]`

| Step | `current.val` | `current.next.val` | Condition | Action | Resulting list |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Start | 1 | 1 | `1 == 1` | Bypass duplicate (`next.next`) | `1 -> 2 -> 3 -> 3` |
| 1 | 1 | 2 | `1 != 2` | Move `current` | `1 -> 2 -> 3 -> 3` |
| 2 | 2 | 3 | `2 != 3` | Move `current` | `1 -> 2 -> 3 -> 3` |
| 3 | 3 | 3 | `3 == 3` | Bypass duplicate (`next.next`) | `1 -> 2 -> 3 -> null` |
| 4 | 3 | `null` | `current.next == null` | Loop ends | `1 -> 2 -> 3` |

Output: `[1, 2, 3]`.

## 3. Time Complexity
**Time Complexity: O(n)**
Let `n` be the number of nodes in the list. The loop traverses the linked list from left to right. Each pointer inspection and assignment takes constant time $O(1)$. Since each node is visited at most once, the time scales linearly with list length: $O(n)$.

## 4. Space Complexity
**Space Complexity: O(1)**
I modify node connections in-place without creating new nodes or helper data structures. Only one pointer variable (`current`) is stored, resulting in constant auxiliary space: $O(1)$.

## 5. Reflection / Improvement
This iterative in-place approach is already optimal in both time and space. A recursive version is also possible (`head.next = deleteDuplicates(head.next)`), but recursion introduces an $O(n)$ call stack overhead. The iterative method remains superior because it strictly preserves $O(1)$ memory.
