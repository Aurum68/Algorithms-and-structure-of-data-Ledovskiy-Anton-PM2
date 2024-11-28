import random

a = [random.randint(-100, 101) for i in range(random.randint(10, 100))]
print("Old: ", a)

for i in range(1, len(a)):
    t = a[i]
    j = i - 1
    while j >= 0 and t < a[j]:
        a[j+1] = a[j]
        j -= 1
    a[j+1] = t
print("New: ", a)