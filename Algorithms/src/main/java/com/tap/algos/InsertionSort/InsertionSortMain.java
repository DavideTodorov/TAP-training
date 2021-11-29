
package com.tap.algos.InsertionSort;

import com.tap.algos.Utils;

public class InsertionSortMain {

    public static void main(String[] args) {
        int[] ints = Utils.generateArray(5);
        InsertionSortMain.sort(ints);

        for (int anInt : ints) {
            System.out.println(anInt);
        }

        String[] arr = Utils.generateStringArray(5);
        InsertionSortMain.sort(arr);

        for (String s : arr) {
            System.out.println(s);
        }
    }

    public static int[] sort(int[] arr){

        for (int i = 0; i < arr.length; i++) {
            int currElement = arr[i];

            for (int j = i-1; j >= 0; j--) {
                if (currElement < arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        return arr;
    }

    public static <T extends Comparable<T>> T[] sort(T[] arr){

        for (int i = 0; i < arr.length; i++) {
            T currElement = arr[i];

            for (int j = i-1; j >= 0; j--) {
                if (currElement.compareTo(arr[j]) < 0){
                    T temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        return arr;
    }
}
