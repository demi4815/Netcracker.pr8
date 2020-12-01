package ua.edu.sumdu.ta.Karina.pr8;

import ru.edu.samdu.ta.Karina.pr8.*;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException
    {
        //TaskXMLSerializer.test();
        //HTTP.getXmlFile(HTTP.HttpConnectionAndSb(HTTP.xml));
        //HTTP.getJsonFile(HTTP.HttpConnectionAndSb(HTTP.json));
        HTTP.XmlWork(HTTP.XmlToFood());
    }
}
