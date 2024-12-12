/**
 * BSTreeADT
 */
package utilities;

import java.io.Serializable;

import implementations.BSTreeNode;

/**
 * <p>
 * The <code>BSTreeADT</code> interface is designed to be used as a basis for 
 * the BST data structure that will be developed in the CPRG 304 class at
 * SAIT. The implementors of this interface will be required to add all the
 * functionality.
 * </p>
 * 
 * @param <E> The type of elements this list holds.
 */
public interface BSTreeADT<E extends Comparable<? super E>> extends	Serializable
{
	/**
	 * The node at the root of the Binary Search Tree will be returned.
	 * 
	 * @return node stored at the root of tree is returned
	 * @throws NullPointerException if the tree is empty and there is no root node.
	 */
	public BSTreeNode<E> getRoot() throws NullPointerException;

	/**
	 * Determines the row height of the tree and returns that value as an integer
	 * value.
	 * 
	 * @return the height of the tree.
	 */
	public int getHeight();

	/**
	 * The number of elements currently stored in the tree is counted and the value
	 * is returned.
	 * 
	 * @return number of elements currently stored in tree.
	 */
	public int size();

	/**
	 * Checks if the tree is currently empty.
	 * 
	 * @return returns boolean true if the tree is empty otherwise false.
	 */
	public boolean isEmpty();

	/**
	 * Clears all elements currently stored in tree and makes the tree empty.
	 */
	public void clear();

	/**
	 * Checks the current tree to see if the element passed in is stored in the
	 * tree. If the element is found in the tree the method returns true and if the
	 * element is not in the tree the method returns false.
	 * 
	 * @param entry the element to find in the tree
	 * @return returns boolean true if element is currently in the tree and false if
	 *         the element is not found in the tree
	 * @throws NullPointerException if the element being passed in is null
	 */
	public boolean contains( E entry ) throws NullPointerException;

	/**
	 * Retrieves a node from the tree given the object to search for.
	 * 
	 * @param entry element object being searched
	 * @return the node with the element located in tree, null if not found
	 * @throws NullPointerException if the element being passed in is null
	 */
	public BSTreeNode<E> search( E entry ) throws NullPointerException;

	/**
	 * Adds a new element to the tree according to the natural ordering established
	 * by the Comparable implementation.
	 * 
	 * @param newEntry the element being added to the tree
	 * @return a boolean true if the element is added successfully else false
	 * @throws NullPointerException if the element being passed in is null
	 */
	public boolean add( E newEntry ) throws NullPointerException;

	/**
	 * Removes the smallest element in the tree according to the natural ordering
	 * established by the Comparable implementation.
	 * 
	 * @return the removed element or null if the tree is empty
	 */
	public BSTreeNode<E> removeMin();

	/**
	 * Removes the largest element in the tree according to the natural ordering
	 * established by the Comparable implementation.
	 * 
	 * @return the removed element or null if the tree is empty
	 */
	public BSTreeNode<E> removeMax();

	/**
	 * Generates an in-order iteration over the contents of the tree. Elements are
	 * in their natural order.
	 * 
	 * @return an iterator with the elements in the natural order
	 */
	public Iterator<E> inorderIterator();

	/**
	 * Generates a pre-order iteration over the contents of the tree. Elements are
	 * order in such a way as the root element is first.
	 * 
	 * @return an iterator with the elements in a root element first order
	 */
	public Iterator<E> preorderIterator();

	/**
	 * Generates a post-order iteration over the contents of the tree. Elements are
	 * order in such a way as the root element is last.
	 * 
	 * @return an iterator with the elements in a root element last order
	 */
	public Iterator<E> postorderIterator();
}
