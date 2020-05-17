package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        PrintStream consoleStream = System.out;
        //Создаем динамический массив
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //создаем адаптер к классу PrintStream
        PrintStream stream = new PrintStream(outputStream);
        //Устанавливаем его как текущий System.out
        System.setOut(stream);
        //Преобразовываем записанные в наш ByteArray данные в строку
        testString.printSomething();
        //Возвращаем все как было
        System.setOut(consoleStream);
        //Пишем в файл
        FileOutputStream outFile = new FileOutputStream(fileName);
        outputStream.writeTo(outFile);
        System.out.println(outputStream.toString());


        outFile.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

