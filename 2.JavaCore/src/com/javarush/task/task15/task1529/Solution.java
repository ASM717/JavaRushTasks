package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }
    
    static {
        try {
            reset();
        } catch (IOException e){
            e.printStackTrace();
        }
        //add your code here - добавьте код тут
    }

    public static CanFly result;

    public static void reset() throws IOException {
        //add your code here - добавьте код тут
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String parametr = reader.readLine();
        if (parametr.equals("helicopter")) {
            result = new Helicopter();
        }else if (parametr.equals("plane")) {
            String k = reader.readLine();
            int n = Integer.parseInt(k);
            result = new Plane(n);
        }
        reader.close();
    }
}
