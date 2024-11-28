import random

a = [random.randint(1, 1000000) for i in range(random.randint(10, 100))]
print("Old: ", a)

base = 10
max_digit = max([len(str(i)) for i in a])
bins = [[] for _ in range(base)]

for i in range(max_digit):
    for x in a:
        digit = (x // base ** i) % base
        bins[digit].append(x)
    a = [x for queue in bins for x in queue]
    bins = [[] for _ in range(base)]

print("New: ", a)