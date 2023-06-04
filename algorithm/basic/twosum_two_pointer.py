# 문제의 제약 조건에 따라 러닝시간 초과할 수 있다.
# 따라서 더 효율적인 알고리즘을 생각해보자

# (중요) 리스트를 정렬 해볼까? -> 새로운 접근 방법? -> 정렬의 시간복잡도는 nlogn (n2 보다 적게 걸리네 가치는 있다) <- 아이디어는 정렬
# 생각의 확장

def twoSum(nums, target):
    nums.sort()
    l, r = 0, len(nums) - 1
    while l < r:
        if nums[l] + nums[r] > target:
            r -= 1
        elif nums[l] + nums[r] < target:
            l += 1
        else:
            return True
    return False


# nums = 4, 1, 9, 7, 5, 3, 16
print(twoSum(nums=[4, 1, 9, 7, 5, 3, 16], target=14))
