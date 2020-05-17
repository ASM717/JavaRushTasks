package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = "";
        String fileName2 = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fileName1 = reader.readLine();
        fileName2 = reader.readLine();
        reader.close();

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(fileName1));
        while (br.ready()){
            sb.append((char)br.read());
        }
        br.close();

        String temp = sb.toString();
        temp = temp.replaceAll("\\p{IsPunctuation}", "");

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName2));
        bw.write(temp);

        bw.close();
    }
}
