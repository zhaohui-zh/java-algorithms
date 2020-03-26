package com.zh.core;

/**
 * 队列
 * Created by zhaohui on 2020/3/26
 */
public interface MyQueue<E> {

    /**
     * 队列是否为空
     * @return
     */
    boolean empty();

    /**
     * 入队
     * @param e
     * @return
     */
    E add(E e);

    /**
     * 出队
     * @return
     */
    E remove();

    /**
     * 取队首元素但不出队
     * @return
     */
    E peek();

}
