package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static Map<String, Integer> createMap() {
        //напишите тут ваш код
        Map<String, Integer> map = new HashMap<>();
        map.put("Гена", 200);
        map.put("Стас", 500);
        map.put("Дюша", 350);
        map.put("Мартиросян", 5000);
        map.put("Харламов", 4500);
        map.put("Батрудинов", 3000);
        map.put("Карибидис", 4000);
        map.put("Никита", 450);
        map.put("Скороход", 3500);
        map.put("Турбо", 300);

        return map;
    }

    public static void removeItemFromMap(Map<String, Integer> map) {
        //напишите тут ваш код
        //Набор поддерживает удаление элементов, которое удаляет соответствующее
        // отображение с карты с помощью операций Iterator.remove , Set.remove , removeAll , retainAll и clear
        Iterator iterator = map.entrySet().iterator();

        Map.Entry pair;
        Integer a;
        while (iterator.hasNext()) {
            pair = (Map.Entry) iterator.next();
            a = (Integer) pair.getValue();
            if (a < 500)
                iterator.remove();
        }
    }

    public static void main(String[] args) {
        //HashMap<String, Integer> map = (HashMap<String, Integer>) createMap();
        //removeItemFromMap (map);

        //for (Map.Entry <String, Integer> pair : map.entrySet())
        //  System.out.println(pair.getKey()+" "+pair.getValue());
    }
}