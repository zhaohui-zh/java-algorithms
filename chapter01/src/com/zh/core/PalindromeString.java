package com.zh.core;

/**
 * 回文字符串
 * 可以删除一个字符，判断是否能构成回文字符串
 * Created by zhaohui on 2020/3/14
 */
public class PalindromeString {

    public static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--))
                return false;
        }
        return true;
    }

    public static boolean validatePalindrome(String s) {
        if (s == null) {
            return false;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--))
                return isPalindrome(s, i, j+1) || isPalindrome(s, i-1, j);
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abcbfa";
        System.out.println(validatePalindrome(s));
    }

}
