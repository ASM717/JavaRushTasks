package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new MyStream(new PrintStream(outputStream));
        System.setOut(stream);

        testString.printSomething();
        System.setOut(consoleStream); //Возвращаем как было

        System.out.println(outputStream.toString());

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }

    //Все остальные методы, кроме переопределенных будут работать, как и в обычном PrintStream
    public static class MyStream extends PrintStream {
        private static int count = 0;
        private String ad = "\nJavaRush - курсы Java онлайн";

        private PrintStream printStream;

        MyStream (PrintStream printStream) {
            super(printStream);
            this.printStream = printStream;
        }

        @Override
        public void print(String s) {
            if (MyStream.count == 1) {
                printStream.print(s+ad);
                MyStream.count = 0;
            } else {
                printStream.print(s);
                MyStream.count++;
            }
        }

        public MyStream(OutputStream out) {
            super(out);
        }
    }
}
