# twoSum 완전탐색
def twoSum(nums, target):
    n = len(nums)
    for i in range(n-1):
        for j in range(i+1, n-1):
            if nums[i] + nums[j] == target:
                return True
    return False


print(twoSum(nums=[4, 1, 9, 7, 5, 3, 16], target=14))