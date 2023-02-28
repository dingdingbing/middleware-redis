package com.dingfubing.deadLock;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/28 10:08
 */
public class DeadLock02 {

    static Object object1 = new Object();
    static Object object2 = new Object();
    static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            if (flag) {
                synchronized (object1) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (object2) {
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            if (!flag) {
                synchronized (object2) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (object1) {
                    }
                }
            }
        });
        t1.start();
        Thread.sleep(500);
        flag = !flag;
        t2.start();

    }


}
