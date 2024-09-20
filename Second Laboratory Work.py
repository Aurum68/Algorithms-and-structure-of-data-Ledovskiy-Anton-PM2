def mathematics(a: str, b: str, operator: str):
    if operator == '+':
        return str(int(a) + int(b))
    if operator == '-':
        return str(int(a) - int(b))
    if operator == '*':
        return str(int(a) * int(b))
    if operator == '/' and b != '0':
        return str(int(int(a) / int(b)))
    elif b == '0':
        quit("Невозможно посчитать - на ноль делить нельзя")

    
operators_butMinus = '+*/'
operators = '+-*/'
a = list(input("Введите арифметическое выражение (в конце =) "))

if a[-1] != '=':
    quit("Невозможно посчитать - нет =")
if a[0] in operators_butMinus or a[-2] in operators:
    quit("Невозможно посчитать - неверно расставлены операторы")
i = 0
while i != len(a)-1:
    if a[i] in operators and a[i+1] in operators:
        quit("Невозможно посчитать - неверно расставлены операторы")
    if a[i] == '(' and a[i+1] in operators_butMinus or a[i+1] == ')' and a[i] in operators:
        quit("Невозможно посчитать - неверно расставлены операторы")
    else:
        i+=1

if a[0] == '-':
    a.insert(0, '0')

i = 0
while i != len(a) - 1:
    if a[i] == '(' and a[i+1] == '-':
        a.insert(i+1, '0')
    else:
        i+=1

i = 0
while i != len(a) - 1:
    try:
        p = int(a[i])
        k = int(a[i+1])
        a[i] = a[i] + a[i+1]
        a.pop(i+1)
    except:
        i += 1

scob = [i for i in a if i in '()']
open_scob_count = scob.count('(')
close_scob_count = scob.count(')')

if  open_scob_count != close_scob_count:
    quit("Невозможно посчитать - неверно расставлены скобки")

i = 0
while len(scob) != 0:
    if (scob[i] == '(' and scob[i + 1] == ')'):
        scob.pop(i)
        scob.pop(i)
        i=0
    else:
        i+=1
    if i == len(scob) and len(scob) != 0:
        quit("Невозможно посчитать - неверно расставлены скобки")

hight_opers = '*/'
down_opers = '-+'

queue = []
oper_stack = []

i = 0
while i != len(a) - 1:
    if a[i] not in operators and a[i] not in '()':
        queue.append(a[i])
    elif a[i] in operators:
        if len(oper_stack) != 0:
            if oper_stack[-1] in hight_opers or (oper_stack[-1] in down_opers and a[i] in down_opers):
                queue.append(oper_stack[-1])
                oper_stack[-1] = a[i]
            else:
                oper_stack.append(a[i])
        else:
            oper_stack.append(a[i])
    elif a[i] in '()':
        if a[i] == ')':
            while oper_stack[-1] != '(':
                queue.append(oper_stack[-1])
                oper_stack.pop(-1)
            oper_stack.pop(-1)
        else:
            oper_stack.append(a[i])
    i += 1

for i in range(len(oper_stack) - 1, -1, -1):
    queue.append(oper_stack[i])

i = 0
while len(queue) != 1:
    if queue[i] in operators:
        queue[i] = mathematics(queue[i-2], queue[i-1], queue[i])
        queue.pop(i-1)
        queue.pop(i-2)
        i=0
    else:
        i+=1

print(queue[0])