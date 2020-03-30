package com.zh.core;

/**
 * 二叉树
 * <p>
 * 前序、中序、后续遍历二叉树
 *
 * @author Zhaohui
 * @date 2020/3/30
 */
public class BinaryTree {

    public Node<Character> root;

    /**
     * 构建一棵二叉树
     */
    public BinaryTree() {
        Node<Character> nodeD = new Node<>('D', null, null);
        Node<Character> nodeC = new Node<>('C', null, null);
        Node<Character> nodeB = new Node<>('B', null, nodeD);
        Node<Character> nodeA = new Node<>('A', nodeB, nodeC);
        root = nodeA;
    }

    /**
     * 前序遍历
     *
     * @param node 二叉树根节点
     */
    public void traversePreOrder(Node<Character> node) {
        if (node != null) {
            System.out.println(node.data);
            traversePreOrder(node.lchild);
            traversePreOrder(node.rchild);
        }
    }

    /**
     * 非递归前序遍历
     *
     * @param node 二叉树根节点
     */
    public void traversePreOrderNoRecursion(Node<Character> node) {
        MyQueue<Node<Character>> queue = new MyLinkedQueue<>();
        Node<Character> curNode = node;
        if (node != null) {
            System.out.println(curNode.data);
            queue.add(curNode);
            curNode = curNode.lchild;
        }
        while (!queue.empty() && curNode != null) {
            while (curNode != null) {
                System.out.println(curNode.data);
                queue.add(curNode);
                curNode = curNode.lchild;
            }
            if (!queue.empty()) {
                curNode = queue.remove().rchild;
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param node 二叉树根节点
     */
    public void traverseInOrder(Node<Character> node) {
        if (node != null) {
            traverseInOrder(node.lchild);
            System.out.println(node.data);
            traverseInOrder(node.rchild);
        }
    }

    /**
     * 后序遍历
     *
     * @param node 二叉树根节点
     */
    public void traversePostOrder(Node<Character> node) {
        if (node != null) {
            traversePostOrder(node.lchild);
            traversePostOrder(node.rchild);
            System.out.println(node.data);
        }
    }

    /**
     * 层次遍历（广度优先遍历）
     *
     * @param node 二叉树根节点
     */
    public void traverseLevel(Node<Character> node) {
        MyQueue<Node<Character>> queue = new MyLinkedQueue<>();
        // 将根节点放入队列
        if (node != null) {
            System.out.println(node.data);
            if (node.lchild != null)
                queue.add(node.lchild);
            if (node.rchild != null)
                queue.add(node.rchild);
        }
        while (!queue.empty()) {
            Node<Character> curNode = queue.remove();
            System.out.println(curNode.data);
            if (curNode.lchild != null)
                queue.add(curNode.lchild);
            if (curNode.rchild != null)
                queue.add(curNode.rchild);
        }
    }

    /**
     * 求二叉树深度
     *
     * @param node 二叉树根节点
     * @return 二叉树深度
     */
    public int getDepth(Node<Character> node) {
        int depth, ldepth, rdepth;
        if (node == null)
            depth = 0;
        else {
            ldepth = getDepth(node.lchild);
            rdepth = getDepth(node.rchild);
            depth = 1 + (ldepth > rdepth ? ldepth : rdepth);
        }
        return depth;

    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        System.out.println("PreOrder");
        binaryTree.traversePreOrder(binaryTree.root);
        System.out.println("PreOrder No Recursion");
        binaryTree.traversePreOrderNoRecursion(binaryTree.root);
        System.out.println("InOrder");
        binaryTree.traverseInOrder(binaryTree.root);
        System.out.println("PostOrder");
        binaryTree.traversePostOrder(binaryTree.root);
        System.out.println("Level");
        binaryTree.traverseLevel(binaryTree.root);
        System.out.println("Depth");
        System.out.println(binaryTree.getDepth(binaryTree.root));
    }

    private static class Node<E> {
        E data;
        Node<E> lchild;
        Node<E> rchild;

        Node(E data, Node<E> lchild, Node<E> rchild) {
            this.data = data;
            this.lchild = lchild;
            this.rchild = rchild;
        }
    }
}
