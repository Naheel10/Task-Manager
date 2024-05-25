//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Task Manager Tester
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
 * This class tests the TaskManager, TaskList, and SortedTaskList classes.
 */
public class TaskManagerTester {

    /**
     * Checks the correctness of the implementation of the method compareTo() defined in the Task
     * class. Consider different test scenarios including each of the SortingOrder values: TITLE and
     * PRIORITY
     * Test scenarios: <BR>
     * aTask and anotherTask references any Task objects you can create.<BR>
     * 1. aTask.compareTo(anotherTask) is expected to return 0 if they are equal with respect to the
     * comparison criteria. <BR>
     * 2. aTask.compareTo(aTask) is expected to return 0 <BR>
     * 3. aTask.compareTo(anotherTask) is expected to return a negative integer (less than zero) if
     * aTask is less than another Task with respect to the comparison criteria. <BR>
     * 4.aTask.compareTo(anotherTask) is expected to return a positive integer (greater than zero) if
     * aTask is greater than another Task with respect to the comparison criteria.
     *
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
  public static boolean testTaskCompareTo() {
    
    Task task1 = new Task("gym", "Do chest exercises", true); // Low priority
    Task task2 = new Task("Math discussion", "Go to math discussion for quiz", false);  // High priority
    Task task3 = new Task("gym", "Do chest exercises", true);  // High priority, same title as task1

    // Tests sorting order to TITLE and compare
    Task.setSortingOrderByTitle();
    if (task1.compareTo(task3) != 0) {
        System.out.println("Title comparison failed.");
        return false;
    }

    // Test sorting order to PRIORITY and compare
    Task.setSortingOrderByPriorityLevel();
    if (task1.compareTo(task2) >= 0) { 
        System.out.println("Priority comparison failed.");
        return false;
    }

    // Tests equality using the comparison criteria
    if (task1.compareTo(task3) != 0) {
        System.out.println("Equal title comparison failed.");
        return false;
    }
    
    // Tests self-comparison using the comparison criteria
    if (task1.compareTo(task1) != 0) {
        System.out.println("Self comparison title comparison failed.");
        return false;
    }

    return true;
}



    /**
     * Checks the correctness of the implementation of the equals() method defined in the Task clas.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testTaskEquals() {
        Task task1 = new Task("Gym", "Do chest exercises", true);
        Task task2 = new Task("gym", "Do shoulder and bicep exercises", false);

        // Comparing titles case-insensitively
        if (!task1.equals(task2)) {
            System.out.println("Tasks with same title should be equal.");
            return false;
        }

        Task task3 = new Task("Math discussion", "Go to math discussion for quiz", true);
        if (task1.equals(task3)) {
            System.out.println("Tasks with different title should not be equal.");
            return false;
        }

        return true;
    }


    /**
     * Tests the add(), isEmpty(), and size() methods of the TaskList class.
     * Test scenarios: <BR>
     * 1. Create a new empty TaskList and verify that isEmpty() returns true.<BR>
     * 2. Add a few tasks to the end of the TaskList and verify that isEmpty() returns false.<BR>
     * 3. Verify that size() returns the expected size after adding each Task.
     *
     * @return true if all test scenarios pass, false otherwise.
     *
     */
    public static boolean testAddIsEmptySize() {
        TaskList list = new TaskList();

        // Checks if list is initially empty
        if (!list.isEmpty()) {
            System.out.println("New list should be empty.");
            return false;
        }

        // Tests add a task and check if list updates correctly
        Task task1 = new Task("Gym", "Do chest exercises", true);
        list.add(task1);
        if (list.isEmpty() || list.size() != 1) {
            System.out.println("The list should not be empty after adding this task and size should increment to 1.");
            return false;
        }

        // Tests another task to check if size increments correctly
        Task task2 = new Task("Math discussion", "Go to math discussion for quiz", false);
        list.add(task2);
        if (list.size() != 2) {
            System.out.println("After adding another task list size should increment to 2.");
            return false;
        }

        return true;
    }


