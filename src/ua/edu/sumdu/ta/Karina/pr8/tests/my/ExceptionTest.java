package ua.edu.sumdu.ta.Karina.pr8.tests.my;

import ua.edu.sumdu.ta.Karina.pr8.*;

import org.junit.*;

public class ExceptionTest {

    /**
     * For class Task
     */

    @Test(expected = IllegalArgumentException.class)
    public void testTaskConstructorNonrepeated()
    {
        Task task = new Task("A", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTaskConstructorRepeated()
    {
        Task task = new Task("A", 5, 10, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextTimeAfter()
    {
        Task task = new Task("A", 5);
        task.nextTimeAfter(-1);
    }

    /**
     * For class ArrayTaskList
     */

    @Test(expected = NullPointerException.class)
    public void testAddArray()
    {
        ArrayTaskList list = new ArrayTaskList();
        Task task = null;
        list.add(task);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddWithIndexArray()
    {
        ArrayTaskList list = new ArrayTaskList();
        Task task1 = new Task("A", 5);
        Task task2 = new Task("B", 5, 10, 1);
        list.add(task1);
        list.add(2, task2);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveArray()
    {
        Task task1 = new Task("A", 5);
        Task task2 = new Task("B", 5, 10, 1);
        Task task3 = null;
        ArrayTaskList list = new ArrayTaskList();
        list.add(task1);
        list.add(task2);
        list.remove(task3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTaskArray()
    {
        Task task1 = new Task("A", 5);
        Task task2 = new Task("B", 5, 10, 1);
        ArrayTaskList list = new ArrayTaskList();
        list.add(task1);
        list.add(task2);
        list.getTask(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncomingArray()
    {
        Task task1 = new Task("A", 5);
        Task task2 = new Task("B", 5, 10, 1);
        ArrayTaskList list = new ArrayTaskList();
        list.add(task1);
        list.add(task2);
        task1.setActive(true);
        task2.setActive(true);
        list.incoming(-1, 10);
    }

    /**
     * For class LinkedTaskList
     */

    @Test(expected = NullPointerException.class)
    public void testAddLinked()
    {
        LinkedTaskList list = new LinkedTaskList();
        Task task = null;
        list.add(task);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddWithIndexLinked()
    {
        LinkedTaskList list = new LinkedTaskList();
        Task task1 = new Task("A", 5);
        Task task2 = new Task("B", 5, 10, 1);
        list.add(task1);
        list.add(2, task2);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveLinked()
    {
        Task task1 = new Task("A", 5);
        Task task2 = new Task("B", 5, 10, 1);
        Task task3 = null;
        LinkedTaskList list = new LinkedTaskList();
        list.add(task1);
        list.add(task2);
        list.remove(task3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTaskLinked()
    {
        Task task1 = new Task("A", 5);
        Task task2 = new Task("B", 5, 10, 1);
        LinkedTaskList list = new LinkedTaskList();
        list.add(task1);
        list.add(task2);
        list.getTask(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncomingLinked()
    {
        Task task1 = new Task("A", 5);
        Task task2 = new Task("B", 5, 10, 1);
        LinkedTaskList list = new LinkedTaskList();
        list.add(task1);
        list.add(task2);
        task1.setActive(true);
        task2.setActive(true);
        list.incoming(-1, 10);
    }

}
