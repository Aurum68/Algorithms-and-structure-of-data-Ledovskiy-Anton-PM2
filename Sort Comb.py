import random

a = [random.randint(-100, 101) for i in range(random.randint(10, 100))]
print(a)

lenght = len(a)
factor = 1.247
gapFactor = lenght / factor

while gapFactor > 1:
    gap = round(gapFactor)

    for i in range(0, lenght - gap ):
        j = i + gap
        if a[i] > a[j]:
            a[i], a[j] = a[j], a[i]
    gapFactor = gapFactor / factor

print(a)
    
