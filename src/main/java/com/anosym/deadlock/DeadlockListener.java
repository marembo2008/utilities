/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.deadlock;

import java.lang.management.ThreadInfo;

/**
 *
 * @author Administrator
 *
 * This is called whenever a problem with threads is detected.
 * The two events are deadlockDetected() and thresholdExceeded().
 */
public interface DeadlockListener {

    /**
     * @param deadlockedThread The deadlocked thread, with stack
     * trace of limited depth.
     */
    void deadlockDetected(ThreadInfo deadlockedThread);

    /**
     * @param allThreads All the threads in the JVM, without
     * stack traces.
     */
    void thresholdExceeded(ThreadInfo[] allThreads);
}
