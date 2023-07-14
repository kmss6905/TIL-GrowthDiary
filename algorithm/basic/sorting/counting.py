from itertools import count


array = [7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2]
count_array = [0]*10
for i in array:
    count_array[i] += 1

for i,v in enumerate(count_array):
    print(str(i)*v, end="")

    