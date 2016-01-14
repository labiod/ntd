package com.kgb;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by k.betlej on 1/12/16.
 */
public class ThreadManager {

    private static final ThreadManager sInstance = new ThreadManager();
    Map<String, Thread> mThreads;
    private int mRendering;

    private ThreadManager() {
        mThreads = new HashMap<>();
    }

    public void addTask(String name, Task task) {
        task.setTimeElapse(mRendering);
        Thread thread = mThreads.get(name);
        if(thread != null && thread.isAlive()) {
            thread.interrupt();
        }
        thread = new Thread(task);
        thread.start();
        mThreads.put(name, thread);
    }

    public void killAllTask() {
        System.out.println("kill all task");
        System.out.println("mThreads.length: " + mThreads.size());
        for(Map.Entry<String, Thread> entry : mThreads.entrySet()) {
            if(entry.getValue().isAlive()) {
                System.out.println("interupt task");
                entry.getValue().interrupt();
            }
        }
    }

    public static ThreadManager getInstance() {
        return sInstance;
    }

    public void setRendering(int rendering) {
        mRendering = rendering;
    }
}
