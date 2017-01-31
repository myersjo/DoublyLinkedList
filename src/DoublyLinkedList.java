import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.lang.Integer;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Jordan Myers
 *  @version 13/10/16 18:15
 */

/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * 
 * @param <T>
 *            This is a type parameter. T is used as a class name in the
 *            definition of this class.
 *
 *            When creating a new DoublyLinkedList, T should be instantiated
 *            with an actual class name that extends the class Comparable. Such
 *            classes include String and Integer.
 *
 *            For example to create a new DoublyLinkedList class containing
 *            String data: DoublyLinkedList<String> myStringList = new
 *            DoublyLinkedList<String>();
 *
 *            The class offers a toString() method which returns a
 *            comma-separated sting of all elements in the data structure.
 * 
 *            This is a bare minimum class you would need to completely
 *            implement. You can add additional methods to support your code.
 *            Each method will need to be tested by your jUnit tests -- for
 *            simplicity in jUnit testing introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>> {

	public final int INSERT_AT_TAIL = Integer.MAX_VALUE;

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode {
		public final T data; // this field should never be updated. It gets its
								// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * 
		 * @param theData
		 *            : data of type T, to be stored in the node
		 * @param prevNode
		 *            : the previous Node in the Doubly Linked List
		 * @param nextNode
		 *            : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) {
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor
	 * 
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * 
	 * @return true if list is empty, and false otherwise
	 *
	 *         Worst-case asymptotic runtime cost: Theta(1)
	 *
	 *         Justification: This method simply checks if the head and tail
	 *         references are null. This check runs in Theta(1) asymptotic time
	 *         - a constant time operation.
	 */
	public boolean isEmpty() {
		//return (head == null && tail == null) ? true : false;
		return (head == null);
	}

	/**
	 * Inserts an element in the doubly linked list
	 * 
	 * @param pos
	 *            : The integer location at which the new data should be
	 *            inserted in the list. We assume that the first position in the
	 *            list is 0 (zero). If pos is less than 0 then add to the head
	 *            of the list. If pos is greater or equal to the size of the
	 *            list then add the element at the end of the list.
	 * @param data
	 *            : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 *         Worst-case asymptotic runtime cost: Theta(n)
	 *
	 *         Justification: We assume all of the operations in the while-loop
	 *         execute in constant time - Theta(1). Therefore, every iteration
	 *         of the while-loop will have a cost of Theta(1). In the worst
	 *         case, the new node will be created at the end of the list. Thus,
	 *         if the list has n elements, the while-loop will iterate over all
	 *         n elements. The total cost of this method in the worst case will
	 *         be n*Theta(1) = Theta(n)
	 */
	public void insertBefore(int pos, T data) {
		if (isEmpty()) {
			DLLNode newNode = new DLLNode(data, null, null);
			head = newNode;
			tail = newNode;
		} else {
			if (pos <= 0) {
				DLLNode newNode = new DLLNode(data, null, head);
				head.prev = newNode;
				head = newNode;
			} else {
				DLLNode currentNode = head;
				int currentNodePos = 0;
//				boolean inserted = false;

				while (currentNodePos <= pos) {
					if (currentNodePos == pos) {
						DLLNode newNode = new DLLNode(data, currentNode.prev, currentNode);
						currentNode.prev.next = newNode;
						currentNode.prev = newNode;
//						inserted = true;
						return;

						// end of list reached; not yet inserted or pos is last
						// possible number, insert at end
					} else if (currentNode == tail || pos == INSERT_AT_TAIL) {
						if (pos == INSERT_AT_TAIL)
							currentNode = tail;
						DLLNode newNode = new DLLNode(data, currentNode, null);
						currentNode.next = newNode;
						tail = newNode;
//						inserted = true;
						return;
					} else {
						currentNode = currentNode.next;
						currentNodePos++;
					}
				}
			}
		}
		return;
	}

	/**
	 * Returns the data stored at a particular position
	 * 
	 * @param pos
	 *            : the position
	 * @return the data at pos, if pos is within the bounds of the list, and
	 *         null otherwise.
	 *
	 *         Worst-case asymptotic runtime cost: Theta(n)
	 *
	 *         Justification: As with insertBefore() above, we assume all of the
	 *         operations in the while-loop execute in constant time - Theta(1).
	 *         Therefore, every iteration of the while-loop will have a cost of
	 *         Theta(1). In the worst case, the node we are looking for will be
	 *         at the end of the list. Thus, if the list has n elements, the
	 *         while-loop will iterate over all n elements. The total cost of
	 *         this method in the worst case will be n*Theta(1) = Theta(n)
	 */
	public T get(int pos) {
		if (isEmpty() || pos < 0) {
			return null;
		} else {
			T data = null;
			DLLNode currentNode = head;
			int currentNodePos = 0;
//			boolean finished = false;

			while (currentNodePos <= pos && currentNode != null) {
				if (currentNodePos == pos) {
					data = currentNode.data;
//					finished = true;
					return data;

					// \/ else if (no match found but end of list reached)
//				} else if (currentNode == tail) { // (&& currentNodePos != pos)
//					data = null;
////					finished = true;
//					return data;
				} else {
					currentNode = currentNode.next;
					currentNodePos++;
				}
			}
			return data;
		}
	}

	/**
	 * Deletes the element of the list at position pos. First element in the
	 * list has position 0. If pos points outside the elements of the list then
	 * no modification happens to the list.
	 * 
	 * @param pos
	 *            : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been
	 *         modified.
	 *
	 *         Worst-case asymptotic runtime cost: Theta(n)
	 *
	 *         Justification: As previously, we assume all of the operations in
	 *         the while-loop execute in constant time - Theta(1). Therefore,
	 *         every iteration of the while-loop will have a cost of Theta(1).
	 *         In the worst case, the node to be deleted will be at the end of
	 *         the list. Thus, if the list has n elements, the while-loop will
	 *         iterate over all n elements. The total cost of this method in the
	 *         worst case will be n*Theta(1) = Theta(n)
	 */
	public boolean deleteAt(int pos) {
		if (isEmpty() || pos < 0) {
			return false;
		} else {
			DLLNode currentNode = head;
			int currentNodePos = 0;
//			boolean finished = false;
			boolean elementDeleted = false;

			while (currentNodePos <= pos && currentNode != null) {
				if (currentNodePos == pos) {
					if (currentNode == head) {
						if (currentNode.next != null) {
							currentNode.next.prev = null;
							head = currentNode.next;
						} else { // if (the last node is deleted)
							head = null;
							tail = null;
						}
					} else if (currentNode == tail) {
						currentNode.prev.next = null;
						tail = currentNode.prev;
					} else {
						currentNode.prev.next = currentNode.next;
						currentNode.next.prev = currentNode.prev;
					}
					elementDeleted = true;
//					finished = true;
					return elementDeleted;
//				} else if (currentNode == tail) {
//					elementDeleted = false;
////					finished = true;
//					return elementDeleted;
				} else {
					currentNode = currentNode.next;
					currentNodePos++;
				}
			}
			return elementDeleted;
		}
	}

	/**
	 * Reverses the list. If the list contains "A", "B", "C", "D" before the
	 * method is called Then it should contain "D", "C", "B", "A" after it
	 * returns.
	 *
	 * Worst-case asymptotic runtime cost: Theta(n)
	 *
	 * Justification: Once again, we assume all of the operations in the
	 * while-loop execute in constant time - Theta(1). Therefore, every
	 * iteration of the while-loop will have a cost of Theta(1). If the list has
	 * n elements, the while-loop will always iterate over all n elements. Thus,
	 * the total cost of this method will be n*Theta(1) = Theta(n)
	 */
	public void reverse() {
		if (isEmpty())
			return;
		else if (head == tail)
			return;
		else {
			DLLNode currentNode = head;
			boolean finished = false;
			while (!finished) {
				DLLNode tempNode = new DLLNode(currentNode.data, currentNode.prev, currentNode.next);
				if (currentNode == head) {
					currentNode.prev = tempNode.next;
					currentNode.next = null;
					currentNode = tempNode.next;
				} else if (currentNode == tail) {
					currentNode.next = tempNode.prev;
					currentNode.prev = null;
					tail = head;
					head = currentNode;
					finished = true;
				} else {
					currentNode.prev = tempNode.next;
					currentNode.next = tempNode.prev;
					currentNode = tempNode.next;
				}
			}
		}
		return;
	}

	/*----------------------- STACK */
	/**
	 * This method should behave like the usual push method of a Stack ADT. If
	 * only the push and pop methods are called the data structure should behave
	 * like a stack. How exactly this will be represented in the Doubly Linked
	 * List is up to the programmer.
	 * 
	 * @param item
	 *            : the item to push on the stack
	 *
	 *            Worst-case asymptotic runtime cost: Theta(1)
	 *
	 *            Justification: The best case running time for the insertBefore
	 *            method is Theta(1). We get this when a node is created at the
	 *            head of the list. This push() method always inserts the item
	 *            at the head, and therefore the insertBefore() method has a run
	 *            time of Theta(1). This is the only operation in the push()
	 *            method and so, push() has a total cost of Theta(1).
	 */
	// pushes/adds to the head of the list
	public void push(T item) {
		insertBefore(0, item);
	}

	/**
	 * This method should behave like the usual pop method of a Stack ADT. If
	 * only the push and pop methods are called the data structure should behave
	 * like a stack. How exactly this will be represented in the Doubly Linked
	 * List is up to the programmer.
	 * 
	 * @return the last item inserted with a push; or null when the list is
	 *         empty.
	 *
	 *         Worst-case asymptotic runtime cost: Theta(1)
	 *
	 *         Justification: Here we have a similar situation to that in the
	 *         push() method above. The best case running time for the
	 *         deleteAt() method is Theta(1). We get this when a node is deleted
	 *         at the head of the list. This pop() method always deletes the
	 *         item at the head, and therefore the deleteAt() method has a run
	 *         time of Theta(1). We assume the other operations in this method
	 *         have a constant run time and so, push() has a total cost of
	 *         Theta(1).
	 */
	// pops/removes from the head of the list
	public T pop() {
		T data = null;
		if (!isEmpty()) {
			data = head.data;
			deleteAt(0);
		}

		return data;
	}

	/*----------------------- QUEUE */

	/**
	 * This method should behave like the usual enqueue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure
	 * should behave like a FIFO queue. How exactly this will be represented in
	 * the Doubly Linked List is up to the programmer.
	 * 
	 * @param item
	 *            : the item to be enqueued to the stack
	 *
	 *            Worst-case asymptotic runtime cost: Theta(1)
	 *
	 *            Justification: For the insertBefore() function, if the
	 *            position given is equal to Integer.MAX_VALUE (i.e.
	 *            INSERT_AT_TAIL), the node is always inserted at the end of the
	 *            list. We assume the other operations in the insertBefore()
	 *            method have a constant run time and so, enqueue() has a total
	 *            cost of Theta(1).
	 */
	// adds to the tail of the list
	public void enqueue(T item) {
		insertBefore(INSERT_AT_TAIL, item);
	}

	/**
	 * This method should behave like the usual dequeue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure
	 * should behave like a FIFO queue. How exactly this will be represented in
	 * the Doubly Linked List is up to the programmer.
	 * 
	 * @return the earliest item inserted with a push; or null when the list is
	 *         empty.
	 *
	 *         Worst-case asymptotic runtime cost: Theta(1)
	 *
	 *         Justification: This works in exactly the same way as the pop()
	 *         operation above, and so the justification is the same. The best
	 *         case running time for the deleteAt() method is Theta(1). We get
	 *         this when a node is deleted at the head of the list. This pop()
	 *         method always deletes the item at the head, and therefore the
	 *         deleteAt() method has a run time of Theta(1). We assume the other
	 *         operations in this method have a constant run time and so, push()
	 *         has a total cost of Theta(1).
	 */
	// removes from the head of the list
	public T dequeue() {
		T data = null;
		if (!isEmpty()) {
			data = head.data;
			deleteAt(0);
		}
		return data;
	}

	/**
	 * @return a string with the elements of the list as a comma-separated list,
	 *         from beginning to end
	 *
	 *         Worst-case asymptotic runtime cost: Theta(n)
	 *
	 *         Justification: We know from the Java documentation that
	 *         StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *         We assume all other method calls here (e.g., the iterator methods
	 *         above, and the toString method) will execute in Theta(1) time.
	 *         Thus, every one iteration of the for-loop will have cost
	 *         Theta(1). Suppose the doubly-linked list has 'n' elements. The
	 *         for-loop will always iterate over all n elements of the list, and
	 *         therefore the total cost of this method will be n*Theta(1) =
	 *         Theta(n).
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next) {
			if (!isFirst) {
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}

}
