package com.gawdski;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Anna Gawda
 * @since 14.08.2017
 */
public class Conditionals {

    private Supplier<String> parameterSupplier;
    private boolean condition;
    private Consumer<String> consumer;
    private Supplier<Integer> thenReturnSupplier;

    static Consumer<String> doNothing() {
        return s -> {};
    }

    private Conditionals(Supplier<String> parameterSupplier, boolean condition, Consumer<String> consumer) {
        this.parameterSupplier = parameterSupplier;
        this.condition = condition;
        this.consumer = consumer;
    }

    private Conditionals(Supplier<String> parameterSupplier, boolean condition) {
        this.condition = condition;
        this.parameterSupplier = parameterSupplier;
    }

    private Conditionals(Supplier<String> parameterSupplier) {
        this.parameterSupplier = parameterSupplier;
    }

    static Conditionals given(String string) {
        return new Conditionals(() -> string);
    }

    static Conditionals given(Supplier<String> supplier) {
        return new Conditionals(supplier);
    }

    Conditionals when(boolean condition) {
        return new Conditionals(this.parameterSupplier, condition);
    }

    Conditionals when(Supplier<Boolean> supplier) {
        return new Conditionals(this.parameterSupplier, supplier.get());

    }

    Conditionals then(Consumer<String> consumer) {
        return new Conditionals(parameterSupplier, condition, consumer);
    }

    public ConditionalsSupplier thenReturn(Supplier<Integer> supplier) {
        return new ConditionalsSupplier(supplier, condition);
    }

    void orElse(Consumer<String> consumer) {
        if(condition) this.consumer.accept(parameterSupplier.get());
        else consumer.accept(parameterSupplier.get());
    }

    void orElseThrowE(Exception e) throws Exception {
        if(condition) this.consumer.accept(parameterSupplier.get());
        else throw e;
    }

    void orElseThrow(Supplier<Exception> exceptionSupplier) throws Exception {
        if(condition) this.consumer.accept(parameterSupplier.get());
        else throw exceptionSupplier.get();
    }
}
