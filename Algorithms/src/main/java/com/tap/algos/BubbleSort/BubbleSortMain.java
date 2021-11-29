package com.tap.algos.BubbleSort;

import com.tap.algos.Utils;

public class BubbleSortMain {

    public static void main(String[] args) {
        int[] ints = Utils.generateArray(5);
        BubbleSortMain.sort(ints);

        for (int anInt : ints) {
            System.out.println(anInt);
        }

        String[] arr = Utils.generateStringArray(5);
        BubbleSortMain.sort(arr);

        for (String s : arr) {
            System.out.println(s);
        }

    }

    public static int[] sort(int[] arr){

        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }


    public static <T extends Comparable<T>> T[] sort(T[] arr){

        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i].compareTo(arr[j]) >0){
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }
}
