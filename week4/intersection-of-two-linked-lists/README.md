# Intersection of Two Linked Lists (LeetCode 160)

## 1. Problem
Given the heads of two singly linked lists `headA` and `headB`, find and return the node where the two chains intersect. If they do not intersect, return `null`.

## 2. Approach
I implemented the two-pointer redirection technique.

- If either head is `null`, there cannot be an intersection, so I immediately return `null`.
- I place `ptrA` at `headA` and `ptrB` at `headB`.
- Both pointers advance forward one node at a time.
- When `ptrA` hits `null`, it restarts at `headB`.
- When `ptrB` hits `null`, it restarts at `headA`.
- By swapping heads upon hitting the end, both pointers travel the exact same total distance: `len(A) + len(B)`.
- If an intersection exists, they will collide at the intersection node on the second pass. If no intersection exists, both pointers will hit `null` simultaneously, terminating the loop.

### Challenges and What Did Not Work
A common early mistake is comparing node values (`ptrA.val == ptrB.val`). Linked lists can share identical integer values across unrelated nodes; intersection means referencing the exact same memory address (`ptrA == ptrB`). Another failed initial attempt was using nested loops, which ran into Time Limit Exceeded ($O(n \times m)$).

### Tracing with Example
- List A: `4 -> 1 -> [8 -> 4 -> 5]` (length 5)
- List B: `5 -> 6 -> 1 -> [8 -> 4 -> 5]` (length 6)

| Step | `ptrA` position | `ptrB` position | Description |
| :--- | :--- | :--- | :--- |
| Start | 4 | 5 | Initial heads |
| 1 | 1 | 6 | Moving forward |
| 2 | 8 | 1 | Moving forward |
| 3 | 4 | 8 | Moving forward |
| 4 | 5 | 4 | Moving forward |
| 5 | `null` | 5 | `ptrA` hits end $\to$ redirected to `headB` |
| 6 | 5 *(headB)* | `null` | `ptrB` hits end $\to$ redirected to `headA` |
| 7 | 6 | 4 *(headA)* | Both are now aligned |
| 8 | 1 | 1 | One step before intersection |
| 9 | **8** | **8** | **`ptrA == ptrB`! Intersection found.** |

Output: Node with value `8`.

## 3. Time Complexity
**Time Complexity: O(n + m)**
Let `n` be the length of list A and `m` be the length of list B. In the worst-case scenario (no intersection), both pointers traverse at most `n + m` steps before reaching `null` together. Each comparison and pointer jump runs in $O(1)$, keeping overall time linear: $O(n + m)$.

## 4. Space Complexity
**Space Complexity: O(1)**
The algorithm only keeps two references (`ptrA` and `ptrB`). It does not allocate arrays, sets, or recursive frames, requiring constant memory $O(1)$.

## 5. Reflection / Improvement
This two-pointer redirection algorithm is already the most efficient known solution. A brute-force lookup using a `HashSet` to store all nodes of list A would also take $O(n + m)$ time, but it would cost $O(n)$ extra memory. The two-pointer approach matches that runtime while preserving strict $O(1)$ space.
