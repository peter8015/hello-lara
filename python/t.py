def sortColors(nums):
    low = 0
    mid = 0
    high = len(nums) - 1

    while mid <= high:
        if nums[mid] == 0:
            # 发现红色，换到左边
            nums[low], nums[mid] = nums[mid], nums[low]
            low += 1
            mid += 1
        elif nums[mid] == 1:
            # 发现白色，保持原位
            mid += 1
        else: # nums[mid] == 2
            # 发现蓝色，换到右边
            nums[mid], nums[high] = nums[high], nums[mid]
            high -= 1