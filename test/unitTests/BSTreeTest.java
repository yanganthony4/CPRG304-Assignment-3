package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.Iterator;

import implementations.BSTree;
import implementations.BSTreeNode;

/**
 * @author kitty, maryam
 * @version 2.2 Aug.30, 2024  
 * Class Description:
 * Linked-list-based implementation of the BSTreeADT defined in the CPRG 304
 * Assignment 3.
 */

public class BSTreeTest {
	// Attributes
	private BSTree<Integer> tree;
	private Integer one;
	private Integer two;
	private Integer three;
	private Integer four;
	private Integer five;
	private Integer six;
	private Integer seven;

	/**
	 * Initializes a new BSTree instance and seven Integer instances before each test.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tree = new BSTree<Integer>();
		one = 11;
		two = 22;
		three = 33;
		four = 44;
		five = 55;
		six = 66;
		seven = 77;
	}

	/**
	 * Cleans up instances used after each test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		tree = null;
		one = null;
		two = null;
		three = null;
		four = null;
		five = null;
		six = null;
		seven = null;
	}

	/**
	 * Test method for 
	 * {@link implementations.BSTree#BSTree()}
	 * for creating a new BSTree object with a size of zero.
	 */
	@Test
	public void testBST_Constructor() {
		boolean expected = true;
		boolean actual = tree != null;
		assertEquals("Failed to create tree.", expected, actual);
		assertEquals("Failed to return correct size.", 0, tree.size());
	}

	/**
	 * Test method for
	 * {@link implementations.BSTree#add(java.lang.Comparable)}
	 * to add an item to an empty BSTree.
	 */
	@Test
	public void testBSTreeE() {
		int expected = 11;
		int actual = 0;
		BSTreeNode<Integer> i = null;

		BSTree<Integer> newTree = new BSTree<Integer>();
		assertTrue(newTree.add(one));

		i = newTree.getRoot();
		actual = i.getElement();

		assertEquals("Failed to add item correctly to root.", expected, actual);
		assertEquals("Size of tree should be ", 1, newTree.size());
		assertEquals("Height of tree should be ", 1, tree.getHeight());
	}

	/**
	 * Test method for
	 * {@link implementations.BSTree#add(java.lang.Comparable)}
	 * to add an item to the left of the root.
	 */	
	@Test
	public void testAddNewElement_LeftChild() {
		int expected1 = 22;
		int expected2 = 33;
		BSTreeNode<Integer> v1 = null;
		BSTreeNode<Integer> v2 = null;
		tree.add(three);
		assertTrue(tree.add(two));

		v1 = tree.search(two);
		v2 = tree.search(three);

		int actual1 = v1.getElement();
		int actual2 = v2.getElement();
		assertEquals("Failed to add element correctly.", expected1, actual1);
		assertEquals("Failed to add element correctly.", expected2, actual2);
		assertEquals("Failed to update size correctly.", 2, tree.size());
		assertEquals("Failed to update height correctly.", 2, tree.getHeight());
	}

	/**
	 * Test method for
	 * {@link implementations.BSTree#add(java.lang.Comparable)}
	 * to add an item to the right of the root.
	 */	
	@Test
	public void testAddNewElement_RightChild() {
		int expected = 44;
		BSTreeNode<Integer> value = null;
		tree.add(three);
		assertTrue(tree.add(four));

		value = tree.search(four);

		int actual = value.getElement();
		assertEquals("Failed to add element correctly.", expected, actual);
		assertEquals("Failed to update size correctly.", 2, tree.size());
		assertEquals("Failed to update height correctly.", 2, tree.getHeight());
	}
	
	/**
	 * Test method for
	 * {@link implementations.BSTree#add(java.lang.Comparable)}
	 * to add many items to the tree.
	 */	
	@Test
	public void testAddNewElement_Many() {
		int expected = 77;
		BSTreeNode<Integer> value = null;
		tree.add(four);
		tree.add(two);
		tree.add(six);
		tree.add(one);
		tree.add(five);
		tree.add(seven);

		value = tree.search(seven);

		int actual = value.getElement();
		assertEquals("Failed to add element correctly.", expected, actual);
		assertEquals("Failed to update size correctly.", 6, tree.size());
		assertEquals("Failed to update height correctly.", 3, tree.getHeight());
	}

