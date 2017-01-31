import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for Doubly Linked List
 *
 * @author Jordan Myers
 * @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest {
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new DoublyLinkedList<Integer>();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check if isEmpty() works
	 */
	@Test
	public void testIsEmpty() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		// test empty list
		assertTrue("Checking isEmpty() on an empty list. Expected result = true", testDLL.isEmpty());
		// test non-empty list with 1, 2 and 3 elements
		testDLL.insertBefore(0, 1);
		assertFalse("Checking isEmpty() on a list with 1 element. Expected result = false", testDLL.isEmpty());
		testDLL.insertBefore(1, 2);
		assertFalse("Checking isEmpty() on a list with 2 element. Expected result = false", testDLL.isEmpty());
		testDLL.insertBefore(2, 3);
		assertFalse("Checking isEmpty() on a list with 3 element. Expected result = false", testDLL.isEmpty());
	}
	
	/**
	 * Check if the insertBefore() works
	 */
	@Test
	public void testInsertBefore() {
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);

		// test inserting nodes at various positions in the middle of the list
		testDLL.insertBefore(0, 4);
		assertEquals("Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3",
				testDLL.toString());
		testDLL.insertBefore(1, 5);
		assertEquals("Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3",
				testDLL.toString());
		testDLL.insertBefore(2, 6);
		assertEquals("Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3",
				testDLL.toString());
		testDLL.insertBefore(testDLL.INSERT_AT_TAIL, 7);
		assertEquals("Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3,7",
				testDLL.toString());
		testDLL.deleteAt(6);

		// test inserting nodes when the position is outside the range of the
		// list, i.e. position < 0 or position > (numOfElems)
		testDLL.insertBefore(-1, 7);
		assertEquals(
				"Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list",
				"7,4,5,6,1,2,3", testDLL.toString());
		testDLL.insertBefore(7, 8);
		assertEquals(
				"Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list",
				"7,4,5,6,1,2,3,8", testDLL.toString());
		testDLL.insertBefore(700, 9);
		assertEquals(
				"Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list",
				"7,4,5,6,1,2,3,8,9", testDLL.toString());

		// test empty list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position 0 - expected the element at the head of the list",
				"1", testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(10, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position 10 - expected the element at the head of the list",
				"1", testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(-10, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position -10 - expected the element at the head of the list",
				"1", testDLL.toString());
	}

	/**
	 * Check if get() works
	 */
	@Test
	public void testGet() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);

		// test retrieving values from the head, middle and tail of a list of 3
		// elements
		assertEquals(
				"Checking get() for a value at the head of a list; position 0 in a list of 3 elements. Expected value = 1 ",
				"1", testDLL.get(0).toString());
		assertEquals(
				"Checking get() for a value in the middle of a list; position 1 in a list of 3 elements. Expected value = 2 ",
				"2", testDLL.get(1).toString());
		assertEquals(
				"Checking get() for a value at the tail of a list; position 2 in a list of 3 elements. Expected value = 3 ",
				"3", testDLL.get(2).toString());

		// test retrieving a value when the position given is outside the range
		// of the list, i.e. position < 0 or position > (numOfElems)
		assertNull("Checking get() when the position passed is < 0. Expected value = null", testDLL.get(-1));
		assertNull(
				"Checking get() when the position passed is > than the number of elements in the list. Expected value = null",
				testDLL.get(4));

		// test retrieving a value when the list is empty
		testDLL = new DoublyLinkedList<Integer>();
		assertNull("Checking get() when the list is empty", testDLL.get(0));

	}

	/**
	 * Check if deleteAt() works
	 */
	@Test
	public void testDeleteAt() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);
		String originalList = testDLL.toString();

		// test deleting values at the head, middle and end of a list containing
		// 3 elements
		assertTrue("Checking deleteAt() for a value at the head of the list. Expected result = true",
				testDLL.deleteAt(0));
		testDLL.insertBefore(0, 1);
		assertTrue("Checking deleteAt() for a value in the middle of the list. Expected result = true",
				testDLL.deleteAt(1));
		testDLL.insertBefore(1, 2);
		assertTrue("Checking deleteAt() for a value at the tail of the list. Expected result = true",
				testDLL.deleteAt(2));
		testDLL.insertBefore(2, 3);
		// test list is still in the same state
		assertEquals("Checking that the list is the same as before values were deleted and re-inserted", originalList,
				testDLL.toString());

		// test deleting values when the position given is outside the range
		// of the list, i.e. position < 0 or position > (numOfElems)
		assertFalse("Checking deleteAt() when the position passed is < 0. Expected result = false",
				testDLL.deleteAt(-1));
		assertFalse(
				"Checking deleteAt() when the position passed is > than the number of elements in the list. Expected result = false",
				testDLL.deleteAt(4));

		// test deleting a value when the list is empty
		testDLL = new DoublyLinkedList<Integer>();
		assertFalse("Checking deleteAt() when the list is empty. Expected result = false", testDLL.deleteAt(0));
	}

	/**
	 * Check if reverse() works
	 */
	@Test
	public void testReverse() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);

		// test a list containing 3 elements
		testDLL.reverse();
		assertEquals("Checking reverse() on a list with 3 elements - expected \"3,2,1\" (from \"1,2,3\"", "3,2,1",
				testDLL.toString());

		// test an empty list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.reverse();
		assertEquals("Checking reverse() on an empty list of elements - expected an empty string", "",
				testDLL.toString());
		
		// test for a 1 element list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.reverse();
		assertEquals("Checking reverse() on a list with 1 element - expected \"1\"", "1", testDLL.toString());
		
	}

	/**
	 * Check if push() and pop() work
	 */
	@Test
	public void testPushAndPop() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(1);
		testDLL.push(2);
		testDLL.push(3);

		// test values are in the correct order when pushed on together and then
		// popped off, i.e. LIFO
		assertEquals(
				"Checking the value at the top of the stack is equal to the last value pushed on. Expected result = 3",
				"3", testDLL.pop().toString());
		assertEquals(
				"Checking the value at the top of the stack is equal to the last value pushed on. Expected result = 2",
				"2", testDLL.pop().toString());
		assertEquals(
				"Checking the value at the top of the stack is equal to the last value pushed on. Expected result = 1",
				"1", testDLL.pop().toString());

		// test pop() when the list is empty
		assertNull("Checking pop() when the list ('stack') is empty. Expected result = null", testDLL.pop());
	}

	/**
	 * Check if enqueue() and dequeue() work
	 */
	@Test
	public void testEnqueueAndDequeue() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.enqueue(1);
		testDLL.enqueue(2);
		testDLL.enqueue(3);

		// test values are in the correct order when enqueued together and then
		// dequeued, i.e. FIFO
//		System.out.println(testDLL.toString());
		assertEquals("Checking the value dequeued is equal to the first value enqueued (that is still in the list). Expected value = 1", "1", testDLL.dequeue().toString());
//		System.out.println(testDLL.toString());
		assertEquals("Checking the value dequeued is equal to the first value enqueued (that is still in the list). Expected value = 2", "2", testDLL.dequeue().toString());
//		System.out.println(testDLL.toString());
		assertEquals("Checking the value dequeued is equal to the first value enqueued (that is still in the list). Expected value = 3", "3", testDLL.dequeue().toString());
		
		// test dequeue() when the list is empty
		assertNull("Checking dequeue() when the list('queue') is empty. Expected result = null", testDLL.dequeue());
	}
}
