package com.javarush.task.task26.task2601;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        //создание массива
        Integer[] array = {13, 8, 15, 5, 17};
        //System.out.println(Arrays.toString(array));

        //System.out.println(Arrays.toString(sort(array)));
    }

    public static Integer[] sort(Integer[] array) {
        //сортировка массива
        Arrays.sort(array);
        //System.out.println(Arrays.toString(array));
        //медиана
        int numMiddle = array.length / 2;
        double mediana = (array.length % 2 == 1) ? array[numMiddle] : (array[numMiddle - 1] + array[numMiddle]) / 2;
        //System.out.println("Медиана: " + (int)mediana);
        //сортировка массива по удаленности от медианы
        //захуячил лямбдой
        Arrays.sort(array, (o1, o2) -> {
            int result = (int)Math.round(Math.abs(mediana - o1) - Math.abs(mediana - o2));
            return (result == 0) ? (o1 - o2) : result;

        });
        return array;
    }
}
