n = 4
a = ["사과", "딸기", "포도", "배"]

for i in range(1 << n): # 16
    ret = ""
    for j in range(n): # 4
        if i & (1 << j):
            ret += (a[j] + " ")
    print(ret)