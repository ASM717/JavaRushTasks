package com.javarush.task.task28.task2801;

/* 
Осваиваем switch
*/
public class Solution {
    public static enum E1 {A, B, C, Y}

    public static enum E2 {D, E, F}

    public static enum E3 {D, E, F}

    public static void main(String[] args) {
        Solution.switchTest(E1.C);
        Solution.switchTest(E3.D);
        Solution.switchTest(E2.D);

        /* output
        it's E1.C
        undefined
        it's E2.D
         */
        //Вывод для E2.F - "it's E2.F"
        //Вывод для всех других значений - "undefined"
    }

    public static void switchTest(Enum obj) {
        //add your code here
        String typesEnum = obj.getClass().getSimpleName().toString();
        switch (typesEnum) {
            case "E1": {
                System.out.println("it's E1." + obj.name());
            }
            break;
            case "E2": {
                System.out.println("it's E2." + obj.name());
            }
            break;
            default: {
                System.out.println("undefined");
            }
        }

    }
}
