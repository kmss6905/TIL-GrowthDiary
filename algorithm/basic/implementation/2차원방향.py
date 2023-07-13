a = int(input())
directions = {
    "R" : (0, 1),
    "L" : (0, -1),
    "U" : (-1, 0),
    "D" : (1, 0)
}

n = list(input().split(" "))
cy, cx = (0, 0)
for i in n:
    dy, dx = directions[i] # if 문 x -> hashTable (memory)

    # 다음 좌표
    ny = cy + dy
    nx = cx + dx

    # 다음 좌표 확인
    if 0<= nx < a and 0<= ny < a:
        cy, cx = (ny, nx)

print(cy+1, cx+1)