import os
import random


def generate_random_word () -> str:
    abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя"
    word_len = random.randint(1, 20)
    word = str()
    for i in range(word_len):
        word += abc[random.randint(0, len(abc) - 1)]
    word += " "
    return word


def hash_func(string: str) -> int:
    english = "abcdefghijklmnopqrstuvwxyz"
    russian = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
    buf = string.lower()
    key = 0
    for i in range(len(buf)):
        if buf[i] in english:
            key += (english.index(buf[i]) + 1) * 58 ** i
        elif buf[i] in russian:
            key += (russian.index(buf[i]) + 1) * 58 ** i
    key = key % 2 ** 20
    return key


def add_to_hash_table (string: str):
    key = hash_func(string)
    hash_table[key].append(string)


if not os.path.isdir('files'):
    os.mkdir('files')
with open('files/input.txt', 'w') as input_file:
    text_len = random.randint(1000, 1000000)
    for i in range(text_len):
        input_file.write(generate_random_word())
    input_file.write('\n')
    input_file.close()

hash_table = [[] for i in range(2**20)]
with open('files/input.txt', 'r') as input_file:
    symb = input_file.read(1)
    while symb != '\n':
        string = ''
        while symb != " " and symb != '\n':
            if symb != " " and symb != '\n':
                string += symb
            symb = input_file.read(1)
        if string != "" and string != " ":
            add_to_hash_table(string)
        else:
            symb = input_file.read(1)
    input_file.close()

with open('files/output.txt', 'w') as output_file:
    for i in range(len(hash_table)):
        output_file.write(str(i) + ', '.join(hash_table[i]) + '\n')
    output_file.close()

print(max([len(i) for i in hash_table]))

