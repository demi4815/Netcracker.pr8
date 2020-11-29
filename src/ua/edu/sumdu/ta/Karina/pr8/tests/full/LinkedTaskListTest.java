package ua.edu.sumdu.ta.Karina.pr8.tests.full;

import org.junit.Before;
import ua.edu.sumdu.ta.Karina.pr8.*;

public class LinkedTaskListTest extends TaskListTest {

    @Before
    public void before()
    {
        tasks = new LinkedTaskList();
    }
}
