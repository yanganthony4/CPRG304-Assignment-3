package implementations;

public class BSTreeNode<E> {
    private E element;
    private BSTreeNode<E> left;
    private BSTreeNode<E> right;
    private int height; // Node height

    public BSTreeNode(E element) {
        this.element = element;
        this.left = null;
        this.right = null;
        this.height = 1; // Default height for a new node
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public BSTreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(BSTreeNode<E> left) {
        this.left = left;
    }

    public BSTreeNode<E> getRight() {
        return right;
    }

    public void setRight(BSTreeNode<E> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

