import random

a = [random.randint(-100, 101) for i in range(random.randint(10, 100))]
print("Old: ", a)

for i in range(len(a) - 1):
    min_index = i
    for j in range(i + 1, len(a)):
        if a[j] < a[min_index]:
            a[j], a[min_index] = a[min_index], a[j]

print("New: ", a)