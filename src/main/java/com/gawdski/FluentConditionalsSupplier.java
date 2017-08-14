package com.gawdski;

import java.util.function.Supplier;

/**
 * @author Anna Gawda
 * @since 14.08.2017
 */
public class FluentConditionalsSupplier <T> {

    private Supplier<T> resultIfTrue;
    private boolean isTrue;

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    void setResultIfTrue(Supplier<T> resultIfTrue) {
        this.resultIfTrue = resultIfTrue;
    }

    T orElse(T suppliedNumber) {
        if(!isTrue) return suppliedNumber;
        else return resultIfTrue.get();
    }

    public T orElse(Supplier<T> supplier) {
        if(!isTrue) return supplier.get();
        else return resultIfTrue.get();
    }

    public T orElseThrowE(Exception e) throws Exception {
        if(!isTrue) throw e;
        else return resultIfTrue.get();
    }

    public T orElseThrow(Supplier<Exception> e) throws Exception {
        if(!isTrue) throw e.get();
        else return resultIfTrue.get();
    }
}
