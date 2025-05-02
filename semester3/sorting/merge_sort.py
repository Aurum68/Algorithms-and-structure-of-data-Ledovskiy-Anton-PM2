import random


def merge_lists(a: list[int], b: list[int]) -> list[int]:
    c = []
    m = len(a)
    n = len(b)

    i=j=0
    while i < m and j< n:
        if a[i] < b[j]:
            c.append(a[i])
            i+=1
        else:
            c.append(b[j])
            j+=1
    c += a[i:] + b[j:]
    return c


def merge_sort(a: list[int]) -> list[int]:
    n = len(a) // 2
    a1 = a[:n]
    a2 = a[n:]

    if len(a1) > 1:
        a1 = merge_sort(a1)
    if len(a2) > 1:
        a2 = merge_sort(a2)
    
    return merge_lists(a1, a2)


a = [random.randint(1, 200) for i in range(random.randint(10, 100))]
print("Old: ", a)

print("New: ", merge_sort(a))

