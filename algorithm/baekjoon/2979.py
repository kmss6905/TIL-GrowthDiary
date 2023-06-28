# 카운팅 배열 사용
# 시각은 이상과 미만 이다. (도착한 시간, 떠난 시간 등등) -> 우리가 알고자 하는 건 "시간" 이다
def sol():
    nums = input().split(" ")
    a = int(nums[0])
    b = int(nums[1])
    c = int(nums[2])
    map = {}
    for i in range(100):  # O(1)
        map[i] = 0

    for _ in range(3):
        times = input().split(" ")
        for i in range(int(times[0]), int(times[1])):
            map[i] += 1

    sum = 0
    for v in map.values():
        if v == 1:
            sum += a
        if v == 2:
            sum += b * 2
        if v == 3:
            sum += c * 3
    print(sum)

if __name__ == '__main__':
    sol()
