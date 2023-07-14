# HashTable 을 이용한 TwoSum
# 시간복잡도 O(N)
def sol(nums, target):
    map = {}
    for i in nums:
        map[i] = True
        if target - i in map:
            return True
    return False

# HashTable 자료구조를 사용했을 때 문제가 되는 것은. [3, 3] 의 경우 3을 인덱스, 배열의 인덱스를 value로 설정할 경우
# 3 : 0 -> 3 : 1 로 덮어버려진다는 문제점.
class Solution(object):
    def twoSum(self, nums, target):
        m = {}
        for i, v in enumerate(nums):
            m[v] = i
        for i, v in enumerate(nums):
            t = target - v
            if t in m:
                if i != 0:
                    return [m[t], i]

    # O(N^2)
    def twoSumBruthForce(self, nums, target):
        for i, v in enumerate(nums):
            for j in range(i+1, len(nums)):
                if nums[i] + nums[j] == target:
                    return [i, j]

    # O(N)
    def hashTable(self, nums, target):
        table = {}
        for i in range(len(nums)):
            if nums[i] not in table:
                table[target-nums[i]] = i
            else:
                return [i, table[nums[i]]]

if __name__ == '__main__':
    # print(sol(nums=[4, 1, 9, 7, 5, 3, 16], target= 14))
    print(Solution().twoSumBruthForce(nums=[4, 1, 9, 7, 5, 3, 16], target= 14))