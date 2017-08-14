package com.gawdski;


import java.util.function.Supplier;

/**
 * @author Anna Gawda
 * @since 14.08.2017
 */
public class FluentConditionals {

    private static boolean isTrue;
    public static Runnable doNothing = () -> {
    };

    public static FluentConditionals when(boolean condition) {
        isTrue = condition;
        return new FluentConditionals();
    }

    public static FluentConditionals when(Supplier<Boolean> supplier) {
        isTrue = supplier.get();
        return new FluentConditionals();
    }

    public FluentConditionalsRunnable then(Runnable function) {
        FluentConditionalsRunnable fluentConditionals = new FluentConditionalsRunnable();
        if (isTrue) {
            fluentConditionals.setDoIfTrue(function);
            fluentConditionals.setTrue(true);
        }
        return fluentConditionals;
    }

    public <T> FluentConditionalsSupplier<T> thenReturn(Supplier<T> supplier) {
        FluentConditionalsSupplier<T> fluentConditionals = new FluentConditionalsSupplier<>();
        if (isTrue) {
            fluentConditionals.setResultIfTrue(supplier);
            fluentConditionals.setTrue(true);
        }
        return fluentConditionals;
    }

    public <T> FluentConditionalsSupplier<T> thenReturn(T suppliedNumber) {
        FluentConditionalsSupplier<T> fluentConditionals = new FluentConditionalsSupplier<>();
        if (isTrue) {
            fluentConditionals.setResultIfTrue(() -> suppliedNumber);
            fluentConditionals.setTrue(true);
        }
        return fluentConditionals;
    }
}