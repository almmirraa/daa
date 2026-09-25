# First Bad Version (LeetCode 278)

## 1. Problem
I have `n` versions of a product, numbered from `1` to `n`. One of the versions is bad, and all versions after it are also bad. I need to find the first bad version. I am given an API `isBadVersion(version)` that returns whether a version is bad.

## 2. Approach
My solution uses binary search to find the first bad version. I use two pointers, `left = 1` and `right = n`. In each step, I calculate the middle version `mid`. 

If `isBadVersion(mid)` returns `true`, then `mid` is bad, and the first bad version must be in the left half (including `mid`). So I set `right = mid`.

If it returns `false`, then `mid` is good, and the first bad version must be to the right, so I set `left = mid + 1`.

The loop continues while `left < right`. When they meet, `left` points to the first bad version.

## 3. Time Complexity
**Time Complexity: O(log n)**

At every step, the search range is cut in half. The number of calls to `isBadVersion` grows logarithmically with `n`. Therefore, the time complexity is O(log n).

## 4. Space Complexity
**Space Complexity: O(1)**

I only use a few variables (`left`, `right`, `mid`), which do not depend on `n`. The additional memory used is constant, O(1).

## 5. Reflection / Improvement
This solution is already optimal in terms of API calls. O(log n) is the minimum number of checks needed for a monotonic range. There is no faster asymptotic approach for this problem.
