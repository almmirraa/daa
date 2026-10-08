# Remove Linked List Elements (LeetCode 203)

## 1. Problem
Given the head of a linked list and an integer `val`, remove all nodes in the list that have `Node.val == val`, and return the updated head.

## 2. Approach
I used an iterative approach with a dummy head node.

- Because the node to remove could be the very first node (`head`), handling `head` separately can introduce edge cases. A `dummy` node pointing to `head` (`dummy.next = head`) standardizes removal logic for all positions.
- I use a pointer `current` starting at `dummy`.
- In a `while` loop, I check `current.next`:
  - If `current.next.val == val`, I unlink that node: `current.next = current.next.next`. I do not move `current` forward yet because the new `current.next` might also contain `val`.
  - If `current.next.val != val`, I safely move forward: `current = current.next`.
- Once the list is exhausted, `dummy.next` points to the new head.

### Challenges and What Did Not Work
Before introducing `dummy`, I tried writing a separate loop to remove matching values from the head (`while (head != null && head.val == val)`). This worked for basic cases, but repeatedly led to `NullPointerException` errors on empty lists (`[]`) or lists consisting entirely of the target value (e.g., `[7, 7, 7, 7]` with `val = 7`). Using a dummy node made the code significantly cleaner and eliminated special branching.

### Tracing with Example
- Input: `head = [1, 2, 6, 3, 4, 5, 6]`, `val = 6`
- Setup: `dummy = [0] -> 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6`, `current = dummy`

| Step | `current.val` | `current.next.val` | Action | Resulting list |
| :--- | :--- | :--- | :--- | :--- |
| 1 | 0 (dummy) | 1 | Not equal, advance | `0 -> 1 -> ...` |
| 2 | 1 | 2 | Not equal, advance | `... -> 1 -> 2 -> ...` |
| 3 | 2 | 6 | Equal, skip node | `2 -> 3` (bypassed 6) |
| 4 | 2 | 3 | Not equal, advance | `... -> 2 -> 3 -> ...` |
| 5 | 3 | 4 | Not equal, advance | `... -> 3 -> 4 -> ...` |
| 6 | 4 | 5 | Not equal, advance | `... -> 4 -> 5 -> ...` |
| 7 | 5 | 6 | Equal, skip node | `5 -> null` (bypassed 6) |
| 8 | 5 | `null` | End reached | Loop terminates |

Output: `dummy.next` -> `[1, 2, 3, 4, 5]`.

## 3. Time Complexity
**Time Complexity: O(n)**
Let `n` be the number of nodes in the linked list. We inspect each node's value exactly once as `current.next` advances through the list. Node re-linking takes $O(1)$ operations, yielding an overall linear time complexity: $O(n)$.

## 4. Space Complexity
**Space Complexity: O(1)**
The algorithm only uses one extra dummy node and a single traversal pointer. Pointers are adjusted directly in memory without supplementary collections or call stacks, giving constant memory $O(1)$.

## 5. Reflection / Improvement 
This is already the optimal iterative solution. Since this problem is tagged under recursion, it can also be solved recursively:
```java
if (head == null) return null;
head.next = removeElements(head.next, val);
return head.val == val ? head.next : head;

However, recursion consumes O(n) space on the call stack and can cause a StackOverflowError on very large lists. The iterative dummy-node approach remains preferred for production use due to its O(1) space guarantee.
