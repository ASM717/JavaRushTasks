package com.javarush.task.task22.task2208;

import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null)
                sb.append(key + " = '" + value + "' and ");
        }
        //System.out.print(sb.substring(0, sb.length() - 5));
        if (sb.length() > 0)
            return sb.substring(0, sb.length() - 5);
        else
            return sb.toString();
    }
}
