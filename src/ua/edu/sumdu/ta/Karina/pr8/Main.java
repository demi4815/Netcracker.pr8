package ua.edu.sumdu.ta.Karina.pr8;

import ru.edu.samdu.ta.Karina.pr8.*;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException {

        final String fileName = "tasks.xml";
        final String resultName1 = "result1.xml";
        final String resultName2 = "result2.xml";

        TaskXMLSerializer.load(fileName);

        Task[] tasks = {
                new Task("A",0),
                new Task("B",1),
                new Task("C", 2, 10, 1),
                new Task("D", 3, 21, 3)
        };

        tasks[0].setActive(true);
        tasks[2].setActive(true);

        AbstractTaskList list1 =  new ArrayTaskList();
        AbstractTaskList list2 =  new LinkedTaskList();

        for (int i = 0; i < tasks.length; i++)
        {
            list1.add(tasks[i]);
            list2.add(tasks[i]);
        }

        TaskXMLSerializer.save(list1, resultName1);
        TaskXMLSerializer.save(list2, resultName2);


    }
}
