# Palindrome Linked List (LeetCode 234)

## 1. Problem
Given the head of a singly linked list, determine if the values in the list form a palindrome (reading the exact same forward and backward). Return `true` if it is a palindrome, and `false` otherwise.

## 2. Approach
I implemented the optimal three-phase in-place approach:

1. **Find the middle:** I use fast and slow pointers (`slow` moves 1 node, `fast` moves 2 nodes). When `fast` reaches the end, `slow` is positioned right at the midpoint.
2. **Reverse second half:** Starting from `slow`, I reverse all pointer connections in place using an iterative three-pointer technique (`prev`, `curr`, `nextTemp`).
3. **Compare halves:** I set `firstHalf = head` and `secondHalf = prev` (the head of the reversed half). I walk both pointers simultaneously, comparing values. If all pairs match until `secondHalf` hits `null`, the list is a valid palindrome.

### Challenges and What Did Not Work
My first prototype converted the linked list into an `ArrayList` and used two pointers from both ends. While that easily passed, it used $O(n)$ auxiliary memory. When switching to the in-place method, I encountered a bug with odd-length lists (like `[1, 2, 1]`) because `slow` lands directly on the central element. Iterating comparisons strictly bounded by `secondHalf != null` resolved this cleanly without needing conditional branches for odd vs. even lengths.

### Tracing with Example
- Input: `head = [1, 2, 2, 1]`

**Phase 1: Finding Middle**
- `slow` lands at index 2 (the first `2`).

**Phase 2: Reversing Second Half**
- Sublist `[2 -> 1 -> null]` is reversed into `[1 -> 2 -> null]`.
- `prev` now points to the new head of this reversed half (value `1`).

**Phase 3: Comparison**

| Step | `firstHalf.val` | `secondHalf.val` | Check | Action |
| :--- | :--- | :--- | :--- | :--- |
| 1 | 1 | 1 | `1 == 1` (match) | Advance both |
| 2 | 2 | 2 | `2 == 2` (match) | Advance both |
| End | - | `null` | Loop finishes | Return `true` |

Output: `true`.

## 3. Time Complexity
**Time Complexity: O(n)**
Let `n` be the total number of nodes:
- Finding the middle takes $n / 2$ steps: $O(n)$.
- Reversing the second half takes $n / 2$ steps: $O(n)$.
- Comparing the two halves takes at most $n / 2$ steps: $O(n)$.
Adding these sequential stages gives $O(n) + O(n) + O(n) = O(n)$ linear time.

## 4. Space Complexity
**Space Complexity: O(1)**
The entire operation modifies existing pointers in-place. Only a handful of pointer references (`slow`, `fast`, `prev`, `curr`) are used, requiring strictly $O(1)$ constant memory.

## 5. Reflection / Improvement
This in-place method reaches the theoretical limit for singly linked lists: $O(n)$ time and $O(1)$ space. The simpler array-copy approach or recursive traversal both run in $O(n)$ time but sacrifice memory by requiring $O(n)$ auxiliary space. An optional finishing improvement for production environments would be re-reversing the second half before returning to restore the caller's original list structure.
