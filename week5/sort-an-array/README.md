# Sort an Array (LeetCode 912)

## 1. Problem
Given an integer array `nums`, sort the array in ascending order and return it. The solution must run in $O(n \log n)$ time complexity without using built-in sorting library functions.

## 2. Approach
I implemented the classic Merge Sort algorithm using the Divide-and-Conquer paradigm.

1. **Divide:** Recursively split the current subarray `[left, right]` in half by calculating the midpoint `mid = left + (right - left) / 2`.
2. **Conquer:** Recursively sort both halves (`mergeSort(left, mid)` and `mergeSort(mid + 1, right)`) until subarrays of length 1 are reached (base case).
3. **Combine (Merge):** Merge the two sorted halves back together into a temporary array using two pointers (`i` starting at `left`, `j` starting at `mid + 1`). Pick the smaller element at each step, flush any remaining elements, and copy the merged range back into the original array.

### Challenges and What Did Not Work
- **Time Limit Exceeded (TLE) with simple algorithms:** My first instinct was to test standard $O(n^2)$ sorting like Insertion Sort or Bubble Sort, but LeetCode provides input sizes up to $5 \times 10^4$, which immediately triggered Time Limit Exceeded.
- **Memory Limit / Allocation Overhead:** In an earlier merge sort draft, I created new smaller subarrays inside each `merge()` call (`new int[...]`). Allocating hundreds of small arrays caused high garbage collector overhead and Memory Limit Exceeded. I fixed this by allocating a single `temp` buffer of size $n$ once at the beginning and passing it through the recursive calls.

### Tracing with Example
- Input: `nums = [5, 2, 3, 1]`

1. Split `[5, 2, 3, 1]` into `[5, 2]` and `[3, 1]`.
2. Split `[5, 2]` into `[5]` and `[2]`.
   - Merge `[5]` and `[2]` $\to$ `[2, 5]`.
3. Split `[3, 1]` into `[3]` and `[1]`.
   - Merge `[3]` and `[1]` $\to$ `[1, 3]`.
4. Final merge step of `[2, 5]` and `[1, 3]`:

| Step | Left pointer (`i`) | Right pointer (`j`) | Comparison | Placed in `temp` | Current `temp` |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Start | `nums[0] = 2` | `nums[2] = 1` | `2 > 1` | `1` | `[1]` |
| 1 | `nums[0] = 2` | `nums[3] = 3` | `2 <= 3` | `2` | `[1, 2]` |
| 2 | `nums[1] = 5` | `nums[3] = 3` | `5 > 3` | `3` | `[1, 2, 3]` |
| End | `nums[1] = 5` | Right exhausted | Flush left | `5` | `[1, 2, 3, 5]` |

Output: `[1, 2, 3, 5]`.

## 3. Time Complexity
**Time Complexity: $\Theta(n \log n)$**
- The array is halved at each level of recursion, producing a tree depth of $\log_2 n$.
- At each level of the tree, merging all elements takes linear time $\Theta(n)$.
- Total work across all levels is $n \times \log_2 n = \Theta(n \log n)$. This holds for best, average, and worst cases because Merge Sort always divides and merges regardless of the initial order.

## 4. Space Complexity
**Space Complexity: $O(n)$**
- We use one auxiliary array `temp` of size $n$ to merge elements.
- The recursion stack takes $O(\log n)$ memory.
- Therefore, overall auxiliary space complexity is $O(n)$.

## 5. Reflection / Improvement
Merge Sort provides a guaranteed $O(n \log n)$ runtime, making it immune to worst-case pitfalls. To optimize space to $O(\log n)$ or $O(1)$, Heap Sort or QuickSort (with randomized pivot) could be used in-place. However, QuickSort can degrade to $O(n^2)$ if pivot selection is poor, whereas Merge Sort remains strictly stable and predictable.
