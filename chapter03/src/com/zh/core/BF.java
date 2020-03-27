package com.zh.core;

/**
 * BF算法-经典字符串匹配算法
 * Created by zhaohui on 2020/3/27
 */
public class BF {

    /**
     * 返回字串subStr再主串str中的位置，-1表示没找到
     * @param str 主串
     * @param subStr 字串
     * @return int
     */
    public static int find(String str, String subStr) {
        char[] s = str.toCharArray();
        char[] t = subStr.toCharArray();
        int i = 0;
        int j = 0;
        while (i < s.length && j < t.length) {
            if (s[i] == t[j]) {
                i++;
                j++;
            } else {
                // i回退，j置0
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == t.length)
            return i - j;
        else
            return -1;
    }

    public static int find2(String str, String subStr) {
        int i = 0;
        int j = 0;
        while (i < str.length() && j < subStr.length()) {
            if (str.charAt(i) == subStr.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == subStr.length())
            return i - j;
        else
            return -1;
    }

    public static void main(String[] args) {
        String s = "ababaabaabac";
        String p = "abaabac";
        System.out.println(find(s, p));
        String str = "abcdef";
        String subStr = "def";
        System.out.println(find(str, subStr));
        System.out.println(find2(str, subStr));
    }
}
