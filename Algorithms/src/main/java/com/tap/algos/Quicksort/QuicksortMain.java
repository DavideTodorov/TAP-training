package com.tap.algos.Quicksort;

import com.tap.algos.Utils;

public class QuicksortMain {

    public static void main(String[] args) {
        int[] ints = Utils.generateArray(5);
        QuicksortMain.sort(ints);

        for (int anInt : ints) {
            System.out.println(anInt);
        }

        String[] arr = Utils.generateStringArray(5);
        QuicksortMain.sort(arr);

        for (String s : arr) {
            System.out.println(s);
        }
    }

    //For int[]
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

    //For T[]
    public static <T extends Comparable<T>> T[] sort(T[] arr) {

        quickSort(arr, 0, arr.length - 1);

        return arr;
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr, int startIndex, int endIndex) {

        if (startIndex < endIndex) {
            int partitionIndex = partition(arr, startIndex, endIndex);

            quickSort(arr, startIndex, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, endIndex);
        }

    }

    private static <T extends Comparable<T>> int partition(T[] arr, int startIndex, int endIndex) {
        T pivotElement = arr[endIndex];
        int indexBeforeStart = startIndex - 1;

        for (int i = startIndex; i < endIndex; i++) {
            if (arr[i].compareTo(pivotElement) < 0) {
                indexBeforeStart += 1;

                T temp = arr[i];
                arr[i] = arr[indexBeforeStart];
                arr[indexBeforeStart] = temp;
            }

        }

        T temp = arr[indexBeforeStart + 1];
        arr[indexBeforeStart + 1] = arr[endIndex];
        arr[endIndex] = temp;

        return indexBeforeStart + 1;
    }
}
