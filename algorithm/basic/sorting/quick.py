# 암기하기
array = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]
def quick_sort(array,start,end):
    if start >= end:
        return
    pivot = start
    left = start + 1 # 왼쪽 인덱스
    right = end # 오른쪽 인덱스
    while(left <= right):
        # 피벗보다 큰 데이터를 찾을 때 까지 반복
        while(left <= end and array[left] <= array[pivot]): # 왼쪽에서 오른쪽으로 이동 (left += 1)
            left += 1
        # 피벗보다 작은 데이터를 찾을 때까지 반복
        while(right > start and array[right] >= array[pivot]): # 오른쪽에서 왼쪽으로 이동 (right -= 1)
            right -= 1
        if (left > right): # 엇갈렸다면 작은 데이터와 피벗을 교체
            array[right], array[pivot] = array[pivot], array[right]
        else: # 엇갈리지 않았다면 작은 데이터와 큰 데이터를 교체 
            array[left], array[right] = array[right], array[left]
    quick_sort(array, start, right - 1)
    quick_sort(array, right + 1, end)

quick_sort(array, 0, len(array) - 1)
print(array)


