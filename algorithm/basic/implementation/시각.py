"""

이 문제는 가능한 모든 시각의 경우를 하나씩 모두 세서 풀 수 있는 문제
하루는 86400초이다 (24 * 60 * 60)
따라서 단순히 시각을 1씩 증가시키면서 3이 하나라도 포함되어 있는 지를 확인
"완전탐색 ( Brute Forcing )"

"""
h = int(input())
count = 0
for i in range(h+1):
    for j in range(60):
        for k in range(60):
            if '3' in str(i) + str(j) + str(k):
                count += 1
print(count)