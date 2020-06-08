package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) {
        Long number = Long.parseLong(args[1]);
        String text = args[2];
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw")) {

            randomAccessFile.seek(number);
             byte[] buffer = new byte[text.length()];
            randomAccessFile.read(buffer, 0, text.length());
            String textFromFile = convertByteToString(buffer);
            randomAccessFile.seek(randomAccessFile.length());
            if (text.equals(textFromFile))
                randomAccessFile.write("true".getBytes());
            else randomAccessFile.write("false".getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertByteToString(byte readBytes[]) {
        return new String(readBytes);
    }
}
