package com.zh.core;

/**
 * 栈
 * Created by zhaohui on 2020/3/25
 */
public interface MyStack<E> {


    /**
     * 栈是否为空
     * @return
     */
    boolean empty();

    /**
     * 返回栈顶元素，不出栈
     * @return
     */
    E peek();

    /**
     * 返回栈顶元素并将其出栈
     * @return
     */
    E pop();

    /**
     * 元素入栈并返回入栈元素
     * @param item
     * @return
     */
    E push(E item);

}
