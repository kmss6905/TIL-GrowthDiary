"""
문제, 모험가 길드 : 문제 해결 아이디어
오름차순 정렬 이후 공포가 가장 낮은 모험가부터 하나씩 확인한다.
1. 앞에서부터 공포도를 하나씩 확인하며 '현재 그룹에 포함된 모험가의 수'가 '현재 확인하고 있는 공포도'
보다 크거나 같다면 이를 그룹으로 설정

2. 이러한 방법을 이용하며 공포도가 오름차순으로 정렬되어 있다는 점에서,
항상 최소한의 모험가의 수만 포함하여 그룹을 결성하게 됨


https://www.youtube.com/watch?v=2zjoKjt97vQ

"""

n = int(input())
nums = list(map(int, input().split()))
nums.sort()
# 1 2 2 2 3
gn = 0
group = []
for i in nums:
    if i == 1:
        gn += 1
    else:
        if i != len(group):
            group.append(i)
        else:
            group = []
            gn += 1
print(gn)