package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        FileInputStream stream = new FileInputStream(fileName);

        while (stream.available() > 0) {
            System.out.print((char)stream.read());
            }
            System.out.println();

            reader.close();
            stream.close();
        }
    }

        // напишите тут ваш код
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //переменная br будет содержать ссылку на символьный поток, связанный с консолью через поток ввода System.in
        //String filename = br.readLine();

        //InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream
        //("C:\\Users\\stepa\\Google Диск\\JavaRushTasks\\JavaRushTasks\\" +
        //                                        "2.JavaCore\\src\\com\\javarush\\task\\task13\\task1318\\bufio.txt"));
        //Scanner sc = new Scanner(new FileReader(filename));


