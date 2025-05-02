x = int(input('Введите число: '))

osnov = [3, 5, 7]
res = []

for i in range(1, x+1):
    j = i
    while j % osnov[0] == 0 or j % osnov[1] == 0 or j % osnov[2] == 0:
        if j % osnov[0] == 0:
            j //= osnov[0]
        if j % osnov[1] == 0:
            j //= osnov[1]
        if j % osnov[2] == 0:
            j //= osnov[2]
    if j == 1:
        res.append(str(i))

print(', '.join(res))