package ru.edu.samdu.ta.Karina.pr8;

import ua.edu.sumdu.ta.Karina.pr8.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.stream.*;

public class TaskXMLSerializer
{
    public static void load(String fileName) throws FileNotFoundException, XMLStreamException
    {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(fileName, new FileInputStream(fileName));

        int k = 1;

        while (true)
        {
            if(reader.getEventType() == XMLStreamConstants.CHARACTERS)
            {
                if (!reader.isWhiteSpace())
                {
                    System.out.println(k + ")" + reader.getText());
                    k++;
                }

            }

            if (!reader.hasNext())
            {
                break;
            }

            reader.next();
        }

    }

    public static void save(AbstractTaskList object, String fileName) throws IOException, XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter(fileName));

        writer.writeStartDocument("UTF-8","1.0");

        writer.writeStartElement("tasks");

        for (int i = 0; i < object.size(); i++) {

            writer.writeStartElement("task");

            if (object.getTask(i).isRepeated())
            {
                writer.writeAttribute("end", String.valueOf(object.getTask(i).end));

                writer.writeAttribute("repeat", String.valueOf(object.getTask(i).repeat));

                writer.writeAttribute("start", String.valueOf(object.getTask(i).start));
            }
            else
            {
                writer.writeAttribute("end", "");

                writer.writeAttribute("repeat", "0");

                writer.writeAttribute("start", "");
            }

            writer.writeAttribute("time", String.valueOf(object.getTask(i).time));

            writer.writeAttribute("repeated", String.valueOf(object.getTask(i).isRepeated()));

            writer.writeAttribute("active", String.valueOf(object.getTask(i).active));

            writer.writeCharacters(object.getTask(i).title);

            writer.writeEndElement();
        }

        writer.writeEndElement();

        writer.writeEndDocument();

        writer.flush();
    }

    public static void test() throws IOException, XMLStreamException
    {
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
