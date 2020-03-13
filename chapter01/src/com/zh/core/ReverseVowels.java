package com.zh.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 反转字符串中的元音字符
 * Created by zhaohui on 2020/3/14
 */
public class ReverseVowels {

    public static final Set<Character> vowels = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public static String reverseVowels(String s) {
        if (s == null) {
            return null;
        }
        int i = 0;
        int j = s.length() - 1;
        char[] result = new char[s.length()];
        while (i <= j) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            if (!vowels.contains(c1))
                result[i++] = c1;
            if (!vowels.contains(c2))
                result[j--] = c2;
            if (vowels.contains(c1) && vowels.contains(c2)) {
                result[i++] = c2;
                result[j--] = c1;
            }
        }
        return new String(result);
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(reverseVowels(s));
    }

}
