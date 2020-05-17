package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        FileInputStream inputStream = new FileInputStream(reader.readLine());
        FileOutputStream stream1 = new FileOutputStream(reader.readLine());
        FileOutputStream stream2 = new FileOutputStream(reader.readLine());

        ArrayList<Integer> array = new ArrayList<>();
        while (inputStream.available() > 0) {
            array.add(inputStream.read());
        }
        if (array.size()%2==0){
            for (int i =0;i<array.size()/2;i++){
                stream1.write(array.get(i));
            }
            for (int i =(array.size()/2);i<array.size();i++){
                stream2.write(array.get(i));
            }

        }else {
            for (int i = 0; i < (array.size() / 2 + 1); i++) {
                stream1.write(array.get(i));
            }
            for (int i = (array.size() / 2 + 1); i < array.size(); i++) {
                stream2.write(array.get(i));
            }
        }
        inputStream.close();
        stream1.close();
        stream2.close();
    }
}


