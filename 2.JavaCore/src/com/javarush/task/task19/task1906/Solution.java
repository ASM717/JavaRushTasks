package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s1 = reader.readLine();
        String s2 = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(s1);
        FileWriter fileWriter = new FileWriter(s2);

        int i = 1;
        int symbol;

        while (fileReader.ready()) {
            symbol = fileReader.read();
            if (i % 2 == 0) {
                fileWriter.write(symbol);
            }
            i++;
        }
        fileReader.close();
        fileWriter.close();
    }
}
