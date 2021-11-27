# Merge Sort

## Table of contents

* [Overview](#overview)
* [Algorithm explanation](#algorithm-explanation)
* [Splitting](#split-method)
* [Merging](#merge-method)

### Overview

> Merge sort is a complex sorting algorithm with O(n * logN).

### Algorithm explanation

> Merge sort is a recursive sorting algorithm that is composed of two main
> parts: [Splitting](#split-method) and [Merging](#merge-method).

### Split method

##### Method parameters: int[] arr, int startIndex, int endIndex

> The algorithm starts with the splitting method. It will be invoked
> recursively until the 'startIndex' becomes greater than or equal to the
> 'endIndex'. This method finds the middle index 'midIndex'. This index is
> used to split the array in left and right halves. The left half is created
> by invoking the split method recursively with 'startIndex' and
> 'endIndex' which in the case of getting the left side is the 'midIndex'.
> We then split the 'arr' recursively by invoking the split method with
> arguments: 'startIndex = midIndex + 1' and 'endIndex'. This process will
> continue until the 'startIndex' becomes greater than or equal to the
> 'endIndex'.

### Merge Method

##### Method parameters: int[] arr, int startIndex, int midIndex, int endIndex

> When we enter the merge method we have arr split into single elements.
> First we create 'tempArray' which we will use to store the sorted elements.
> We create three variables 'leftSlot = startIndex', 'rightSlot = midIndex + 1'
> and 'k = 0' (k is the currentIndex of tempArray). We start looping over
> the 'arr' elements with while loop. 
> It will continue until 'leftSlot <= midIndex && rightSlot <= endIndex'
> is not satisfied anymore. 
> The sorting logic is that we will insert the bigger element of 'arr[leftSlot]'
> and 'arr[rightSlot]' at index 'k' of the 'tempArray' and increment 'k'.
> After we have finished looping we check if all elements to the left of the
> 'midIndex' are placed in the 'tempArray' and if they are not we place them in it.
> We do the same check for the right side of the 'midIndex'. At this point sorting
> is finished and we copy the elements from 'tempArray' to 'arr'.