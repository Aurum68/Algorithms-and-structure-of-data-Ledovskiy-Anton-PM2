import random

a = [random.randint(-100, 101) for i in range(random.randint(10, 20))]
print("Old: ", a)

ln = len(a)
k = 1
while (3**k - 1)/2 < (ln/3):
    k += 1
k-=1
step = (3**k -1)//2

while step >= 1:
    for i in range(step, len(a)):
        t = a[i]
        j = i - step
        while j >= 0 and t < a[j]:
            a[j+step] = a[j]
            j -= step
        a[j+step] = t
    k-=1
    step = (3**k-1)//2
print("New: ", a)