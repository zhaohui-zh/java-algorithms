package com.zh.core;

/**
 * 顺序栈
 * Created by zhaohui on 2020/3/25
 */
public class MyArrayStack<E> implements MyStack<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int length;
    private int maxLength;
    private E[] elementData;
    private int top;

    public MyArrayStack() {
        length = 0;
        maxLength = DEFAULT_CAPACITY;
        elementData = (E[]) new Object[maxLength];
        top = -1;
    }

    /**
     * 返回栈中元素个数
     * @return
     */
    private int size() {
        return length;
    }

    /**
     * 栈是否为空
     * @return
     */
    @Override
    public boolean empty() {
        return size() == 0;
    }

    /**
     * 返回栈顶元素，不出栈
     * @return
     */
    @Override
    public E peek() {
        if (empty()) {
            return null;
        }
        return elementData[top];
    }

    /**
     * 返回栈顶元素并将其出栈
     * @return
     */
    @Override
    public E pop() {
        if (empty()) {
            return null;
        }
        adjustSize();
        E item = peek();
        elementData[top--] = null;
        length--;
        return item;
    }

    /**
     * 元素入栈并返回入栈元素
     * @param item
     * @return
     */
    @Override
    public E push(E item) {
        adjustSize();
        elementData[++top] = item;
        length++;
        return item;
    }

    /**
     * 动态调整栈的大小
     */
    private void adjustSize() {
        if (top > maxLength / 2) {
            maxLength *= 2;
            E[] newElementData = (E[]) new Object[maxLength];
            System.arraycopy(elementData, 0, newElementData, 0, top+1);
            // for (int i = 0; i <= top ; i++) {
            //     newElementData[i] = elementData[i];
            // }
            elementData = newElementData;
        }
        if ((top < maxLength / 4) && (maxLength / 4 > DEFAULT_CAPACITY)) {
            maxLength /= 4;
            E[] newElementData = (E[]) new Object[maxLength];
            System.arraycopy(elementData, 0, newElementData, 0, top+1);
            // for (int i = 0; i <= top ; i++) {
            //     newElementData[i] = elementData[i];
            // }
            elementData = newElementData;
        }
    }

    public static void main(String[] args) {
        MyArrayStack<Integer> myArrayStack = new MyArrayStack<>();
        for (int i = 0; i < 100; i++) {
            myArrayStack.push(i);
        }
        System.out.println(myArrayStack.peek());
        System.out.println(myArrayStack.pop());
        System.out.println(myArrayStack.peek());
        System.out.println(myArrayStack.pop());
        System.out.println(myArrayStack.peek());
        System.out.println(myArrayStack.pop());
    }

}
