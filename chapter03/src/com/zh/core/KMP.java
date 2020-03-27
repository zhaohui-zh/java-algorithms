package com.zh.core;

/**
 * KMP算法-快速字符串匹配算法
 * Created by zhaohui on 2020/3/27
 */
public class KMP {

    private static int[] next = new int[1000];

    private static void buildNext(String p) {
        next[0] = 0;
        int i = 1;
        int now = 0;
        while (i < p.length()) {
            if (p.charAt(now) == p.charAt(i)) {
                now++;
                next[i++] = now;
            } else if (now != 0) {
                // now ！= 0，缩小
                now = next[now-1];
            } else {
                // now == 0,无法再缩
                next[i++] = now;
            }
        }
    }

    public static int search(String s, String p) {
        buildNext(p);
        int i = 0;
        int j = 0;
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else if (j != 0) {
                j = next[j-1];
            } else {
                i++;
            }
        }
        if (j == p.length())
            return i - j;
        else
            return -1;
    }

    public static void main(String[] args) {
        String s = "ababaabaabac";
        String p = "abaabac";
        System.out.println(search(s, p));
        String str = "abcdef";
        String subStr = "def";
        System.out.println(search(str, subStr));
    }

}
