package com.tap.algos.BubbleSort;

import com.tap.algos.Utils;

public class BubbleSortMain {

    public static void main(String[] args) {
        int[] ints = Utils.generateArray(10);
        BubbleSortMain.sort(ints);

        for (int anInt : ints) {
            System.out.println(anInt);
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

}
