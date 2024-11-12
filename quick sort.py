import random

def quick_sort(a: list[int], left: int, right: int):
    if left > right: return

    n = (a[left] + a[right])//2
    i = left
    j = right

    while i<= j:
        while a[i] < n: 
            i += 1
        while a[j] > n: 
            j -= 1
        if i <= j:
            a[i], a[j] = a[j], a[i]
            i +=1
            j -=1
    
    quick_sort(a, left, j)
    quick_sort(a, i, right)


a = [random.randint(1, 200) for i in range(random.randint(10, 100))]
print("Old: ", a)

quick_sort(a, 0, len(a) - 1)
print("New: ", a)