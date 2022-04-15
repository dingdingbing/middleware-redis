package com.dingfubing.redispractice;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2022/4/15 21:05
 */
public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    // -Xoss 用于设置本地方法栈大小，但是hotSpot不区分本地方法栈和虚拟机栈 所以只能用-Xss设置栈大小
    // -Xss128k 使用Xss设置栈的容量，
    // The stack size specified is too small, Specify at least 108k
    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        } catch (Error e) {
            System.out.println("stack length:" + javaVMStackSOF.stackLength);
            throw e;
        }
    }

}
