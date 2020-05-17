package com.javarush.task.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static Map<String, Date> createMap() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        Map<String, Date> map = new HashMap<>();

        map.put("Сильвестр Сталлоне", dateFormat.parse("JUL 6 1946"));
        map.put("Фиби Тонкин", dateFormat.parse("JUL 12 1989"));
        map.put("Эдди Редмэйн", dateFormat.parse("JAN 6 1982"));
        map.put("Кен Лоуч", dateFormat.parse("JUN 17 1936"));
        map.put("Усэйн Болт", dateFormat.parse("AUG 21 1986"));
        map.put("Том Мейган", dateFormat.parse("JAN 11 1981"));
        map.put("Энди Гарсиа", dateFormat.parse("APR 12 1956"));
        map.put("Лэнс Хенриксен", dateFormat.parse("MAY 5 1940"));
        map.put("Эсекьель Гарай", dateFormat.parse("OCT 10 1986"));
        map.put("Кэтрин Лэнгфорд", dateFormat.parse("APR 29 1996"));

        //напишите тут ваш код
        return map;
    }

    public static void removeAllSummerPeople(Map<String, Date> map) {
        //напишите тут ваш код
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Date> entry = iterator.next();
            if (entry.getValue().getMonth() > 4 && entry.getValue().getMonth() < 8 ) {
                iterator.remove();

            }
        }

    }

    public static void main(String[] args) {

    }
}
