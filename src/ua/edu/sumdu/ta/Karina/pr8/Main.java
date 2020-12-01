package ua.edu.sumdu.ta.Karina.pr8;

import org.json.simple.parser.ParseException;
import ru.edu.samdu.ta.Karina.pr8.*;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException, ParseException {
        //TaskXMLSerializer.test();
        //Rest.getXmlFile(Rest.HttpConnectionAndSb(Rest.xml));
        //Rest.getJsonFile(Rest.HttpConnectionAndSb(Rest.json));
        //Rest.XmlWork(Rest.XmlToFood());
        Rest.JsonToFood();
    }
}
