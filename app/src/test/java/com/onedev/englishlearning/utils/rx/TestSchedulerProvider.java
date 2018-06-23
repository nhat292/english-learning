package com.onedev.englishlearning.mvp.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by Nhat on 12/13/17.
 */

public class TestSchedulerProvider implements SchedulerProvider {

    private final TestScheduler mTestScheduler;

    public TestSchedulerProvider(TestScheduler testScheduler) {
        this.mTestScheduler = testScheduler;
    }

    @Override
    public Scheduler ui() {
        return mTestScheduler;
    }

    @Override
    public Scheduler computation() {
        return mTestScheduler;
    }

    @Override
    public Scheduler io() {
        return mTestScheduler;
    }

}
