package com.dingfubing.deadLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/28 10:05
 */
public class DeadLock01 {

    public static void main(String[] args) {
        // 独占锁
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        reentrantReadWriteLock.readLock().lock();
        reentrantReadWriteLock.writeLock().lock();
    }
}
