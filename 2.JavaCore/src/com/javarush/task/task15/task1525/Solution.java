package com.javarush.task.task15.task1525;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Файл в статическом блоке
*/

public class Solution<s> {
    public static List<String> lines = new ArrayList<String>();

    static {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(Statics.FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "WINDOWS-1251");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            while (true) {
                String s = reader.readLine();
                if (s == null)
                    break;
                lines.add(s);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        System.out.println(lines);
    }
}
