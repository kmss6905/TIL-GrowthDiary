from json.tool import main

# 재풀이 필요 -> notion 기록
n = int(input())
cnt = 0
number = 0
while True:
    if 0 <= number < 10:
        number += 1
        cnt += 1
    else:
        number += 1
        l = len(str(number))
        for i in range(l): # 1 1
            if i+1 < l:
                if str(number)[i] > str(number)[i+1]:
                    cnt += 1
    if cnt == n:
        break
print(number)
