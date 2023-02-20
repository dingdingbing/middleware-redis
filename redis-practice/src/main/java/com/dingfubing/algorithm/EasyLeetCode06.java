package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/20 14:50
 */
public class EasyLeetCode06 {
    
    public String convert(String s, int numRows) {
        int n = numRows * 2 -2;
        int numCols = n * (numRows - 1);
        char[][] table = new char[numRows][numCols];
        char[] chars = s.toCharArray();
        int row = 0, col = 0;
        for (int i = 0; i < chars.length; i++) {
            table[row][col] = chars[i];
            if (i % n < numCols - 1) {
                row++;
            } else {
                col++;
                row--;
            }
        }
        return "";
    }
}
