package com.javarush.task.task31.task3109;

import java.io.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        File file = new File(fileName);

        Properties properties = new Properties();

        try {
            properties.loadFromXML(new FileInputStream(file));
        } catch (IOException e) {
            try {
                properties.load(new FileReader(file));

            } catch (Exception ex) {
                return properties;
            }
        }
        return properties;
    }
}
