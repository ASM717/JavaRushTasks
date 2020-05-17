package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedInputStream file = new BufferedInputStream(new FileInputStream(args[0]));
        //http://www.asciitable.com/ использовал таблицу символов от 65 до 122
        int count = 0;
        while (file.available() > 0) {
            int a = file.read();
            if (a >= 65 && a <= 122) {
                count++;
            }
        }
        file.close();

        System.out.println(count);
    }
}
