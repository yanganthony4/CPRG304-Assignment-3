package implementations;

import utilities.BSTreeADT;
import utilities.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {
    private BSTreeNode<E> root;
    private int size;

    public BSTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public BSTreeNode<E> getRoot() throws NullPointerException {
        if (root == null) throw new NullPointerException("Tree is empty.");
        return root;
    }

    @Override
    public int getHeight() {
        return root == null ? 0 : root.getHeight();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(E entry) throws NullPointerException {
        if (entry == null) throw new NullPointerException("Entry is null.");
        return search(entry) != null;
    }

    @Override
    public BSTreeNode<E> search(E entry) throws NullPointerException {
        if (entry == null) throw new NullPointerException("Entry is null.");
        return searchRecursive(root, entry);
    }

    private BSTreeNode<E> searchRecursive(BSTreeNode<E> node, E entry) {
        if (node == null) return null;
        int compare = entry.compareTo(node.getElement());
        if (compare == 0) return node;
        if (compare < 0) return searchRecursive(node.getLeft(), entry);
        return searchRecursive(node.getRight(), entry);
    }

    @Override
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null) throw new NullPointerException("Entry is null.");
        if (!contains(newEntry)) {
            root = addRecursive(root, newEntry);
            return true;
        }
        return false;
    }

    private BSTreeNode<E> addRecursive(BSTreeNode<E> node, E entry) {
        if (node == null) {
            size++;
            return new BSTreeNode<>(entry); // New node starts with height 0
        }
        int compare = entry.compareTo(node.getElement());
        if (compare < 0) {
            node.setLeft(addRecursive(node.getLeft(), entry));
        } else if (compare > 0) {
            node.setRight(addRecursive(node.getRight(), entry));
        }
        updateHeight(node); // Update height after insertion
        return node;
    }

    @Override
    public BSTreeNode<E> removeMin() {
        if (root == null) return null;
        BSTreeNode<E> minNode = findMin(root);
        root = removeMinRecursive(root);
        return minNode;
    }

    private BSTreeNode<E> removeMinRecursive(BSTreeNode<E> node) {
        if (node.getLeft() == null) {
            size--;
            return node.getRight();
        }
        node.setLeft(removeMinRecursive(node.getLeft()));
        updateHeight(node);
        return node;
    }

    private BSTreeNode<E> findMin(BSTreeNode<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public BSTreeNode<E> removeMax() {
        if (root == null) return null;
        BSTreeNode<E> maxNode = findMax(root);
        root = removeMaxRecursive(root);
        return maxNode;
    }

    private BSTreeNode<E> removeMaxRecursive(BSTreeNode<E> node) {
        if (node.getRight() == null) {
            size--;
            return node.getLeft();
        }
        node.setRight(removeMaxRecursive(node.getRight()));
        updateHeight(node);
        return node;
    }

    private BSTreeNode<E> findMax(BSTreeNode<E> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    @Override
    public Iterator<E> inorderIterator() {
        ArrayList<E> elements = new ArrayList<>();
        inorderTraversal(root, elements);
        return new TreeIterator<>(elements);
    }

    private void inorderTraversal(BSTreeNode<E> node, ArrayList<E> elements) {
        if (node != null) {
            inorderTraversal(node.getLeft(), elements);
            elements.add(node.getElement());
            inorderTraversal(node.getRight(), elements);
        }
    }

    @Override
    public Iterator<E> preorderIterator() {
        ArrayList<E> elements = new ArrayList<>();
        preorderTraversal(root, elements);
        return new TreeIterator<>(elements);
    }

    private void preorderTraversal(BSTreeNode<E> node, ArrayList<E> elements) {
        if (node != null) {
            elements.add(node.getElement());
            preorderTraversal(node.getLeft(), elements);
            preorderTraversal(node.getRight(), elements);
        }
    }

    @Override
    public Iterator<E> postorderIterator() {
        ArrayList<E> elements = new ArrayList<>();
        postorderTraversal(root, elements);
        return new TreeIterator<>(elements);
    }

    private void postorderTraversal(BSTreeNode<E> node, ArrayList<E> elements) {
        if (node != null) {
            postorderTraversal(node.getLeft(), elements);
            postorderTraversal(node.getRight(), elements);
            elements.add(node.getElement());
        }
    }

    private void updateHeight(BSTreeNode<E> node) {
        int leftHeight = (node.getLeft() == null) ? 0 : node.getLeft().getHeight();
        int rightHeight = (node.getRight() == null) ? 0 : node.getRight().getHeight();
        node.setHeight(Math.max(leftHeight, rightHeight) + 1); // Height starts at 1
    }

}
