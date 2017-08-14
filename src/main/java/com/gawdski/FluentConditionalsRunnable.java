package com.gawdski;

import java.util.function.Supplier;

/**
 * @author Anna Gawda
 * @since 14.08.2017
 */
public class FluentConditionalsRunnable {
    private Runnable doIfTrue;
    private boolean isTrue;

    void setDoIfTrue(Runnable runnable) {
        doIfTrue = runnable;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public void orElse(Runnable function) {
        if (!isTrue) function.run();
        else doIfTrue.run();
    }

    public void orElseThrowE(Exception e) throws Exception {
        if(!isTrue) throw e;
        else doIfTrue.run();
    }

    public void orElseThrow(Supplier<Exception> e) throws Exception {
        if(!isTrue) throw e.get();
        else doIfTrue.run();
    }
}