	/**
	 * Test method for
	 * {@link implementations.BSTree#add(java.lang.Comparable)}
	 * to throw a NullPointerException when passing a null.
	 */	
	@Test
	public void testAddNewElementForNullPointerException() {
		Integer value = null;
		try {
			tree.add(value);
			fail("Add method failed to throw NullPointerException.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.BSTree#clear()}
	 * to clear the tree.
	 */
	@Test
	public void testClear_Size() {
		tree.add(four);
		tree.add(six);
		tree.add(two);
		tree.clear();
		assertEquals("Size of tree should be ", 0, tree.size());
	}

	/**
	 * Test method for
	 * {@link implementations.BSTree#contains(java.larng.Comparable)}
	 * to return true when the tree contains an item.
	 */
	@Test
	public void testContainsForTrue() {
		tree.add(four);
		tree.add(six);
		tree.add(two);
		
		assertTrue("Failed to return true.",tree.contains(four));
		
		assertTrue("Failed to return true.",tree.contains(six));
		
		assertTrue("Failed to return true.",tree.contains(two));
	}

	/**
	 * Test method for
	 * {@link implementations.BSTree#contains(java.larng.Comparable)}
	 * to return false when the tree doesn't contains an item.
	 */
	@Test
	public void testContainsForFalse() {
		tree.add(four);
		tree.add(six);
		tree.add(two);
		
		assertFalse("Failed to return false.",tree.contains(one));
		
		assertFalse("Failed to return true.",tree.contains(seven));
		
	}
	/**
	 * Test method for
	 * {@link implementations.BSTree#contains(java.larng.Comparable)}
	 * to throw a NullPointerException when null is passed.
	 */
	@Test
	public void testContainsForException() {
		try {
			tree.contains(null);
			fail("Failed to throw NullPointerException.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for
	 * {@link implementations.BSTree#search(java.lang.Comparable)}
	 * to search for an item found at the root level.
	 */
	@Test
	public void testSearch_Root() {
		tree.add(one);
		tree.add(two);
		tree.add(three);

		int expected = 11;
		int actual = tree.search(one).getElement();
	
		assertEquals("Failed to return the correct element.", expected, actual);
	}

	/**
	 * Test method for
	 * {@link implementations.BSTree#search(java.lang.Comparable)}
	 * to search for an item found at level 2.
	 */
	@Test
	public void testSearch_Level2() {
		tree.add(one);
		tree.add(two);
		tree.add(three);

		int expected = 22;
		int actual = tree.search(two).getElement();

		assertEquals("Failed to return the correct element.", expected, actual);
	}
	
	/**
	 * Test method for
	 * {@link implementations.BSTree#search(java.lang.Comparable)}
	 * to search for an item found at level 3.
	 */
	@Test
	public void testSearch_Level3() {
		tree.add(one);
		tree.add(two);
		tree.add(three);

		int expected = 33;
		int actual = tree.search(three).getElement();
	
		assertEquals("Failed to return the correct element.", expected, actual);
	}
	
	/**
	 * Test method for
	 * {@link implementations.BSTree#search(java.lang.Comparable)}
	 * to return null when searching for an item not found.
	 */
	@Test
	public void testSearch_NotFound() {
		tree.add(one);
		tree.add(two);
		tree.add(three);
		tree.add(four);

		Integer expected = null;
		BSTreeNode<Integer> actual = tree.search(five);
		
		assertEquals("Failed to return null.", expected, actual);
	}

	/**
	 * Test method for
	 * {@link implementations.BSTree#search(java.lang.Comparable)}
	 * to throw NullPointerException when searching for an item not found.
	 */
	@Test
	public void testSearch_NullPointerException() {
		try {
			tree.search(null);
			fail("Failed to throw NullPointerException.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.BSTree#getHeight()}
	 * to return the height of a balanced tree.
	 */
	@Test
	public void testGetHeight_Balanced() {
		tree.add(two);
		tree.add(three);
		tree.add(one);
		tree.add(four);
		int expected = 3;
		int actual = tree.getHeight();
		assertEquals("Failed to return correct height.", expected, actual);
	}

	/**
	 * Test method for 
	 * {@link implementations.BSTree#getHeight()}
	 * to return the height of a ill-balanced tree.
	 */
	@Test
	public void testGetHeight_IllBalanced() {
		tree.add(one);
		tree.add(two);
		tree.add(three);
		tree.add(four);
		tree.add(five);
		tree.add(six);
		tree.add(seven);
		int expected = 7;
		int actual = tree.getHeight();
		assertEquals("Failed to return correct height.", expected, actual);
	}

	/**
	 * Test method for 
	 * {@link implementations.BSTree#getRoot()}
	 * to return the root node of the tree.
	 */
	@Test
	public void testGetRoot() {
		tree.add(three);
		tree.add(two);
		tree.add(four);
		int expected = three;
		int actual = tree.getRoot().getElement();
		
		assertEquals("Failed to return the root element.", expected, actual);
	}
	
	/**
	 * Test method for 
	 * {@link implementations.BSTree#getRoot()}
	 * to throw NullPointerException when getting the root of an empty tree.
	 */
	@Test
	public void testGetRoot_NullPointerException() {
		try {
			tree.getRoot();
			fail("Failed to throw NullPointerException.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.BSTree#isEmpty()}
	 * to return true when the tree is empty.
	 */
	@Test
	public void testIsEmptyForTrue() {
		assertTrue("Failed to return true.", tree.isEmpty());
	}

	/**
	 * Test method for 
	 * {@link implementations.BSTree#isEmpty()}
	 * to return false when the tree is not empty.
	 */
	@Test
	public void testIsEmptyForFalse() {
		tree.add(one);
		assertFalse("Failed to return false.", tree.isEmpty());
	}

	/**
	 * Test method for 
	 * {@link implementations.BSTree#inorderIterator()}
	 * to return an iterator to iterate over the tree using the in-order traversal.
	 */
	@Test
	public void testInorderIterator() {
		tree.add(four);
		tree.add(two);
		tree.add(six);
		tree.add(one);
		tree.add(five);
		tree.add(three);
		tree.add(seven);

		Integer[] shouldBe = { one, two, three, four, five, six, seven };
		Iterator<Integer> it = tree.inorderIterator();
		int i = 0;
		boolean actual = true;
		while (it.hasNext()) {
			if (it.next() != shouldBe[i++]) {
				actual = false;
			}
		}

		assertEquals("Failed to return the correct in-order iterator.", true, actual);
	}

	/**
	 * Test method for 
	 * {@link implementations.BSTree#postorderIterator()}
	 * to return an iterator to iterate over the tree using the post-order traversal.
	 */
	@Test
	public void testPostorderIterator() {
		tree.add(four);
		tree.add(two);
		tree.add(six);
		tree.add(one);
		tree.add(five);
		tree.add(three);
		tree.add(seven);

		Integer[] shouldBe = { one, three, two, five, seven, six, four };
		Iterator<Integer> it = tree.postorderIterator();
		int i = 0;
		boolean actual = true;
		while (it.hasNext()) {
			if (it.next() != shouldBe[i++]) {
				actual = false;
			}
		}

		assertEquals("Postorder iterator is out of order ", true, actual);
	}

	/**
	 * Test method for 
	 * {@link implementations.BSTree#preorderIterator()}
	 * to return an iterator to iterate over the tree using the pre-order traversal.
	 */
	@Test
	public void testPreorderIterator() {
		tree.add(four);
		tree.add(two);
		tree.add(six);
		tree.add(one);
		tree.add(five);
		tree.add(three);
		tree.add(seven);

		Integer[] shouldBe = { four, two, one, three, six, five, seven };
		Iterator<Integer> it = tree.preorderIterator();
		int i = 0;
		boolean actual = true;
		while (it.hasNext()) {
			if (it.next() != shouldBe[i++]) {
				actual = false;
			}
		}

		assertEquals("Preorder iterator is out of order ", true, actual);
	}

	/**
	 * Test method for 
	 * {@link implementations.BSTree#size()}
	 * to return the size of a non-empty tree.
	 */
	@Test
	public void testSize() {
		tree.add(four);
		tree.add(two);
		tree.add(six);
		tree.add(one);
		tree.add(five);

		int expected = 5;
		int actual = tree.size();
		assertEquals("Failed to return correct size.", expected, actual);
	}
	
	
	/**
	 * Test method for 
	 * {@link implementations.BSTree#removeMin()}
	 * to return the minimum value in the tree.
	 */
	@Test
	public void testRemoveMin() {
		tree.add(four);
		tree.add(two);
		tree.add(six);
		tree.add(one);
		tree.add(five);

		int expected = 11;
		int actual = tree.removeMin().getElement();
		assertEquals("Failed to return minimum value.", expected, actual);
	}
	
	/**
	 * Test method for 
	 * {@link implementations.BSTree#removeMin()}
	 * to return null when tree is empty.
	 */
	@Test
	public void testRemoveMinNull() {
		assertNull("Failed to return null.", tree.removeMin());
	}
	
	/**
	 * Test method for 
	 * {@link implementations.BSTree#removeMax()}
	 * to remove the maximum value in the tree.
	 */
	@Test
	public void testRemoveMax() {
		tree.add(four);
		tree.add(two);
		tree.add(six);
		tree.add(one);
		tree.add(five);

		int expected = 66;
		int expectedSize = 4;
		
		int actual = tree.removeMax().getElement();
		assertEquals("Failed to remove the maximum value.", expected, actual);
		assertEquals("Failed to update size.", expectedSize, tree.size());
		assertFalse(tree.contains(six));
	}
	
	/**
	 * Test method for 
	 * {@link implementations.BSTree#removeMax()}
	 * to return the maximum value in the tree.
	 */
	@Test
	public void testRemoveMaxNull() {
		assertNull("Failed to return maximum value.", tree.removeMax());
	}
}
