package com.tap.algos;

import com.tap.algos.BubbleSort.BubbleSortMain;
import com.tap.algos.InsertionSort.InsertionSortMain;
import com.tap.algos.MergeSort.MergeSortMain;
import com.tap.algos.Quicksort.QuicksortMain;
import com.tap.algos.SelectionSort.SelectionSortMain;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class BenchmarkAlgos {
    @Benchmark
    @Warmup(iterations = 1)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 0, warmups = 1)
    public void benchMarkInsertion() {
        int[] ints = Utils.generateArray(10000);
    }

    @Benchmark
    @Warmup(iterations = 1)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 0, warmups = 1)
    public void benchMarkQS() {
        int[] ints = Utils.generateArray(10000);
        QuicksortMain.sort(ints);
    }

    @Benchmark
    @Warmup(iterations = 1)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 0, warmups = 1)
    public void benchMarkMS() {
        int[] ints = Utils.generateArray(10000);
        MergeSortMain.sort(ints);
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 3)
    @Warmup(iterations = 1)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 1, warmups = 1)
    public void benchMarkBS() {
        int[] ints = Utils.generateArray(10000);
        BubbleSortMain.sort(ints);
    }

    @Benchmark
    @Warmup(iterations = 1)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 0, warmups = 1)
    public void benchMarkIS() {
        int[] ints = Utils.generateArray(10000);
        InsertionSortMain.sort(ints);
    }

    @Benchmark
    @Warmup(iterations = 1)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 0, warmups = 1)
    public void benchMarkSS() {
        int[] ints = Utils.generateArray(10000);
        SelectionSortMain.sort(ints);
    }

}
