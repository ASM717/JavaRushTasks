package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader rf1 = new BufferedReader(new FileReader(reader.readLine()));
            BufferedReader rf2 = new BufferedReader(new FileReader(reader.readLine()));
            {
                reader.close();
                while (rf1.ready()){
                    allLines.add(rf1.readLine());
                }
                rf1.close();
                while (rf2.ready()); {
                    forRemoveLines.add(rf2.readLine());
                }
                rf2.close();
                new Solution().joinData();
            }
        } catch (IOException e) {
        }
    }

    public void joinData() throws CorruptedDataException {
        /// Хз как тут писать
    }
}
