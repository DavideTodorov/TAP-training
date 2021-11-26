# Quicksort

## Table of contents

* [Overview](#overview)
* [Algorithm explanation](#algorithm-explanation)
* [Partition method](#partition-method-explanation)

### Overview

> Complex sorting algorithm with O(n * LOGn) complexity.

### Algorithm explanation

> Quicksort is a complex algorithm which uses recursion to sort the elements.
> One of the main steps is partitioning the array in several smaller areas,
> whose data is then sorted. The main functionality is contained in the
> [Partition](#partition-method-explanation) method.

### Partition method explanation

##### Method parameters: int[] arr, int firstIndex, int lastIndex

> The partition method selects the last element as pivot point. And declares
> a variable which points to the index before the first element of the subset
> (called 'indexBeforeStart'). After that it starts iterating from the
> beginning until the end. We retrieve
> value on each iteration and if the current value is smaller than the pivot
> value, we swap the places of the current value with the element at index
> 'indexBeforeStart'. After exiting the for loop we swap the places of the element
> at index 'indexBeforeStart + 1' and the element at the last index. This will
> make sure that the current subset from the array is sorted.
> 'indexBeforeStart + 1' is returned as partition index and will be used for
> further partitioning of the array. It will serve as both the
> firstIndex('partition index + 1') and lastIndex('partition index - 1').
> This index will no longer be put in the iterations as it is considered
> to be sorted.This process is invoked recursively until the 'firstIndex'
> becomes more than or equal to the 'lastIndex'.