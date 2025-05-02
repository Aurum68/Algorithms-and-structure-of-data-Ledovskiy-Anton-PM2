import random


def heapify(nums: list[int], i: int, length: int):
    i_left = 2 * i + 1
    i_right = 2*i + 2
    i_largest = i

    if i_left <= length and nums[i_left] > nums[i_largest]:
        i_largest = i_left
    if i_right <= length and nums[i_right] > nums[i_largest]:
        i_largest = i_right
    
    if i_largest == i:
        return
    else:
        nums[i], nums[i_largest] = nums[i_largest], nums[i]
        heapify(nums, i_largest, length)


def build_max_heap(nums: list[int]):
    i_middle = len(nums) // 2
    for i in range(i_middle, -1, -1):
        heapify(nums, i, len(nums) - 1)


def heap_sort(nums: list[int]):
    build_max_heap(nums)
    for i in range(len(nums)-1, -1, -1):
        nums[i], nums[0] = nums[0], nums[i]
        heapify(nums, 0, i-1)


a = [random.randint(1, 200) for i in range(random.randint(10, 100))]
print("Old: ", a)

heap_sort(a)
print("New: ", a)