package com.dingfubing.redispractice;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO
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
        String s = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s.intern() == s);
        System.out.println("计算机".intern());
    }

}