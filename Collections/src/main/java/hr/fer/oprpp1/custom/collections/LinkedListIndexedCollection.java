package hr.fer.oprpp1.custom.collections;

/**
 * 
 * @author TeaMadzarac
 * @version 17/10/2022
 *
 */
public class LinkedListIndexedCollection extends Collection{
	
/**
 * 
 * @author TeaMadzarac
 * @version 17/10/2022
 *
 */
	private static class ListNode {
		
		ListNode next;
		ListNode previous;
		Object value;
		
	}
	
	/**
	 * size of the collection
	 * first and last nodes that are in the collection
	 */
	private int size;
	private ListNode first;
	private ListNode last;
	
	/**
	 * constructor that generates an empty collection
	 */
	public LinkedListIndexedCollection()  {
		this.first = new ListNode();
		this.last = this.first;
		this.size = 0;
	}
	
	/**
	 * constructor that adds all elemments from other to this collection
	 * @param other
	 */
	public LinkedListIndexedCollection(Collection other) {
		this.first = new ListNode();
		this.last = new ListNode();
		this.last = this.first;
		this.size = 0;
		addAll(other);
	}

	/**
	 * returns size of the collection
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * adds given value in the collection, at the end
	 * if value is null it throws NullPointerException
	 */
	@Override
	public void add(Object value) {

		if(value.equals(null)) {
			throw new NullPointerException();
		}

		if(this.size == 0) {
			this.first.value = value;
			this.first.previous = null;
			this.first.next = null;
			this.last = this.first;
			this.size++;
		} else {
			ListNode newNode = new ListNode();
			newNode.value = value;
			newNode.previous = this.last;
			newNode.next = null;
			this.last.next = newNode;
			this.last = newNode;
			this.size++;
		}


	}

	/**
	 * checks if the collection contains given value
	 */
	@Override
	public boolean contains(Object value) {
		ListNode node = new ListNode();
		node = this.first;
		for(int i = 0; i < this.size; i++ ) {
			if(node.value.equals(value)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}


	/**
	 * removes object with a given value from the collection, if the object was successfully removed it returns true
	 */
	@Override
	public boolean remove(Object value) {
		int index;
		index = indexOf(value);
		if(index == -1) {
			return false;
		} else {
			remove(index);
			return true;
		}
	}


	/**
	 * returns an array with values copied from the collection
	 */
	@Override
	public Object[] toArray() {
		Object[] a = new Object[this.size];
		ListNode node = new ListNode();
		node = this.first;
		for(int i = 0; i < this.size; i++ ) {
			a[i] = node.value;
			node = node.next;
		}
		return a;
	}


	/**
	 * goes through all elements of the collection does a given command over them
	 */
	@Override
	public void forEach(Processor processor) {

		ListNode node = new ListNode();
		node = this.first;
		for(int i = 0; i < this.size; i++) {
			processor.process(node.value);
			node = node.next;
		}
	}


	/**
	 * returns an object that in a given position
	 * @param index position of the object we want
	 * @return
	 */
	public Object get(int index) {

		if(index < 0 || index > size -1) {
			throw new IndexOutOfBoundsException();
		}

		ListNode node = new ListNode();
		node = this.first;
		for(int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.value;
	}

	/**
	 * removes all objects from the collection
	 */
	public void clear() {
		this.first = new ListNode();
		this.last = this.first;
		this.size = 0;
	}

	/**
	 * /**
	 * inserts a given value into a giiven position of the collection
	 * @param value we want to add in the collection
	 * @param position where in the collection we want to add the value
	 */
	public void insert(Object value, int position) {

		if(position < 0 || position > this.size) {
			throw new IndexOutOfBoundsException();

		} else if (position == this.size) {
			add(value);

		} else if (position == 0) {
			ListNode insertNode = new ListNode();
			insertNode.value = value;
			insertNode.previous = null;
			this.first.previous = insertNode;
			insertNode.next = this.first;
			this.first = insertNode;
			this.size++;

		} else {
			ListNode node = new ListNode();
			node = this.first;
			for(int i = 0; i < position; i++) {
				node = node.next;
			}
			ListNode insertNode = new ListNode();
			insertNode.value = value;
			insertNode.previous = node.previous;
			insertNode.next = node;
			node.previous.next = insertNode;
			node.previous = insertNode;
			this.size++;
		}

	}

	/**
	 * returns index of the first instance of given object in a collection
	 * if collection doeasn't have a required value it returns -1
	 * @param value
	 * @return
	 */
	public int indexOf(Object value) {

		if(value == null) {
			return -1;
		}
		ListNode node = new ListNode();
		node = this.first;
		for(int i = 0; i < this.size -1; i++) {
			if(node.value.equals(value)) {
				return i;
			}
			node = node.next;
		}
		if(node.value.equals(value)) {
			return this.size -1;
		} else {
			return -1;
		}
	}

	/**
	 * removes object that is on the given position
	 * if the given index is out of bounds of the collection throws IndexOutOfBoundsException
	 * @param index position of the objectt we want to remove
	 */
	public void remove(int index) {

		if(index < 0 || index > this.size -1) {
			throw new IndexOutOfBoundsException();
		}

		if(this.size == 1) {
			 clear();

		} else if(index == this.size - 1) {
			ListNode nodeRem = new ListNode();
			nodeRem = this.last;
			nodeRem.previous.next = null;
			nodeRem.previous = null;
			this.size--;
		} else if (index == 0) {
			ListNode nodeRem = new ListNode();
			nodeRem = this.first;
			this.first = nodeRem.next;
			this.first.previous = null;
			nodeRem.next = null;
			this.size--;

		} else {
			ListNode nodeRem = new ListNode();
			nodeRem = this.first;
			for(int i = 0; i < index; i++) {
				nodeRem = nodeRem.next;
			}
			nodeRem.previous.next = nodeRem.next;
			nodeRem.next.previous = nodeRem.previous;
			this.size--;
		}

	}
	

}

