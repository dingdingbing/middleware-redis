package com.dingfubing.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NK002 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        ArrayList<Integer> integers = new ArrayList<>(i);
        while (in.hasNextInt()) {
            integers.add(in.nextInt());
        }
        integers.stream().distinct().sorted(Comparator.comparingInt(a -> a)).collect(Collectors.toList()).forEach(System.out::println);

    }
}
