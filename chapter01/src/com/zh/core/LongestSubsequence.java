package com.zh.core;

import java.util.Arrays;
import java.util.List;

/**
 * 最长子序列
 * 删除 s 中的一些字符，使得它构成字符串列表 d 中的一个字符串，
 * 找出能构成的最长字符串。如果有多个相同长度的结果，返回字典序的最小字符串
 * Created by zhaohui on 2020/3/14
 */
public class LongestSubsequence {

    private static boolean isSubString(String s, String target) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < target.length()) {
            if (s.charAt(i) == target.charAt(j))
                j++;
            i++;
        }
        return j == target.length();
    }

    public static String findLongestWord(String s, List<String> d) {
        String longestWord = "";
        for (String target : d) {
            int l1 = longestWord.length();
            int l2 = target.length();
            if (l2 < l1 || (l2 == l1 && longestWord.compareTo(target) < 0))
                continue;
            if (isSubString(s, target))
                longestWord = target;
        }
        return longestWord;
    }

    public static void main(String[] args) {
        String  s = "abpcplea";
        String[] strList = {"ale","apple","monkey","plea"};
        List<String> d = Arrays.asList(strList);
        System.out.println(findLongestWord(s, d));
    }

}
