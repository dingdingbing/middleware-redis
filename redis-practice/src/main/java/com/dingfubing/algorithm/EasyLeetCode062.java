package com.dingfubing.algorithm;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/20 15:36
 */
public class EasyLeetCode062 {

    public String convert(String s, int numRows) {
        int length = s.length();
        ArrayList<StringBuilder> stringBuilders = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            stringBuilders.add(new StringBuilder());
        }
        int i = 0;
        int flag = -1;
        for (char c : s.toCharArray()) {
            stringBuilders.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (StringBuilder builder : stringBuilders) {
            stringBuilder.append(builder);
        }
        return stringBuilder.toString();
    }
}
