package com.javarush.task.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleMap();
        printPeopleMap(map);
    }

    public static Map<String, String> createPeopleMap() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<>();
        map.put("Сидоров","Джони");
        map.put("Волкер","Марк");
        map.put("Волкер","Джони");
        map.put("Матная","Кунилина");
        map.put("Люсик","Русик");
        map.put("Лобин","Робин");
        map.put("Вечный","Поперечный");
        map.put("Феминистка","Наталка");
        map.put("Лалка","Менеджер");
        map.put("Сидоров","Марк");

        return map;
    }

    public static void printPeopleMap(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
