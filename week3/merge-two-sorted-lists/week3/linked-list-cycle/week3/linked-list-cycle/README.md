# Linked List Cycle (LeetCode 141)

## 1. Problem
Given the head of a linked list, I need to check if there is a cycle in it. A cycle means that a node points back to a previous node and creates an infinite loop. I need to return `true` if a cycle exists, and `false` otherwise.

## 2. Approach
I used Floyd's cycle-finding method with two pointers (fast and slow).

- I set both `slow` and `fast` pointers at the start (`head`).
- In each step:
  - `slow` moves forward by 1 node (`slow = slow.next`).
  - `fast` moves forward by 2 nodes (`fast = fast.next.next`).
- If there is no cycle, `fast` will hit `null` and the loop ends (return `false`).
- If there is a cycle, `fast` will enter the loop and gradually catch up to `slow` from behind. When `slow == fast`, a cycle is found (return `true`).

### Tracing with Examples

#### Example 1: With Cycle
List: `3 -> 2 -> 0 -> -4`, and `-4` points back to `2`.

| Step | slow position | fast position | Are they equal? |
| :--- | :--- | :--- | :--- |
| Start | 3 | 3 | Initial |
| 1 | 2 | 0 | No |
| 2 | 0 | 2 (looped back) | No |
| 3 | -4 | -4 (caught up) | Yes, cycle found! |

Output: `true`.

#### Example 2: Without Cycle
List: `1 -> 2 -> null`

| Step | slow position | fast position | Check |
| :--- | :--- | :--- | :--- |
| Start | 1 | 1 | Initial |
| 1 | 2 | null | `fast.next == null`, stop |

Output: `false`.

## 3. Time Complexity
**Time Complexity: O(n)**
Let `n` be the total number of nodes. 
- If there is no cycle, `fast` reaches the end in at most `n / 2` steps.
- If there is a cycle, `slow` enters the cycle in at most `n` steps, and `fast` catches up to `slow` in less than one full loop.
Overall, the total operations stay linear, so the time complexity is O(n).

## 4. Space Complexity
**Space Complexity: O(1)**
I only use two pointer variables (`slow` and `fast`). No extra memory or collections (like a HashSet) are allocated, so space complexity is constant, O(1).

## 5. Reflection / Improvement
This is already the optimal solution. We could also solve this using a HashSet to store visited nodes, which is O(n) time, but that would require O(n) extra space. The two-pointer approach is better because it achieves the same time complexity with O(1) space.
