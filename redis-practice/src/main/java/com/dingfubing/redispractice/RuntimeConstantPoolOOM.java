package com.dingfubing.redispractice;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串常量池OOM 设置堆内存
 * -Xmx6M
 *
 * @author dingfubing
 * @since 2022/4/15 21:34
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // Set<String> set = new HashSet<>();
        // short i = 0;
        // while (true) {
        //     set.add(String.valueOf(i++).intern());
        // }
        // java 7 之后，包括Java 7 字符串常量池移到了堆中，对象的创建也在堆，所以返回的是true
        // 但是如果是一些关键字，会返回false，因为一开始在创建这个对象之前已经存在了这个字符串
        String s = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s.intern() == s);
        System.out.println("计算机".intern());

        String s1 = new String("123");
        String s2 = new String("123");
        System.out.println(s1.intern() == s2.intern());

    }

}
