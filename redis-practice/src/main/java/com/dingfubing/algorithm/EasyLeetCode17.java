package com.dingfubing.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/21 15:49
 */
public class EasyLeetCode17 {
    String[] letterMap = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        dg(digits, 0, new StringBuilder());
        return res;
    }
    List<String> res = new ArrayList<>();
    public void dg(String digits, int index, StringBuilder builder) {

        // 退出条件 单字符来看，index = 1 时直接返回  并且防止数组越界
        if (index == digits.length()) {
            res.add(builder.toString());
            return;
        }

        int c = digits.charAt(index) - '0';
        String temp = letterMap[c];
        for (int i = 0; i < temp.length(); i++) {
            builder.append(temp.charAt(i));
            // 会添加一个字符在后面
            dg(digits, index + 1, builder);
            // 剔除后面一个字符，例如 ad 变为 a 才能拼接下一个为ae
            builder.deleteCharAt(builder.length() - 1);
        }

    }
}
