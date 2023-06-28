# 투포인터와 비슷하다.
# leetcode - sum 문제 아이디어
def sol(s):
    i = 0
    j = len(s) - 1
    result = 1
    while i <= j:
        if s[i] != s[j]:
            result = 0
            break
        i += 1
        j -= 1
    return result


if __name__ == '__main__':
    print(sol(input()))