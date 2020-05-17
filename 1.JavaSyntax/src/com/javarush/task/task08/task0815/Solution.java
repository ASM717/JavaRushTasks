package com.javarush.task.task08.task0815;

import java.util.HashMap;
import java.util.Map;

/* 
Перепись населения
*/

public class Solution {
    public static Map<String, String> createMap() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<>(10);
        map.put("Astvatsaturyan", "Stepan");
        map.put("Markovna", "Suzanna");
        map.put("Minosyan", "Revaz");
        map.put("Farshmakovna", "Lyuda");
        map.put("Monrachkova", "Leva");
        map.put("markin", "Leva");
        map.put("Siskin", "Vadim");
        map.put("Jlova", "Lyuda");
        map.put("Repin", "Stepan");
        map.put("Porster", "Suzanna");

        return map;
    }

    public static int getCountTheSameFirstName(Map<String, String> map, String name) {
        //напишите тут ваш код
        int count = 0;
        for (Map.Entry<String, String> pair : map.entrySet()){
            if (pair.getValue().equals(name)){
                count++;
            }
        }
        return count;

    }

    public static int getCountTheSameLastName(Map<String, String> map, String lastName) {
        //напишите тут ваш код
        int count = 0;
        for (Map.Entry<String, String> pair : map.entrySet()){
            if (pair.getKey().equals(lastName)){
                count++;
            }

        }
        return count;
    }

    public static void main(String[] args) {

    }
}
