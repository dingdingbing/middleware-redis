package com.dingfubing.deadLock;

import lombok.SneakyThrows;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/7 16:34
 */
public class DeadLock implements Runnable {

    public static int flag = 1;

    static Object object1 = new Object();
    static Object object2 = new Object();

    @SneakyThrows
    @Override
    public void run() {
        if (flag == 0) {
            synchronized (object1) {
                System.out.println("get o1");
                Thread.sleep(3000);
                synchronized (object2) {
                    System.out.println("get o2");
                }
            }
        }
        if (flag == 1) {
            synchronized (object2) {
                System.out.println("get o2");
                Thread.sleep(3000);
                synchronized (object1) {
                    System.out.println("get o1");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock deadLock = new DeadLock();
        DeadLock.flag = 1;
        new Thread(deadLock).start();
        Thread.sleep(1000);
        DeadLock deadLock1 = new DeadLock();
        DeadLock.flag = 0;
        new Thread(deadLock1).start();
    }
}
