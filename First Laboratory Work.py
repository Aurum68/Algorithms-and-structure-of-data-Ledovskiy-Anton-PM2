s = list(input("Введите строку "))
l = len(s)
#res = []


i = 0
while len(s) != 0:
    if (s[i] == '(' and s[i + 1] == ')') or \
        (s[i] == '[' and s[i + 1] == ']') or \
        (s[i] == '{' and s[i + 1] == '}'):
        s.pop(i)
        s.pop(i)
        # res.append()
        # res.append()
        i=0
    else:
        i+=1
    if i == len(s) and len(s) != 0:
        print("Строки не существует")
        break
if len(s) == 0:
    print("Строка существует")

