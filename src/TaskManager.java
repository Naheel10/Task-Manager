//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Task Manager
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
// Persons: none
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////


/**
 * This class models TaskManager objects. A TaskManager has a to do list of incomplete tasks, and a
 * list of completed tasks. This class allows the user to manage the tasks in the to do list.
 */
public class TaskManager {


  /**
   * to do list of incomplete tasks
   */
  protected TaskList toDoList; // list of incomplete tasks

  /**
   * TaskList of completed tasks
   */
  protected TaskList completedTasks; // list of completed tasks

  /**
   * Constructs a TaskManager with empty to-do and completed task lists.
   */
  public TaskManager() {
    toDoList = new TaskList();
    completedTasks = new TaskList();
  }


  /**
   * Appends a task to the end of the to-do list.
   *
   * @param task The task to be added to the to-do list.
   */
  public void appendTask(Task task) {
    toDoList.add(task);
  }

  /**
   * Moves a specified task to the top (head) of the to-do list.
   *
   * @param indexTaskToMove The index of the task to move to the top (head) of the to-do list.
   * @throws IndexOutOfBoundsException if indexTaskToMove is out of bounds with respect to the to-do
   *                                   list valid range of indexes.
   */
  public void movetoTop(int indexTaskToMove) {
    Task task = toDoList.remove(indexTaskToMove);
    toDoList.addFirst(task);
  }

  /**
   * Moves a specified task to the bottom (tail) of the to-do list.
   *
   * @param indexTaskToMove The index of the task to move to the bottom (tail) of the to-do list.
   * @throws IndexOutOfBoundsException if indexTaskToMove is out of bounds with respect to the to-do
   *                                   list valid range of indexes.
   */
  public void moveToBottom(int indexTaskToMove) {
    Task task = toDoList.remove(indexTaskToMove);
    toDoList.add(task);
  }

  /**
   * Moves a specified task to a position before another task in the to-do list given their indexes.
   *
   * @param indexTaskToMove The index of the task to move within the to-do list.
   * @param indexOtherTask  The index of the other task before which the first task will be moved.
   * @return true if the task was successfully moved; false otherwise.
   *
   */
  public boolean moveBeforeOtherTask(int indexTaskToMove, int indexOtherTask) {
    if (indexTaskToMove < 0 || indexTaskToMove >= toDoList.size() || indexOtherTask < 0 || indexOtherTask >= toDoList.size()) {
        return false; 
    }
    if (indexTaskToMove == indexOtherTask) {
        return true;
    }
    Task taskToMove = toDoList.remove(indexTaskToMove);
    if (indexTaskToMove < indexOtherTask) {
        toDoList.add(indexOtherTask - 1, taskToMove);
    } else {
        toDoList.add(indexOtherTask, taskToMove);
    }
    return true;
}

  /**
   * Moves a specified task to a position after another task in the to-do list, given their indexes.
   *
   * @param indexTaskToMove The index of the task to move.
   * @param indexOtherTask  The index of the other task after which the first task will be moved.
   * @return true if the task was successfully moved; false otherwise.
   */
  public boolean moveAfterOtherTask(int indexTaskToMove, int indexOtherTask) {
    if (indexTaskToMove == indexOtherTask || indexTaskToMove == indexOtherTask + 1) {
      return false;
    }
    Task task = toDoList.remove(indexTaskToMove);
    toDoList.add(indexOtherTask + 1, task);
    return true;
  }
  /**
   * Removes a task from the to-do list based on its index.
   *
   * @param index The index of the task to remove.
   * @return true if the task was successfully removed; false if the index was invalid.
   */
  public boolean removeTask(int index) {
    try {
      toDoList.remove(index);
      return true;
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
  }

  /**
   * Marks the task at index in the to-do list as complete and moves it to the completed tasks list.
   * The completed task can be added to the beginning OR the end of the completedTasks list.
   *
   * @param index The index of the task to mark as complete.
   * @return true if the task was successfully marked as complete and moved; false if the index was
   *         invalid.
   */
  public boolean completeTask(int index) {
    try {
      Task completedTask = toDoList.get(index);
      toDoList.remove(index);
      completedTasks.add(completedTask);
      return true;
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
  }

  /**
   * Returns a new TaskList that contains all tasks sorted in the increasing order
   * 
   * @return a new TaskList that contains all tasks sorted in the increasing order
   */
  public TaskList sortTasks() {
    TaskList sortedList = new TaskList();

    // Add all tasks from the to-do list to the sorted list
    for (int i = 0; i < toDoList.size(); i++) {
        sortedList.add(toDoList.get(i));
    }

    // Bubble sort algorithm to sort the tasks by title
    int n = sortedList.size();
    for (int i = 0; i < n-1; i++) {
        for (int j = 0; j < n-i-1; j++) {
            Task task1 = sortedList.get(j);
            Task task2 = sortedList.get(j+1);
            if (task1.compareTo(task2) > 0) {
                // Swap tasks
                sortedList.add(j, task2);
                sortedList.remove(j+2);
            }
        }
    }

    return sortedList;
}

}
