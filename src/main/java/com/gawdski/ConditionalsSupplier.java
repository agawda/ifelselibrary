package com.gawdski;

import java.util.function.Supplier;

/**
 * @author Anna Gawda
 * @since 14.08.2017
 */
public class ConditionalsSupplier {
    private boolean isConditionTrue;
    private Supplier<Integer> thenReturnSupplier;

    public ConditionalsSupplier(Supplier<Integer> thenReturnSupplier, boolean isConditionTrue) {
        this.thenReturnSupplier = thenReturnSupplier;
        this.isConditionTrue = isConditionTrue;
    }

    public Integer orElse(Supplier<Integer> supplier) {
        if(isConditionTrue) return thenReturnSupplier.get();
        return supplier.get();
    }

}