    /**
     * Tests the addFirst(), and add(index, element) methods of the TaskList class.
     * Test scenarios: <BR>
     * 1. Test adding elements to an empty TaskList <BR>
     * 2. Test adding elements to the beginning, middle, and end of a non-empty TaskList.
     *
     * @return true if all test scenarios pass, false otherwise.
     *
     */
    public static boolean testAddToTaskList() {
        TaskList taskList = new TaskList();

        Task task1 = new Task("Gym", "Do chest exercises", false);
        Task task2 = new Task("Math discussion", "Go to math discussion for quiz", true);
        Task task3 = new Task("Soccer", "Watch the champions league soccer game", false);
        Task task4 = new Task("Laundry", "Do laundy at night", true);

        // Test 1: Adding element to empty TaskList
        taskList.addFirst(task1);
        if (taskList.isEmpty() || taskList.size() != 1) {
            System.out.println("Could not add to empty tasklist");
            return false;
        }

        // Test 2: Adding element to beginning of a non-empty TaskList
        taskList.addFirst(task2);
        if (taskList.size() != 2) {
            System.out.println("Could not add to begininng of tasklist");
            return false;
        }

        // Test 3: Assuming 'add' appends to the end if no index is specified. This adds to the "end."
        taskList.add(task3);
        if (taskList.size() != 3) {
            System.out.println("Could not add to end of tasklist.");
            return false;
        }

        // Test 4: Test 'add(index, element)' by adding another element, expecting the list to grow.
        taskList.add(1, task4); // Attempt to add task4 at index 1
        if (taskList.size() != 4) {
            System.out.println("Could not insert task to specific position of tasklist");
            return false;
        }

        return true;
    }


    /**
     * Tests and remove(index) and clear() methods of the TaskList class.
     * Test scenarios: <BR>
     * 1. Test removing elements from various positions in the TaskList using remove(index). <BR>
     * 2. Test removing elements from an empty TaskList or at invalid indices. <BR>
     * 3. Test clear() method and verify that the TaskList is empty after calling it.
     *
     * @return true if all test scenarios pass, false otherwise.
     *
     */
    public static boolean testRemoveClear() {
      TaskList tasks = new TaskList();

      Task firstTask = new Task("Gym", "Do chest exercises", true);
      Task secondTask = new Task("Soccer", "Watch champions league soccer game", false);
      Task thirdTask = new Task("Math discussion", "Go to math discussion for quiz", true);

      // Adding tasks to the list
      tasks.add(firstTask);
      tasks.add(secondTask);
      tasks.add(thirdTask);

      // testing removing from various positions
      tasks.remove(1);
      if (tasks.size() != 2) {
          System.out.println("expecting size is 2 after removal");
          return false;
      }

      tasks.remove(0); // Remove the first task
      if (tasks.size() != 1) {
          System.out.println("expected size was 1 after removal");
          return false;
      }

      tasks.remove(0);
      if (!tasks.isEmpty()) {
          System.out.println("Expected an empty list after removing the last remaining task.");
          return false;
      }

      //testing removing from an empty list
      try {
          tasks.remove(0);
          System.out.println("Expected an index exception when removing from empty list");
          return false;
      } catch (IndexOutOfBoundsException e) {
          // this is the expected bahavior so catching it
      }

      tasks.add(firstTask);
      tasks.add(secondTask);

      // test clear method
      tasks.clear();
      if (!tasks.isEmpty()) {
          System.out.println("expected the list to be empty");
          return false;
      }

      return true;
  }


    public static boolean testAddToSortedTaskList() {
      SortedTaskList sortedTaskList = new SortedTaskList();

      Task taskHighPriority = new Task("A Task", "High priority", true);
      Task taskMidPriority = new Task("M Task", "Mid priority", true);
      Task taskLowPriority = new Task("Z Task", "Low priority", false);

  
      sortedTaskList.add(taskMidPriority); 
      sortedTaskList.add(taskLowPriority); 
      sortedTaskList.add(taskHighPriority); 

      // Verifying if the list has the correct size after additions
      if (sortedTaskList.size() != 3) {
          System.out.println("Tasks are not being added correctly to the SortedTaskList.");
          return false;
      }

      // Assuming a method exists to get tasks by index to verify the order
      if (!sortedTaskList.get(0).equals(taskHighPriority) ||
              !sortedTaskList.get(1).equals(taskMidPriority) ||
              !sortedTaskList.get(2).equals(taskLowPriority)) {
          System.out.println("Tasks are not sorted correctly in the SortedTaskList.");
          return false;
      }

      return true;
  }



