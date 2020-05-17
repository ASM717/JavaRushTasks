package com.javarush.task.task13.task1322;

/* 
Интерфейс SimpleObject
*/

public class Solution {
    static public class StringObject implements SimpleObject<String> {
        @Override
        public Solution.SimpleObject<String> getInstance() {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        SimpleObject<String> stringObject = new StringObject();
    }

    interface SimpleObject<T> {
        SimpleObject<T> getInstance();
    }

}
