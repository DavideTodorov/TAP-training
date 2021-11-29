package com.tap.algos.SelectionSort;

import com.tap.algos.Utils;

public class SelectionSortMain {
    public static void main(String[] args) {
        int[] ints = Utils.generateArray(5);
        SelectionSortMain.sort(ints);

        for (int anInt : ints) {
            System.out.println(anInt);
        }

        String[] arr = Utils.generateStringArray(5);
        SelectionSortMain.sort(arr);

        for (String s : arr) {
            System.out.println(s);
        }
    }

    public static int[] sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int currMinimum = arr[i];
            int currMinimumIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (currMinimum > arr[j]) {
                    currMinimum = arr[j];
                    currMinimumIndex = j;
                }
            }

            arr[currMinimumIndex] = arr[i];
            arr[i] = currMinimum;
        }


        return arr;
    }

    public static <T extends Comparable<T>>  T[] sort(T[] arr) {

        for (int i = 0; i < arr.length; i++) {
            T currMinimum = arr[i];
            int currMinimumIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (currMinimum.compareTo(arr[j]) > 0) {
                    currMinimum = arr[j];
                    currMinimumIndex = j;
                }
            }

            arr[currMinimumIndex] = arr[i];
            arr[i] = currMinimum;
        }

        return arr;
    }
}
