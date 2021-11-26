package com.tap.algos.Quicksort;

import com.tap.algos.Utils;

public class QuicksortMain {

    public static void main(String[] args) {
        int[] ints = Utils.generateArray(5);
        QuicksortMain.sort(ints);

        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] sort(int[] arr) {

        quickSort(arr, 0, arr.length - 1);

        return arr;
    }

    private static void quickSort(int[] arr, int startIndex, int endIndex) {

        if (startIndex < endIndex) {
            int partitionIndex = partition(arr, startIndex, endIndex);

            quickSort(arr, startIndex, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, endIndex);
        }

    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        int pivotElement = arr[endIndex];
        int indexBeforeStart = startIndex - 1;

        for (int i = startIndex; i < endIndex; i++) {
            if (arr[i] < pivotElement) {
                indexBeforeStart += 1;

                int temp = arr[i];
                arr[i] = arr[indexBeforeStart];
                arr[indexBeforeStart] = temp;
            }

        }

        int temp = arr[indexBeforeStart + 1];
        arr[indexBeforeStart + 1] = arr[endIndex];
        arr[endIndex] = temp;

        return indexBeforeStart + 1;
    }
}
