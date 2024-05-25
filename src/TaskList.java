//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Task List
// Course: CS 300 Spring 2024
//
// Author: Muhammad Naheel
// Email: naheel@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Abheesh Kumar
// Partner Email: akumar265@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: I was struggling with one of the methods so I asked my friend "Nischal Bista" who clarified the problem to me and I
// found and fixed the error myself.
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class models a list of Tasks. It implements the ListADT as a doubly linked list that stores
 * only elements of type Task.
 */
public class TaskList implements ListADT<Task> {

  private LinkedNode<Task> head; // Reference to the first node in this list (with respect to the
                                 // forward direction)
  private LinkedNode<Task> tail;// Reference to the last node in this list (with respect to the
                                // forward direction)
  private int size; // Total number of Task objects stored in this list

  public TaskList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * Checks if the list is empty
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the size of this list
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Adds newElement to the end (tail) of this list
   * 
   * @param newElement - element to be added to this list
   * @throws NullPointerException - if newElement is null
   */
  @Override
  public void add(Task newElement) {
    if (newElement == null) {
      throw new NullPointerException("Element cannot be null");
    }
    LinkedNode<Task> newNode = new LinkedNode<>(newElement);
    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else {
      tail.setNext(newNode);
      newNode.setPrev(tail);
      tail = newNode;
    }
    size++;
  }

  /**
   * Adds new Element to the head of this list
   * 
   * @param newElement - element to be added to this list
   * @throws NullPointerException - if newElement is null
   */
  @Override
  public void addFirst(Task newElement) {
    if (newElement == null) {
      throw new NullPointerException("Element cannot be null");
    }
    LinkedNode<Task> newNode = new LinkedNode<>(newElement);
    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.setNext(head);
      head.setPrev(newNode);
      head = newNode;
    }
    size++;
  }

  /**
   * Adds newElement at the given position index within this list
   * 
   * @param index      - index at which the specified element is to be inserted
   * @param newElement - element to be added to this list
   * @throws NullPointerException - if newElement is null IndexOutOfBoundsException - if the index
   *                              is out of range (index < 0 || index > size())
   */
  @Override
  public void add(int index, Task newElement) {
    if (newElement == null) {
      throw new NullPointerException("Element cannot be null");
    }
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("Index is out of range");
    }
    if (index == 0) {
      addFirst(newElement);
    } else if (index == size()) {
      add(newElement);
    } else {
      LinkedNode<Task> newNode = new LinkedNode<>(newElement);
      LinkedNode<Task> current = getNode(index);
      LinkedNode<Task> previous = current.getPrev();
      previous.setNext(newNode);
      newNode.setPrev(previous);
      newNode.setNext(current);
      current.setPrev(newNode);
      size++;
    }
  }

  /**
   * Returns the element at the specified position in this list.
   * 
   * @param index - index of the element to return
   * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index ≥ size())
   * @return the element at the specified position in this list
   */
  @Override
  public Task get(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Index is out of range");
    }
    LinkedNode<Task> node = getNode(index);
    return node.getData();
  }

  /**
   * one element e such that toFind.equals(e) == true.
   * 
   * @param toFind - element to find
   * @return true if this list contains at least one match with toFind
   */
  @Override
  public boolean contains(Task toFind) {
    LinkedNode<Task> current = head;
    while (current != null) {
      if (current.getData().equals(toFind)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  /**
   * Removes the element at the specified position in this list.
   * 
   * @param index - the index of the element to be removed
   * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index ≥ size())
   * @return the element that was removed from the list
   */
  @Override
  public Task remove(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Index is out of range");
    }
    if (index == 0) {
      return removeFirst();
    } else if (index == size() - 1) {
      return removeLast(); // No need to decrement size here
    } else {
      LinkedNode<Task> current = getNode(index);
      LinkedNode<Task> previous = current.getPrev();
      LinkedNode<Task> next = current.getNext();
      previous.setNext(next);
      next.setPrev(previous);
      size--;
      return current.getData();
    }
  }

  /**
   * Removes all of the elements from this list. The list will be empty after this call returns.
   */
  @Override
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Private method to get the node position
   * 
   * @param index index at which the specified element is to be inserted
   * @return current node
   */
  private LinkedNode<Task> getNode(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Index is out of range");
    }
    LinkedNode<Task> current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    return current;
  }

  /**
   * Removes first task from the TaskList.
   * 
   * @return new TaskList with first task removed.
   */
  private Task removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    Task removed = head.getData();
    if (size() == 1) {
      head = null;
      tail = null;
    } else {
      head = head.getNext();
      head.setPrev(null);
    }
    size--;
    return removed;
  }

  /**
   * Removes last task from the TaskList.
   * 
   * @return new TaskList with last task removed.
   */
  private Task removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    Task removed = tail.getData();
    if (size() == 1) {
      head = null;
      tail = null;
      size--; // Decrement size when removing the last element
    } else {
      tail = tail.getPrev();
      tail.setNext(null);
      size--;
    }
    return removed;
  }


  /**
   * Returns a String representation of the contents of this list traversed in the forward direction
   * separated by a newline.
   * 
   * @return a String representation of the connected nodes making this linked list traversed
   *         starting from the head of the list
   */
  protected String traverseForward() {
    StringBuilder result = new StringBuilder();
    LinkedNode<Task> current = head;
    while (current != null) {
      result.append(current.getData()).append("\n");
      current = current.getNext();
    }
    return result.toString();
  }

  /**
   * Returns a String representation of the contents of this list traversed in the backward
   * direction separated by a newline.
   * 
   * @return a String representation of the connected nodes making this linked list traversed
   *         starting from the tail of the list
   */
  protected String traverseBackward() {
    StringBuilder result = new StringBuilder();
    LinkedNode<Task> current = tail;
    while (current != null) {
      result.append(current.getData()).append("\n");
      current = current.getPrev();
    }
    return result.toString();
  }

  /**
   * Returns a String representation of this task list traversed in the forward direction from the
   * head to the tail of the list if forward is true. Otherwise (i.e. forward is false), this method
   * returns the string representation of this task list traversed in the backward direction (from
   * tail to head).
   * 
   * @param forward indicates the traversal direction of this task list: true if the traversal
   *                direction is forward, false otherwise.
   * @return a String representation of the tasks stored in this task list. The String
   *         representations of each Task are separated by a newline. If this list is empty, this
   *         method returns an empty string "".
   */
  public String traverse(boolean forward) {
    if (forward) {
      return traverseForward();
    } else {
      return traverseBackward();
    }
  }
}
