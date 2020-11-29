package ua.edu.sumdu.ta.Karina.pr8.tests.my;

import ua.edu.sumdu.ta.Karina.pr8.*;

import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CollectionTest
{
    @Test
    public void hashTest() throws CloneNotSupportedException {
        Task task1 = new Task("test1", 1);
        Task task2 = new Task("test2", 2, 10, 1);

        Task task3 = task1.clone();
        Task task4 = task2.clone();

        assertEquals(task1.hashCode(), task3.hashCode());
        assertEquals(task2.hashCode(), task4.hashCode());
    }

    @Test
    public void equalsTest() throws CloneNotSupportedException {
        Task task1 = new Task("test1", 1);
        Task task2 = new Task("test2", 2, 10, 1);

        Task task3 = task1.clone();
        Task task4 = task2.clone();

        assertTrue(task1.equals(task3));
        assertTrue(task2.equals(task4));
    }


    @Test
    public void cloneListTest() throws CloneNotSupportedException {
        ArrayTaskList tasks1 = new ArrayTaskList ();
        Task[] ts = {new Task("a",0), new Task("b",1), new Task("c",2)};
        tasks1.add(ts[0]);
        tasks1.add(ts[1]);
        tasks1.add(ts[2]);

        AbstractTaskList list1 = tasks1.clone();

        int k = 0;
        while (k < list1.size())
        {
            assertTrue(list1.getTask(k).equals(tasks1.getTask(k)));
            k++;
        }

        LinkedTaskList tasks2 = new LinkedTaskList();
        tasks2.add(ts[0]);
        tasks2.add(ts[1]);
        tasks2.add(ts[2]);

        AbstractTaskList list2 = tasks2.clone();

        k = 0;
        while (k < list2.size())
        {
            assertTrue(list2.getTask(k).equals(tasks2.getTask(k)));
            k++;
        }
    }

    @Test
    public void toStringListTest()
    {
        Task task1 = new Task("A", 5);
        Task task2 = new Task("B", 5, 10, 1);
        Task task3 = new Task("C", 6);

        LinkedTaskList list = new LinkedTaskList();

        list.add(task1);
        list.add(task2);
        list.add(task3);

        System.out.println( list.toString());
    }

    @Test
    public void equalsListTest() throws CloneNotSupportedException {
        Task task1 = new Task("A", 5);
        Task task2 = new Task("B", 5, 10, 1);
        Task task3 = new Task("C", 6);

        ArrayTaskList list1 = new ArrayTaskList();
        LinkedTaskList list2 = new LinkedTaskList();

        list1.add(task1);
        list1.add(task2);
        list1.add(task3);
        list2.add(task1);
        list2.add(task2);
        list2.add(task3);

        assertFalse(list1.equals(list2));

        ArrayTaskList list3 = new ArrayTaskList();
        list3.add(task1);
        list3.add(task2);
        list3.add(task3);

        assertTrue(list1.equals(list3));

        list3.remove(task1);

        assertFalse(list1.equals(list3));
    }
}