    private static SortedTaskList getSortedTaskList() {
        SortedTaskList sortedTaskList = new SortedTaskList();

  
        Task highPriorityTask = new Task("Math discussion", "Watch the champions league soccer game", true);
        Task lowPriorityTask = new Task("Soccer", "Watch the champions league soccer game", false);
        Task midPriorityTask = new Task("Gym", "Do chest exercises", true);

        // Adding tasks out of natural sort order to verify sorting after addition
        sortedTaskList.add(midPriorityTask);
        sortedTaskList.add(lowPriorityTask);  
        sortedTaskList.add(highPriorityTask); 
        return sortedTaskList;
    }


    /**
     * Tests the appendTask() method of the TaskManager class.
     * Test scenarios: <BR>
     * 1. Test appending a task to an empty TaskManager. <BR>
     * 2. Test appending multiple tasks to the TaskManager.
     *
     * @return true if all test scenarios pass, false otherwise.
     *
     */
    public static boolean testAppendTask() {
        TaskManager taskManager = new TaskManager();

        // Test 1: Appending a task to an empty TaskManager
        Task task1 = new Task("Gym", "Do chest exercises", true);
        taskManager.appendTask(task1);
        // Check if the TaskManager's to-do list is no longer empty and has exactly one task
        if (taskManager.toDoList.isEmpty() || taskManager.toDoList.size() != 1) {
            System.out.println("Could not append task to empty TaskManager.");
            return false;
        }

        // Test 2: Appending multiple tasks to the TaskManager
        Task task2 = new Task("Math discussion", "Go to math discussion for quiz", false);
        Task task3 = new Task("Soccer", "Watch the champions league soccer game", true);
        taskManager.appendTask(task2);
        taskManager.appendTask(task3);
        // Verifying if the TaskManager's to-do list size increases to 3
        if (taskManager.toDoList.size() != 3) {
            System.out.println("Could not append multiple tasks to TaskManager.");
            return false;
        }

        return true;
    }

    /**
     * Tests the moveToTop(), moveToBottom(), moveBeforeOtherTask(), and moveAfterOtherTask() methods
     * of the TaskManager class.
     *
     * Test scenarios: <BR>
     * 1. Test moving tasks to various positions in the toDoList and ensure the move methods have no
     * impact on the contents of the completedTasks list. <BR>
     * 2. Test moving tasks relative to other tasks (before/after).
     *
     * @return true if all test scenarios pass, false otherwise.
     *
     */
    public static boolean testMoveTasks() {
        TaskManager taskManager = getTaskManager();
        if (taskManager.toDoList.size() != 3) {
            System.out.println("Could not move task to top.");
            return false;
        }

        taskManager.moveToBottom(0); // Move the first task to the bottom
        if (taskManager.toDoList.size() != 3) {
            System.out.println("Could not move task to bottom.");
            return false;
        }

        // Test moving tasks after another tasks (before/after)
        taskManager.moveBeforeOtherTask(2, 1); 
        if (taskManager.toDoList.size() != 3) {
            System.out.println("Could not move task before another task.");
            return false;
        }

        // Test to move the first task after the second task
        taskManager.moveAfterOtherTask(0, 1); 
        if (taskManager.toDoList.size() != 3) {
            System.out.println("Could not move task after another task.");
            return false;
        }

        // verifying if the completedTasks list updates correctly
        taskManager.completeTask(0);
        if (taskManager.toDoList.size() != 2 || taskManager.completedTasks.size() != 1) {
            System.out.println("Could not move completed task to the completedTasks list.");
            return false;
        }

        return true;
    }

    private static TaskManager getTaskManager() {
        TaskManager taskManager = new TaskManager();

        // Add tasks to the TaskManager's to-do list
        Task task1 = new Task("Math discussion", "Go to math discussion for quiz", true);
        Task task2 = new Task("Soccer", "Watch the champions league soccer game", false);
        Task task3 = new Task("Gym", "Do chest exercises", true);
        taskManager.appendTask(task1);
        taskManager.appendTask(task2);
        taskManager.appendTask(task3);

        // Test 1: Move tasks to various positions
        taskManager.movetoTop(2); // Move task3 to the top
        return taskManager;
    }


