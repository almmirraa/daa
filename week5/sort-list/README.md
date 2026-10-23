# Sort List (LeetCode 148)

## 1. Problem
Given the `head` of a singly linked list, sort the list in ascending order and return its head. The solution must achieve $O(n \log n)$ time complexity and ideally minimal extra space.

## 2. Approach
Merge Sort is naturally suited for linked lists because merging two sorted linked lists requires only rewiring pointers without creating extra buffers.

1. **Base Case:** If `head == null` or `head.next == null`, the list is already sorted, so return `head`.
2. **Find Middle & Split:** Use the two-pointer technique (`slow` and `fast`) with a `prev` pointer to locate the midpoint. Set `prev.next = null` to disconnect the first half from the second half (`slow`).
3. **Conquer:** Recursively sort both sublists (`sortList(head)` and `sortList(slow)`).
4. **Merge:** Combine the two sorted linked lists using a standard `dummy` node pattern (same logic as LeetCode 21: *Merge Two Sorted Lists*).

### Challenges and What Did Not Work
- **Infinite Recursion / Stack Overflow:** My first implementation failed to sever the link between the two halves (`prev.next = null`). Without explicitly setting `prev.next = null`, the left sublist never terminated and kept pointing into the right sublist, causing an infinite recursion loop and a `StackOverflowError`.
- **Uneven Halves:** If the fast pointer isn't advanced carefully, `slow` can land on the end node for 2-element lists, leading to an endless split of `[head]` and empty list. Keeping `prev` to break the connection right before `slow` resolved this cleanly.

### Tracing with Example
- Input: `head = [4, 2, 1, 3]`

1. Find midpoint: `slow` reaches node `1`, `prev` is at node `2`.
2. Split by `prev.next = null`:
   - Left sublist: `4 -> 2 -> null`
   - Right sublist: `1 -> 3 -> null`
3. Recursively split left half: `4` and `2` $\implies$ merged into `2 -> 4`.
4. Recursively split right half: `1` and `3` $\implies$ merged into `1 -> 3`.
5. Final merge of `2 -> 4` and `1 -> 3`:

| Step | `l1` node | `l2` node | Compare | Attach to `curr.next` | Merged Chain (`dummy.next`) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Start | 2 | 1 | `2 > 1` | Node(1) | `1` |
| 1 | 2 | 3 | `2 <= 3` | Node(2) | `1 -> 2` |
| 2 | 4 | 3 | `4 > 3` | Node(3) | `1 -> 2 -> 3` |
| End | 4 | `null` | `l2 == null` | Node(4) directly | `1 -> 2 -> 3 -> 4` |

Output: `[1, 2, 3, 4]`.

## 3. Time Complexity
**Time Complexity: $\Theta(n \log n)$**
- Finding the middle takes $O(n)$ time using fast and slow pointers.
- Splitting the list divides the problem size by 2 at each stage ($\log n$ levels of recursion).
- Merging two halves takes linear time $O(n)$.
- According to the Master Theorem ($T(n) = 2T(n/2) + O(n)$), the total time complexity is $\Theta(n \log n)$ across all cases.

## 4. Space Complexity
**Space Complexity: $O(\log n)$**
- Unlike array Merge Sort which requires an auxiliary array of size $O(n)$, linked lists merge nodes in-place by adjusting references ($O(1)$ space for merge).
- The only extra space consumed is the call stack from recursive calls, which reaches a maximum depth of $O(\log n)$.

## 5. Reflection / Improvement
This top-down recursive merge sort runs in optimal $O(n \log n)$ time with $O(\log n)$ stack space. To achieve true $O(1)$ auxiliary space, an iterative bottom-up Merge Sort can be implemented by merging sublists of sizes 1, 2, 4, 8, etc. using loops. While that cuts stack space to $O(1)$, the top-down approach is significantly more readable and avoids pointer-management edge cases.
