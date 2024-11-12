import random


def bitwise_comparison(n: str, m: str) -> str:
    for i in range(len(n)):
        if int(n[i]) < int(m[i]):
            return n
        if int(n[i]) > int(m[i]):
            return m
    return None


a = [str(random.randint(1, 200)) for i in range(random.randint(10, 100))]
print("Old: ", a)

for i in range(len(a)-1):
    for j in range(i+1, len(a)):
        if len(a[i]) > len(a[j]):
            a[i], a[j] = a[j], a[i]
        elif len(a[i]) == len(a[j]) and bitwise_comparison(a[i], a[j]) == a[j]:
            a[i], a[j] = a[j], a[i]
print("New: ", a)