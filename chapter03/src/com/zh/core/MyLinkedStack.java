package com.zh.core;

/**
 * 链式栈
 * Created by zhaohui on 2020/3/25
 */
public class MyLinkedStack<E> implements MyStack<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int length;
    private Node<E> top;
    private Node<E> bottom;

    public MyLinkedStack() {
        length = 0;
        top = null;
        bottom = null;
    }

    private int size() {
        return length;
    }

    @Override
    public boolean empty() {
        return size() == 0;
    }

    @Override
    public E peek() {
        if (empty())
            return null;
        return top.item;
    }

    @Override
    public E pop() {
        if (empty())
            return null;
        E item = top.item;
        top = top.next;
        length--;
        return item;
    }

    @Override
    public E push(E item) {
        if (empty()) {
            Node<E> node = new Node<>(item, null);
            bottom = node;
            top = node;
            length++;
            return item;
        }
        Node<E> node = new Node<>(item, top);
        top = node;
        length++;
        return item;
    }

    private static class Node<E> {
        public E item;
        public Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedStack<Integer> myStack = new MyLinkedStack<>();
        for (int i = 0; i < 100; i++) {
            myStack.push(i);
        }
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
    }
}
