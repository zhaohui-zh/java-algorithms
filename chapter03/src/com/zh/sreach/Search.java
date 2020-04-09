package com.zh.sreach;

/**
 * 查找
 *
 * <P>顺序查找、二分查找、插值查找、分块查找
 *
 * @author Zhaohui
 * @date 2020/4/9
 */
public class Search {

    /**
     * 顺序查找-从前往后
     *
     * <p>数据元素在查找表中的位置为1~n
     *
     * @param list 查找表
     * @param item 待查找元素
     * @return 待查找元素在查找表中的索引
     */
    public int sqeSearch1(Node[] list, Node item) {
        int i = 1;
        while ((i <= list.length - 1) && (list[i].key != item.key))
            i++;
        if (i <= list.length - 1)
            return i;
        return 0;
    }

    /**
     * 顺序查找-从后往前
     *
     * <p>数据元素在查找表中的位置为1~n
     *
     * @param list 查找表
     * @param item 待查找元素
     * @return 待查找元素在查找表中的索引
     */
    public int sqeSearch2(Node[] list, Node item) {
        int i = list.length - 1;
        while (i >= 1 && list[i].key != item.key)
            i--;
        if (i >= 1)
            return i;
        return 0;
    }

    /**
     * 顺序查找-加监视哨
     *
     * <p>数据元素在查找表中的位置为1~n，查找表的第0个元素存放待查找元素
     *
     * @param list 查找表
     * @param item 待查找元素
     * @return 待查找元素在查找表中的索引
     */
    public int sqeSearch3(Node[] list, Node item) {
        list[0] = item;
        int i = list.length - 1;
        while (list[i].key != item.key)
            i--;
        return i;
    }

    /**
     * 二分查找
     *
     * <p>数据元素在查找表中的位置为1~n
     *
     * @param list 待查表
     * @param item 待查找元素
     * @return 待查找元素在超招标中的索引
     */
    public int binarySearch(Node[] list, Node item) {
        int low = 1;
        int high = list.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (item.key == list[mid].key)
                return mid;
            else if (item.key < mid)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return 0;
    }

    /**
     * 插值查找
     *
     * <p>数据元素在查找表中的位置为1~n
     *
     * @param list 待查表
     * @param item 待查找元素
     * @return 待查找元素在超招标中的索引
     */
    public int binarySearch2(Node[] list, Node item) {
        int low = 1;
        int high = list.length - 1;
        int mid;
        while (low <= high) {
            double rate = ((double) (item.key - list[low].key)) / (list[high].key - list[low].key);
            mid = low + (int) rate * (high - low);
            if (item.key == list[mid].key)
                return mid;
            else if (item.key < mid)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return 0;
    }

    private static class Node {
        public int key;
        public String value;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, "One");
        Node node2 = new Node(2, "Two");
        Node node3 = new Node(3, "Three");
        Node node4 = new Node(4, "Four");
        Node node5 = new Node(5, "Five");

        Node[] list = {null, node1, node2, node3, node4, node5};

        Search search = new Search();
        System.out.println(search.sqeSearch1(list, node4));
        System.out.println(search.sqeSearch2(list, node4));
        System.out.println(search.sqeSearch3(list, node4));
        System.out.println(search.binarySearch(list, node4));
        System.out.println(search.binarySearch2(list, node4));
    }
}
