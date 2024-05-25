//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Sorted Task List
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
// Persons: I was struggling with one of the recursive methods as it was resulting in Stack Overflow
// error so I asked my friend "Nischal Bista" who clarified the problem to me and I
// found and fixed the error myself.
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////


import java.util.NoSuchElementException;

/**
 * This class models a sorted task list data structure that extends the doubly linked list of tasks
 * TaskList.
 */
public class SortedTaskList extends TaskList {

  /**
   * Adds a specific task to this sorted list of tasks (in the increasing order). More explicitly,
   * this method finds the correct location to insert this task into the list, according to the
   * priority of the existing tasks in the list, and adds it there
   * 
   * @param aTask - the task to be added to this sorted list
   * @throws NullPointerException - if aTask is null
   */
  @Override
  public void add(Task newElement) throws NullPointerException {
      if (newElement == null) {
          throw new NullPointerException("Task cannot be null");
      }

      // If the list is empty or the new task has higher priority than the last task
      if (isEmpty() || newElement.compareTo(get(size() - 1)) >= 0) {
          super.add(newElement);
          return;
      }

      // Find the correct position to insert the new task based on its priority
      int index = 0;
      while (index < size() && newElement.compareTo(get(index)) >= 0) {
          index++;
      }
      super.add(index, newElement);
  }




  /**
   * public void addFirst(Task aTask)Adds a task to the front of the list IF AND ONLY IF it is less than any other task in the list
   * @param aTask - task to be added to the head of this sorted list
   * @throws NullPointerException - if aTask is null
   */
  @Override
  public void addFirst(Task newElement) throws NullPointerException {
      if (newElement == null) {
          throw new NullPointerException("Task cannot be null");
      }
      if (!isEmpty() && newElement.compareTo(get(0)) > 0) {
          throw new IllegalStateException("Adding a task with a title greater than the head of the list");
      }
      super.addFirst(newElement);
  }

  /**
   * Adds aTask to the given index position within this sorted list IF AND ONLY IF index the correct position to aTask to be inserted in this sorted list.
   * @param index - index at which the specified task is to be inserted
   * @throws NullPointerException - if aTask is null
   * IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
   */
  @Override
  public void add(int index, Task newElement) throws NullPointerException, IndexOutOfBoundsException {
      if (newElement == null) {
          throw new NullPointerException("Task cannot be null");
      }
      if (index < 0 || index > size()) {
          throw new IndexOutOfBoundsException("Index is out of range");
      }
      if (index == 0 && (!isEmpty() && newElement.compareTo(get(0)) > 0)) {
          throw new IllegalStateException("Adding a task with a title greater than the head of the list");
      }
      super.add(index, newElement);
  }

  /**
   * Returns the task at index zero in this sorted task list
   * @return the task at index zero in this sorted task list
   */
  public Task peekBest() {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    return get(0);
  }

  /**
   * Removes and returns the task at index zero in this sorted task list
   * @return the task that was at index zero within this sorted task list
   */
  public Task removeBest() {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    return remove(0);
  }
}
