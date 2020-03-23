package com.zh.core;

/**
 * 线性表接口
 * Created by zhaohui on 2020/3/23
 */
public interface MyList<E> {

    /**
     * 获取线性表长度
     * @return
     */
    int size();

    /**
     * 按索引获取元素
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 按元素查找索引
     * @param e
     * @return
     */
    int indexOf(E e);

    /**
     * 在线性表末尾插入新元素
     * @param e
     * @return
     */
    boolean add(E e);

    /**
     * 在指定位置插入新元素
     * @param index
     * @param e
     */
    boolean add(int index, E e);

    /**
     * 删除指定位置的元素
     * @param index
     * @return
     */
    E remove(int index);

    /**
     * 删除指定元素
     * @param e
     * @return
     */
    boolean remove(E e);

    /**
     * 清楚线性表所有元素
     */
    void clear();

    /**
     * 输出线性表中的所有元素
     */
    void print();

}
