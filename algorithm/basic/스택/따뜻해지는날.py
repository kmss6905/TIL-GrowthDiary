# https://leetcode.com/problems/daily-temperatures/
# 사고과정
# 초반 완전탐색 생각 자연스럽다. 하지만 주어진 temperatures 의 길이에 따라 실행시간 증가, 문제에서는 극단적으로 n이 최대 10^5
# 따라서 자연스레 O(N), O(logN), O(NlogN) 과 같이 더 낮은 시간복잡도 생각 + 문제 특성 + 다른 자료구조와 알고리즘을 생각해볼까?
# (중요) 정렬도 시간 복잡도를 낮추는 데 좋은 아이디어! 하지만, 문제의 특성상 몇 번째에 있는 지가 중요 하기 때문에 정렬을 쓸 수 없다. <- 생각의 흐름
class Solution(object):
    def dailyTemperatures(self, temperatures):
        output = [0] * len(temperatures)
        stack = []
        for (i, tem) in enumerate(temperatures):# idea 1
            while len(stack) != 0 and stack[-1][1] < tem: # idea 2
                j, _ = stack.pop()
                print(j, _)
                output[j] += i-j
            stack.append((i, tem))
        return output

s = Solution()
print(s.dailyTemperatures([73,74,75,71,69,72,76,73]))