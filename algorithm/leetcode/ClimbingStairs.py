m = {}


class Solution(object):
  def climbStairs(self, n):
    if n == 1:
      return 1

    if n == 2:
      return 2

    if n not in map:
      m[n] = self.climbStairs(n - 1) + self.climbStairs(n - 2)
    return m[n]
