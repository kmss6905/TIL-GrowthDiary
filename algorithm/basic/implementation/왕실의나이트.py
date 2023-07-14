# 시뮬레이션, 2차행렬, 완전탐색
cx, cy = list(input())
cx = ord(cx)-97
cy = int(cy) - 1
cnt = 0
directions = [
    (-2, 1),
    (-1, 2),
    (1, 2),
    (2, 1),
    (-2, -1),
    (-1, -2),
    (2, -2),
    (1, -1)
]
for (dy, dx) in directions:
    nx = cx + dx
    ny = cy + dy
    if 0 <= nx <= 7 and 0<= ny <= 7:
        cnt += 1
print(cnt)