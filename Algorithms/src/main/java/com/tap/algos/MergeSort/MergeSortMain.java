package com.tap.algos.MergeSort;

import com.tap.algos.Utils;

public class MergeSortMain {

    public static void main(String[] args) {
        int[] ints = Utils.generateArray(5);
        MergeSortMain.sort(ints);

        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] sort(int[] arr) {
        split(arr, 0, arr.length - 1);
        return arr;
    }

    private static void split(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int midIndex = (startIndex + endIndex) / 2;


        //Left side
        split(arr, startIndex, midIndex);

        //Right side
        split(arr, midIndex + 1, endIndex);

        merge(arr, startIndex, midIndex, endIndex);
    }

    private static void merge(int[] arr, int startIndex, int midIndex, int endIndex) {
        int[] tempArray = new int[endIndex - startIndex + 1];

        //Index counter for the left side of the array
        int leftSlot = startIndex;

        //Index counter for the right side of the array
        int rightSlot = midIndex + 1;

        //Current index of the temp array
        int k = 0;

        while (leftSlot <= midIndex && rightSlot <= endIndex) {
            if (arr[leftSlot] < arr[rightSlot]) {
                //If the element on the left side of the array is smaller
                //we insert it at the current temp array index and increment leftSlot index
                tempArray[k] = arr[leftSlot];
                leftSlot++;
            } else {
                //If the element on the right side of the array is smaller or equal to the
                //element of the left side of the array
                //we insert it at the current temp array index and increment rightSlot index
                tempArray[k] = arr[rightSlot];
                rightSlot++;
            }

            //Increment temp array index
            k++;
        }

        //Copy the elements that are left to the tempArray
        if (leftSlot <= midIndex) {
            //There are elements left on the left side of the array and we copy them
            //to the temArray
            while (leftSlot <= midIndex) {
                tempArray[k] = arr[leftSlot];
                leftSlot++;
                k++;
            }
        } else if (rightSlot <= endIndex) {
            //There are elements left on the right side of the array and we copy them
            //to the temArray
            while (rightSlot <= endIndex) {
                tempArray[k] = arr[rightSlot];
                rightSlot++;
                k++;
            }
        }

        //The tempArray is completely sorted and we copy its elements
        //to the correct slots in the inputArray
        for (int i = 0; i < tempArray.length; i++) {
            arr[startIndex + i] = tempArray[i];
        }
    }
}
