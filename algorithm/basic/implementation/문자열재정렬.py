a = input()
alpha = []
digitsum = 0
# O(N)
for i in a:
    if 65<=ord(i)<=90:
        alpha.append(i)
    else:
        digitsum += int(i)

# O(NlogN)
alpha.sort() 
alpha.append(str(digitsum))
print(''.join(alpha))