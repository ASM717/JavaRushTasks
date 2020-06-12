package com.javarush.task.task33.task3308;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/* 
Создание класса по строке xml
*/
public class Solution {
    public static void main(String[] args) throws JAXBException {
        String xmlData =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<shop>\n" +
                        "    <goods>\n" +
                        "        <names>S1</names>\n" +
                        "        <names>S2</names>\n" +
                        "    </goods>\n" +
                        "    <count>12</count>\n" +
                        "    <profit>123.4</profit>\n" +
                        "    <secretData>String1</secretData>\n" +
                        "    <secretData>String2</secretData>\n" +
                        "    <secretData>String3</secretData>\n" +
                        "    <secretData>String4</secretData>\n" +
                        "    <secretData>String5</secretData>\n" +
                        "</shop>";

        StringReader reader = new StringReader(xmlData);

        JAXBContext context = JAXBContext.newInstance(getClassName());
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Object o = unmarshaller.unmarshal(reader);

        System.out.println(o.toString());
    }

    public static Class getClassName() throws JAXBException {

        String xmlDataShop = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<shop>\n" +
                "\t<goods>\n" +
                "\t\t<names>S1</names>\n" +
                "\t\t<names>S2</names>\n" +
                "\t</goods>\n" +
                "\t<count>12</count>\n" +
                "\t<profit>123.4</profit>\n" +
                "\t<secretData>String1</secretData>\n" +
                "\t<secretData>String2</secretData>\n" +
                "\t<secretData>String3</secretData>\n" +
                "\t<secretData>String4</secretData>\n" +
                "\t<secretData>String5</secretData>\n" +
                "</shop>";

        StringReader sr = new StringReader(xmlDataShop);
        JAXBContext context = JAXBContext.newInstance(Shop.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object o = unmarshaller.unmarshal(sr);
        System.out.println(o.toString());
        return Shop.class;  //your class name
    }
}
