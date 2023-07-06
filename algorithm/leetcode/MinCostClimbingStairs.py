memo = {}


class Solution(object):

  def minCostClimbingStairs(self, cost):
    """
    :type cost: List[int]
    :rtype: int
    """
    return self.dfs(len(cost))


  def dfs(self, n):
    if n == 0 or n == 1:
      return 0
    if n not in memo:
      memo[n] = min(self.dfs(n - 1) + cost[n - 1], self.dfs(n - 2) + cost[n - 2])
    return memo[n]


if __name__ == '__main__':
    cost = [1,100,1,1,1,100,1,1,100,1]
    s = Solution()
    print(s.minCostClimbingStairs(cost))