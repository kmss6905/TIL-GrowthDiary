memo = {}
class Solution(object):
  def uniquePaths(self, m, n):
    """
    :type m: int
    :type n: int
    :rtype: int
    """
    if m == 1 or n == 1:
      return 1

    if (m, n) not in memo:
      memo[(m, n)] = self.uniquePaths(m-1, n) + self.uniquePaths(m, n-1)
    return memo[(m, n)]

if __name__ == '__main__':
    s = Solution()
    print(s.uniquePaths(3, 2))