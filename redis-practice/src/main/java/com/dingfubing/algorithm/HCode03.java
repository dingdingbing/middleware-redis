package com.dingfubing.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/22 17:21
 */
public class HCode03 {

    public static void main(String[] args) {
        char[] chars = new char[]{'a','b', 'c'};
        // k->char  v->count
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        // 合并重复key，并累积次数
        for (char aChar : chars) {
            characterIntegerHashMap.merge(aChar, 1, Integer::sum);
        }
        int count = calc(chars.length);
        for (Character character : characterIntegerHashMap.keySet()) {
            count = count / calc(characterIntegerHashMap.get(character));
        }
    }


    public static int calc(int count) {
        if (count == 1) {
            return 1;
        }
        return count * calc(count - 1);
    }
}

