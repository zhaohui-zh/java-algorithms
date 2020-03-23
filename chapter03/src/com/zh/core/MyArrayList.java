package com.zh.core;


/**
 * Created by zhaohui on 2020/3/23
 */
public class MyArrayList<E> implements MyList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int length;
    private int maxLength;
    private E[] elementData;

    public MyArrayList() {
        this.maxLength = DEFAULT_CAPACITY;
        this.length = 0;
        elementData = (E[]) new Object[this.maxLength];
    }

    public MyArrayList(int maxLength) {
        this.maxLength = maxLength;
        this.length = 0;
        elementData = (E[]) new Object[this.maxLength];
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public E get(int index) {
        if (checkBoundary(index)) {
            return elementData[index];
        }
        return null;
    }

    @Override
    public int indexOf(E e) {
        if (e == null) {
            for (int i = 0; i < length; i++) {
                if (elementData[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (elementData[i].equals(e))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public boolean add(E e) {
        if (length < maxLength) {
            elementData[length++] = e;
            return true;
        }
        return false;
    }

    @Override
    public boolean add(int index, E e) {
        if (!checkBoundary(index))
            return false;
        if (length < maxLength) {
            for (int i = length; i > index; i--) {
                elementData[i] = elementData[i - 1];
            }
            elementData[index] = e;
            length++;
            return true;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (!checkBoundary(index))
            return null;
        E e = elementData[index];
        for (int i = index; i < length - 1; i++) {
            elementData[i] = elementData[i+1];
        }
        length--;
        return e;
    }

    @Override
    public boolean remove(E e) {
        for (int i = 0; i < length; i++) {
            if (e.equals(elementData[i])) {
                for (int j = i; j < length - 1; j++) {
                    elementData[j] = elementData[j + 1];
                }
                elementData[length - 1] = null;
                length--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            elementData[i] = null;
        }
        length = 0;
    }

    @Override
    public void print() {
        for (int i = 0; i < length; i++) {
            System.out.print(elementData[i] + " ");
        }
        System.out.println();
    }


    private boolean checkBoundary(int index) {
        return index < length;
    }

    public static void main(String[] args) {
        MyList<Integer> list = new MyArrayList<>();
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
