package com.zh.core;

/**
 * 基于链表的循环队列
 * Created by zhaohui on 2020/3/26
 */
public class MyLinkedQueue<E> implements MyQueue<E> {

    private Node<E> headPointer; //头指针
    private Node<E> tailPointer; //尾指针

    public MyLinkedQueue() {
        Node<E> headNode = new Node<>(); //头节点
        headNode.item = null;
        headNode.next = null;
        headPointer = headNode;
        tailPointer = headNode;
    }

    @Override
    public boolean empty() {
        return headPointer == tailPointer;
    }

    @Override
    public E add(E e) {
        Node<E> newNode = new Node<>();
        newNode.item = e;
        newNode.next = null;
        tailPointer.next = newNode;
        tailPointer = newNode;
        return e;
    }

    @Override
    public E remove() {
        if (empty())
            return null;
        Node<E> firstNode = headPointer.next; //取队列第一个元素
        E e = firstNode.item;
        headPointer.next = firstNode.next;
        return e;
    }

    @Override
    public E peek() {
        if (empty())
            return null;
        return headPointer.next.item;
    }

    private static class Node<E> {
        public E item;
        public Node<E> next;
    }

    public static void main(String[] args) {
        MyLinkedQueue<Integer> queue = new MyLinkedQueue<>();
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
