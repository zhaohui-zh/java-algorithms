package com.zh.core;

/**
 * 归并排序抽象类
 * Created by zhaohui on 2020/3/18
 */
public abstract class MergeSort<T extends Comparable<T>> extends Sort<T> {
    protected T[] aux;

    /**
     * 归并
     * @param nums
     * @param l low
     * @param m middle
     * @param h low
     */
    protected void merge(T[] nums, int l, int m, int h) {
       int i = l;
       int j = m + 1;
        for (int k = l; k <= h; k++) {
            aux[k] = nums[k]; //将数据复制到辅助数组
        }
        for (int k = l; k <= h; k++) {
            if (i > m) {
                nums[k] = aux[j++];
            } else if (j > h) {
                nums[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                nums[k] = aux[i++];
            } else {
                nums[k] = aux[j++];
            }
        }
    }
}
