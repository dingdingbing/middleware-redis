package com.dingfubing.redispractice;

/**
 * 一次对象的自我拯救示例
 * 1.对象可以在被GC时自我拯救
 * 2.这种自救的机会只有一次，就是通过重写finalize方法实现，但是一个对象的finalize方法最多之后被执行一次
 * 所以只能有一次自救机会
 *
 * @author dingfubing
 * @since 2022/4/19 21:04
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC finalizeEscapeGC = null;

    public void isAlive() {
        System.out.println("yes i am alive");
    }

    @Override
    protected void finalize() throws Throwable {
        // debug　进不去~
        super.finalize();
        System.out.println("系统垃圾回收了");
        // 此处重新引用
        finalizeEscapeGC = this;
    }

    public static void main(String[] args) throws InterruptedException {
        finalizeEscapeGC = new FinalizeEscapeGC();

        finalizeEscapeGC = null;
        System.gc();

        Thread.sleep(500);
        if (finalizeEscapeGC != null) {
            finalizeEscapeGC.isAlive();
        } else {
            System.out.println("no i am dead");
        }

        finalizeEscapeGC = null;
        System.gc();

        Thread.sleep(500);
        if (finalizeEscapeGC != null) {
            finalizeEscapeGC.isAlive();
        } else {
            System.out.println("no i am dead");
        }
    }
}
