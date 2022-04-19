package com.dingfubing.redispractice;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * 使用 unsafe 分配本机内存
 * 测试直接内存 OOM
 * -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * @author dingfubing
 * @since 2022/4/19 20:08
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field declaredField = Unsafe.class.getDeclaredFields()[0];
        declaredField.setAccessible(true);
        Unsafe unsafe = (Unsafe)declaredField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
