# HashTable 을 이용한 TwoSum
# 시간복잡도 O(N)
def sol(nums, target):
    map = {}
    for i in nums:
        map[i] = True
        if target - i in map:
            return True
    return False


if __name__ == '__main__':
    print(sol(nums=[4, 1, 9, 7, 5, 3, 16], target= 14))