package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s1 = reader.readLine();
        reader.close();
        //FileReader fileReader = new FileReader(s1);

        BufferedReader fileReader = new BufferedReader(new FileReader(s1));
        int count = 0;

        String word = "world";

        while (fileReader.ready()) {
            String line = fileReader.readLine();
            String[] words = line.toString().split("\\W");
            for (String s : words)
                if (s.equals(word))
                    count++;
        }
        fileReader.close();
        System.out.println(count);
    }
}

/*while (fr.ready()){
        if ((char)fr.read() == 'w') {
        if ((char)fr.read() == 'o') {
        if ((char)fr.read() == 'r') {
        if ((char)fr.read() == 'l') {
        if ((char)fr.read() == 'd') {
        freq++;
        }
        }
        }
        }
        }
        } */
