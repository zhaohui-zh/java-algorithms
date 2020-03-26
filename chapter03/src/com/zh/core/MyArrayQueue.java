package com.zh.core;

/**
 * 基于数组的循环队列
 * head指向头节点，tail指向尾节点的后一个节点
 * Created by zhaohui on 2020/3/26
 */
public class MyArrayQueue<E> implements MyQueue<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private int maxLength;
    private int head;
    private int tail;
    private E[] elementData;

    public MyArrayQueue() {
        maxLength = DEFAULT_CAPACITY;
        head = 0;
        tail = 0;
        elementData = (E[]) new Object[maxLength];
    }

    public MyArrayQueue(int maxLength) {
        this.maxLength = maxLength;
        head = 0;
        tail = 0;
        elementData = (E[]) new Object[maxLength];
    }

    @Override
    public boolean empty() {
        return tail == head;
    }

    @Override
    public E add(E e) {
        elementData[tail] = e;
        tail = (tail + 1) % maxLength;
        return e;
    }

    @Override
    public E remove() {
        if (empty())
            return null;
        E element = elementData[head];
        head = (head + 1) % maxLength;
        return element;
    }

    @Override
    public E peek() {
        if (empty())
            return null;
        return elementData[head];
    }

    public static void main(String[] args) {
        MyArrayQueue<Integer> queue = new MyArrayQueue<>(10);
        for (int i = 0; i < 8; i++) {
            queue.add(i);
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            queue.add(i);
        }
        System.out.println();
        for (int i = 0; i < 6; i++) {
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
    }
}