    /**
     * Tests the removeTask() method of the TaskManager class.

     * Test scenarios: <BR>
     * 1. Test removing tasks from various positions in the toDoList and completedTasks lists. <BR>
     * 2 Test removing tasks at invalid indices return false.
     *
     *
     * @return true if all test scenarios pass, false otherwise.
     *
     */
    public static boolean testRemoveTask() {
        TaskManager taskManager = new TaskManager();

        // Prepare tasks for testing
        Task task1 = new Task("Math discussion", "Go to math discussion for quiz", true);
        Task task2 = new Task("Soccer", "Watch the champions league soccer game", false);
        Task task3 = new Task("Gym", "Do chest exercises", true);
        taskManager.appendTask(task1);
        taskManager.appendTask(task2);
        taskManager.appendTask(task3);

        // Test 1: Remove tasks from various positions in the toDoList
        if (!taskManager.removeTask(1)) { // Attempt to remove the second task
            System.out.println("Could not remove a task from the middle of the toDoList.");
            return false;
        }
        if (taskManager.toDoList.size() != 2) {
            System.out.println("toDoList size did not update correctly after removal.");
            return false;
        }

        if (!taskManager.removeTask(0)) { // Attempt to remove the first task
            System.out.println("Could not remove the first task from the toDoList.");
            return false;
        }
        if (taskManager.toDoList.size() != 1) {
            System.out.println("toDoList size did not update correctly after removal.");
            return false;
        }

        // Test 2: Removing tasks at invalid indices return false
        if (taskManager.removeTask(-1)) { // Attempt to remove with an invalid negative index
            System.out.println("Removing task at an invalid index should return false.");
            return false;
        }
        if (taskManager.removeTask(taskManager.toDoList.size())) { // Attempt to remove with an index equal to the size of the list (out of bounds)
            System.out.println("Removing task at an index out of bounds should return false.");
            return false;
        }

        // Additional Test: Checking if list updates correctly when removing the last remaining task
        if (!taskManager.removeTask(0) || !taskManager.toDoList.isEmpty()) {
            System.out.println("Could not remove the last remaining task or toDoList did not become empty.");
            return false;
        }

        return true;
    }


    /**
     * Tests the completeTask() method of the TaskManager class.
     * Test scenarios: <BR>
     * 1. Test completing tasks from various positions in the toDoList. <BR>
     * 2. Test completing tasks at invalid indices.
     *
     * @return true if all test scenarios pass, false otherwise.
     *
     */
    public static boolean testCompleteTask() {
        TaskManager taskManager = new TaskManager();

        // Add tasks to the TaskManager's to-do list
        Task task1 = new Task("Math discussion", "Go to math discussion for quiz", true);
        Task task2 = new Task("Soccer", "Watch the champions league soccer game", false);
        Task task3 = new Task("Gym", "Do chest exercises", true);
        taskManager.appendTask(task1);
        taskManager.appendTask(task2);
        taskManager.appendTask(task3);

        // Test 1: Test completing tasks from various positions in the toDoList
        if (!taskManager.completeTask(0)) { // Complete the first task
            System.out.println("Could not complete first task.");
            return false;
        }
        if (taskManager.toDoList.size() != 2 || taskManager.completedTasks.size() != 1) {
            System.out.println("list sizes did not update correctly after completing task.");
            return false;
        }

        if (!taskManager.completeTask(taskManager.toDoList.size() - 1)) { // Complete the last task in the list
            System.out.println("Could not complete last task.");
            return false;
        }
        if (taskManager.toDoList.size() != 1 || taskManager.completedTasks.size() != 2) {
            System.out.println("list sizes did not update correctly after completing another task.");
            return false;
        }

        // Test 2: Test completing tasks at invalid indices
        if (taskManager.completeTask(-1)) { // Attempt to complete a task at an invalid negative index
            System.out.println("Completing task at invalid index should return false.");
            return false;
        }
        if (taskManager.completeTask(taskManager.toDoList.size())) { // Attempt to complete a task at an index equal to the size of the list
            System.out.println("Completing task at out-of-bounds index should return false.");
            return false;
        }

        // All tests passed
        return true;
    }


    /**
     * Main method
     *
     * @param args input arguments if any
     */
    public static void main(String[] args) {
        System.out.println("testTaskCompareTo(): " + testTaskCompareTo());
        System.out.println("testTaskEquals(): " + testTaskEquals());
        System.out.println("testAddIsEmptySize(): " + testAddIsEmptySize());
        System.out.println("testAddToTaskList)(): " + testAddToTaskList());
        System.out.println("testRemoveClear(): " + testRemoveClear());
        System.out.println("testAddToSortedTaskList(): " + testAddToSortedTaskList());
        System.out.println("testAppendTask(): " + testAppendTask());
        System.out.println("testMoveTasks(): " + testMoveTasks());
        System.out.println("testRemoveTask(): " + testRemoveTask());
        System.out.println("testCompleteTask(): " + testCompleteTask());
    }
}
