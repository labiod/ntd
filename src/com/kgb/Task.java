package com.kgb;

/**
 * Created by k.betlej on 1/13/16.
 */
public abstract class Task implements Runnable {

    private int mRendering = 60;
    private boolean mEndLoop = false;

    public abstract void init();

    public abstract void loop();

    @Override
    public final void run() {
        init();
        boolean interrupted = false;
        try {
            while (!interrupted && !mEndLoop) {
                loop();
                Thread.sleep(1000 / mRendering);
                interrupted = Thread.currentThread().isInterrupted();
                System.out.println("loop");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void endLoop() {
        mEndLoop = true;
    }

    public void setTimeElapse(int rendering) {
        mRendering = rendering;
    }
}
