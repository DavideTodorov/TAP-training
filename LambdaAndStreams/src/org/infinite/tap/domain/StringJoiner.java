package org.infinite.tap.domain;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringJoiner implements Collector<String, String, String> {

    @Override
    public Supplier<String> supplier() {
        return null;
    }

    @Override
    public BiConsumer<String, String> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<String> combiner() {
        return null;
    }

    @Override
    public Function<String, String> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
