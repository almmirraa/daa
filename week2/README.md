# Binary Search (LeetCode 704)

## 1. Problem
Given an array of integers `nums` sorted in ascending order and an integer `target`, I need to find the index of the `target` in the array. If the target exists, I return its index; otherwise, I return `-1`.

## 2. Approach
My solution uses two pointers, `left` and `right`, to define the boundaries of the current search space. In each step, I calculate the middle index `mid`. 

If `nums[mid]` equals `target`, I return `mid`. If `nums[mid]` is less than `target`, the target must be in the right half, so I move `left` to `mid + 1`. Otherwise, it's in the left half, and I move `right` to `mid - 1`. 

I repeat this process until `left` is greater than `right`. If the loop ends without finding the target, I return `-1`.

## 3. Time Complexity
**Time Complexity: O(log n)**

At every step, the algorithm halves the search space. The number of steps needed to find the target (or determine it's missing) is proportional to the logarithm of the array size `n`. Therefore, the time complexity is O(log n).

## 4. Space Complexity
**Space Complexity: O(1)**

My solution only uses a few variables (`left`, `right`, `mid`), which do not depend on the size of the input array. So the additional memory usage is constant, O(1).

## 5. Reflection / Improvement
For a sorted array, there isn't a more efficient approach. O(log n) is the optimal time complexity. The only possible improvements would be minor constant factors (like using a bitwise shift instead of division), but the asymptotic complexity would remain the same.Более эффективного подхода для поиска в **отсортированном** массиве не существует: `O(log n)` — это оптимальная сложность. Улучшить можно только константные факторы (например, использовать битовый сдвиг вместо деления), но асимптотика останется той же.
