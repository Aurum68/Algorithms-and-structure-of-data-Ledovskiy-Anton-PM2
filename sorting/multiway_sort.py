import os
import sys
from timeit import default_timer
import tempfile
import heapq
import random


sys.set_int_max_str_digits(0)
start_time = default_timer()

def sort_block(input_file, block_size):
    """Сортирует блок данных и сохраняет его во временный файл."""
    with open(input_file, 'r') as f:
        numbers = []
        for line in f:
            numbers.append(int(line.strip()))
            if len(numbers) == block_size:
                numbers.sort()
                temp_file = tempfile.NamedTemporaryFile(delete=False)
                with open(temp_file.name, 'w') as temp_f:
                    for number in numbers:
                        temp_f.write(f"{number}\n")
                yield temp_file.name
                numbers.clear()
        
        # Сортируем и сохраняем последний неполный блок
        if numbers:
            numbers.sort()
            temp_file = tempfile.NamedTemporaryFile(delete=False)
            with open(temp_file.name, 'w') as temp_f:
                for number in numbers:
                    temp_f.write(f"{number}\n")
            yield temp_file.name

def external_multi_phase_sort(input_file, block_size):
    """Выполняет внешнюю многофазную сортировку."""
    sorted_temp_files = list(sort_block(input_file, block_size))
    
    # Слияние отсортированных временных файлов
    with open('test_files/sorted_output.txt', 'w') as output_file:
        # Инициализируем кучи для слияния
        min_heap = []
        files = [open(temp_file, 'r') for temp_file in sorted_temp_files]
        
        # Заполняем кучу начальными элементами
        for i, f in enumerate(files):
            line = f.readline()
            if line:
                heapq.heappush(min_heap, (int(line.strip()), i))

        while min_heap:
            smallest, file_index = heapq.heappop(min_heap)
            output_file.write(f"{smallest}\n")
            
            # Читаем следующий элемент из того же файла
            next_line = files[file_index].readline()
            if next_line:
                heapq.heappush(min_heap, (int(next_line.strip()), file_index))

    # Закрываем все временные файлы
    for f in files:
        f.close()
    for temp_file in sorted_temp_files:
        os.remove(temp_file)


num_count = 1000000
def write_random_to_file(filename: str):
    with open(f'{filename}', 'w') as file:
        for i in range(num_count):
            if i < 9999:
                file.write(str(random.randint(100, 100000)) + '\n')
            else:
                file.write(str(random.randint(100, 100000)))

# Пример использования:
source_filename = 'external_test.txt'
write_random_to_file(f'test_files/{source_filename}')

external_multi_phase_sort(f'test_files/{source_filename}', block_size=1000)


print(f'end of execution\ntime for {num_count} = {default_timer() - start_time}')