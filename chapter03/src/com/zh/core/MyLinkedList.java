package com.zh.core;

/**
 * 链表
 * Created by zhaohui on 2020/3/24
 */
public class MyLinkedList<E> implements MyList<E> {

    private int length;

    private Node<E> first;

    private Node<E> last;

    public MyLinkedList() {
        length = 0;
        first = null;
        last = null;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public E get(int index) {
        if (!checkBoundary(index))
            return null;
        Node<E> cur = first;
        for (int i = 1; i <= index; i++) {
            cur = cur.next;
        }
        return cur.item;
    }

    @Override
    public int indexOf(E e) {
        int index = 0;
        Node<E> cur = first;
        if (cur.item.equals(e))
            return index;
        for (index = 1; index < length; index++) {
            cur = cur.next;
            if (cur.item.equals(e))
                return index;
        }
        return -1;
    }

    @Override
    public boolean add(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        // 如果链表为空
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        length++;
        return true;
    }

    @Override
    public boolean add(int index, E e) {
        if (!checkBoundary(index))
            return false;
        Node<E> prev, cur;
        cur = first;
        // 获取第index个元素
        for (int i = 1; i <= index; i++) {
            cur = cur.next;
        }
        prev = cur.prev;
        Node<E> newNode = new Node<>(prev, e, cur);
        prev.next = newNode;
        cur.prev = newNode;
        length++;
        return true;
    }

    @Override
    public E remove(int index) {
        if (!checkBoundary(index))
            return null;
        Node<E> prev, cur, next;
        cur = first;
        for (int i = 1; i <= index; i++) {
            cur = cur.next;
        }
        // 如果删除头节点
        if (cur == first) {
            first = cur.next;
            first.prev = null;
        }
        // 如果删除尾节点
        else if (cur == last) {
            last = last.prev;
            last.next = null;
        }
        // 删除其他节点
        else {
            prev = cur.prev;
            next = cur.next;
            prev.next = next;
            next.prev = prev;
        }
        length--;
        return cur.item;
    }

    @Override
    public boolean remove(E e) {
        // 如果删除头节点
        if (first.item.equals(e)) {
            first = first.next;
            first.prev = null;
            length--;
            return true;
        }
        // 如果删除尾节点
        if (last.item.equals(e)) {
            last = last.prev;
            last.next = null;
            length--;
            return true;
        }
        // 其他情况
        boolean flag = false;
        Node<E> prev, cur, next;
        cur = first;
        for (int i = 1; i < length; i++) {
            cur = cur.next;
            if (cur.item.equals(e)) {
                flag = true;
                break;
            }
        }
        // 如果没找到元素
        if (!flag)
            return false;
        prev = cur.prev;
        next = cur.next;
        prev.next = next;
        next.prev = prev;
        length--;
        return true;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        length = 0;
    }

    @Override
    public void print() {
        Node<E> cur = first;
        System.out.print(cur.item + " ");
        for (int i = 1; i < length; i++) {
            cur = cur.next;
            System.out.print(cur.item + " ");
        }
        System.out.println();
    }

    private boolean checkBoundary(int index) {
        return index < length;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args) {
        MyList<Integer> list = new MyLinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.print();
        list.add(1, 100);
        list.print();
        System.out.println(list.get(3));
        System.out.println(list.indexOf(40));
        System.out.println(list.remove(1));
        list.print();
        if (list.remove(Integer.valueOf(10))) {
            list.print();
        }
        list.print();
    }
}
