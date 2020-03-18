package com.zh.core;

/**
 * 排序抽象类
 * Created by zhaohui on 2020/3/18
 */
public abstract class Sort<T extends Comparable<T>> {

    public abstract void sort(T[] nums);

    protected boolean less(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }

    protected void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
