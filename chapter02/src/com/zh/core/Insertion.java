package com.zh.core;

/**
 * 插入排序
 * 每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左侧数组依然有序
 * Created by zhaohui on 2020/3/18
 */
public class Insertion<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 ; j--) {
                if (less(nums[j], nums[j-1])) {
                    swap(nums, j, j-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {9, 8, 6, 5, 4, 3, 2, 1};
        Insertion<Integer> insertion = new Insertion<>();
        insertion.sort(a);
        for (Integer integer : a) {
            System.out.print(integer + " ");
        }
    }
}
