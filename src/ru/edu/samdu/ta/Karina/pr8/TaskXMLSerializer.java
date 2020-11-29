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

        // Открываем XML-документ и Пишем корневой элемент BookCatalogue
        writer.writeStartDocument("1.0");
        writer.writeStartElement("tasks");

        for (int i = 0; i < object.size(); i++) {

            writer.writeStartElement("task");

            writer.writeAttribute("active", String.valueOf(object.getTask(i).active));

            writer.writeAttribute("end", String.valueOf(object.getTask(i).end));

            writer.writeAttribute("repeat", String.valueOf(object.getTask(i).repeat));

            writer.writeAttribute("repeated", String.valueOf(object.getTask(i).isRepeated()));

            writer.writeAttribute("start", String.valueOf(object.getTask(i).start));

            writer.writeAttribute("time", String.valueOf(object.getTask(i).time));

            writer.writeCharacters(object.getTask(i).title);

            writer.writeEndElement();
        }
        // Закрываем корневой элемент
        writer.writeEndElement();
        // Закрываем XML-документ
        writer.writeEndDocument();
        writer.flush();

    }
}
