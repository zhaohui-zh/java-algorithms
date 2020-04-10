package com.zh.bstree;

/**
 * Binary Search Tree - 二叉查找树
 *
 * @author Zhaohui
 * @date 2020/4/10
 */
public class BSTree<T extends Comparable> {

    private BinaryNode<T> rootNode;

    public BSTree(BinaryNode<T> rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * BSTree是否包含某个数据，从根节点开始查找
     *
     * @param data 待查找数据
     * @return true:包含; false:不包含
     */
    public boolean contains(T data) {
        return contains(data, rootNode);
    }

    /**
     * BSTree是否包含某个数据，从指定节点开始查找
     *
     * @param data 待查找数据
     * @return true:包含; false:不包含
     */
    public boolean contains(T data, BinaryNode<T> node) {
        if (data == null || node == null)
            return false;
        int result = data.compareTo(node.data);
        if (result > 0)
            return contains(data, node.right);
        else if (result < 0)
            return contains(data, node.left);
        else
            return true;
    }

    /**
     * 查找最小元素所在节点
     *
     * @return 最小元素所在节点
     */
    public BinaryNode<T> findMin(BinaryNode<T> node) {
        if (node == null)
            return null;
        else if (node.left == null)
            return node;
        else
            return findMin(node.left);
    }

    /**
     * 查找最大元素所在节点
     *
     * @return 最大元素所在节点
     */
    public BinaryNode<T> findMax(BinaryNode<T> node) {
        if (node == null)
            return null;
        else if (node.right == null)
            return node;
        else
            return findMax(node.right);
    }

    /**
     * 查找最小值
     *
     * @return 查找最小值
     */
    public T findMin() {
        if (rootNode == null)
            return null;
        return findMin(rootNode).data;
    }

    /**
     * 查找最大值
     *
     * @return 查找最大值
     */
    public T findMax() {
        if (rootNode == null)
            return null;
        return findMax(rootNode).data;
    }

    /**
     * 插入元素
     *
     * @param data 待插入数据
     * @param node BSTree的根节点
     * @return 插入后的BSTree的根节点
     */
    public BinaryNode<T> insert(T data, BinaryNode<T> node) {
        if (node == null)
            return new BinaryNode<T>(data, null, null);
        int result = data.compareTo(node.data);
        if (result < 0)
            node.left = insert(data, node.left);
        else
            node.right = insert(data, node.right);
        return node;
    }

    /**
     * 插入元素
     *
     * @param data 待插入数据
     */
    public void insert(T data) {
        rootNode = insert(data, rootNode);
    }

    /**
     * 删除节点
     *
     * @param data 待删除的节点的数据
     * @param node BSTree根节点
     * @return 删除后的BSTree的根节点
     */
    private BinaryNode<T> remove(T data, BinaryNode<T> node) {
        if (node == null)
            return null;
        int result = data.compareTo(node.data);
        if (result < 0)
            node.left = remove(data, node.left);
        else if (result > 0)
            node.right = remove(data, node.right);
        else if (node.left != null && node.right != null) {
            node.data = (T) findMin(node.right).data;
            node.right = remove(node.data, node.right);
        }
        else
            node = node.left == null ? node.right : node.left;
        return node;
    }

    /**
     * 删除节点
     *
     * @param data 待删除节点的数据
     */
    public void remove(T data) {
        rootNode = remove(data, rootNode);
    }



    private static class BinaryNode<T extends Comparable> {
        public T data;
        public BinaryNode left;
        public BinaryNode right;

        public BinaryNode(T data, BinaryNode left, BinaryNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BinaryNode<Integer> myNodeRoot;
        BinaryNode<Integer> nodeRoot = new BinaryNode<>(10, null, null);
        BinaryNode<Integer> nodeLeft = new BinaryNode<>(4, null, null);
        BinaryNode<Integer> nodeRight = new BinaryNode<>(15, null, null);
        myNodeRoot = nodeRoot;
        nodeRoot.left = nodeLeft;
        nodeRoot.right = nodeRight;
        nodeRoot = nodeLeft;
        nodeLeft = new BinaryNode<>(2, null, null);
        nodeRight = new BinaryNode<>(7, null, null);
        nodeRoot.left = nodeLeft;
        nodeRight.right = nodeRight;

        nodeRoot = myNodeRoot.right;
        nodeLeft = new BinaryNode<>(12, null, null);
        nodeRight = new BinaryNode<>(18, null, null);
        nodeRoot.left = nodeLeft;
        nodeRoot.right = nodeRight;

        BSTree<Integer> bsTree = new BSTree<>(myNodeRoot);
        System.out.println(bsTree.contains(18));

        System.out.println(bsTree.findMax());
        System.out.println(bsTree.findMin());

        bsTree.insert(100);

        System.out.println(bsTree.contains(100));
        System.out.println(bsTree.findMax());
        System.out.println(bsTree.findMin());

        System.out.println(bsTree.contains(10));
        bsTree.remove(10);
        System.out.println(bsTree.contains(10));

        System.out.println(bsTree.contains(100));
        System.out.println(bsTree.findMax());
        bsTree.remove(100);
        System.out.println(bsTree.contains(100));
        System.out.println(bsTree.findMax());



    }
}
