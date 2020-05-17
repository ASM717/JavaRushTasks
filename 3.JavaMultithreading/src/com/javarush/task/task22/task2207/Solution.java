package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.ConsoleHandler;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        br.close();
        StringBuilder temp = new StringBuilder();
        String s;
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        BufferedReader br1 = new BufferedReader(new FileReader(name));
        while ((s = br1.readLine()) != null) {
            //E:\test.txt
            list.add(s);
            System.out.println(s);
        }
        br1.close();

        for (int i = 0; i < list.size(); i++)
        {
            temp.append(list.get(i));
            temp.append(" ");
        }
        System.out.println(temp);

        Collections.addAll(list1, temp.toString().split("\\s"));

        for (int i = 0; i < list1.size(); i++)
            for (int j = i+1; j < list1.size(); j++) {
                String x1 = list1.get(i);
                String x2 = new StringBuilder(list1.get(j)).reverse().toString();
                if (x1.equals(x2)) {
                    Pair pair = new Pair();
                    pair.first = x1;
                    pair.second = new StringBuilder(x2).reverse().toString();
                    if (!result.contains(pair))
                        result.add(pair);
                }
            }

        for (Pair x : result)
            System.out.println(x);
    }



    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
