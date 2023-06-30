"""
 a = 65, z = 90
 A = 97, Z = 122
"""
def sol():
    d = input()
    for a in d:
        if 65<=ord(a)<=90:
            if 90-ord(a) >= 13:
                print(chr(ord(a)+13), end="")
            elif 90-ord(a) < 13:
                b = 90-ord(a)
                c = 13 - b - 1
                print(chr(65+c), end="")
        elif 97<=ord(a)<=122:
            if 122-ord(a) >= 13:
                print(chr(ord(a)+13), end="")
            else:
                b = 122 - ord(a)
                c = 13 - b - 1
                print(chr(97 + c), end="")
        else:
            print(a, end="")


if __name__ == '__main__':
    sol()
