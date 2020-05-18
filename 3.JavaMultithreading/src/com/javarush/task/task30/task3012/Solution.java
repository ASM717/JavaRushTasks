package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(1);
        solution.createExpression(2);
        solution.createExpression(3);
        solution.createExpression(9);
        solution.createExpression(32);
        solution.createExpression(74);
        solution.createExpression(258);
        solution.createExpression(951);
        solution.createExpression(1234);
        solution.createExpression(3000);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        int[] arr = {1, 3, 9, 27, 81, 243, 729, 2187, 3000};
        String s = "";
        int num = number; //исходное число в десятичной системе счисления

        while (num > 0) {
            if (num % 3 == 0) {
                s = s + "0";
                num /= 3;
            } else if (num % 3 == 1) {
                s = s + "+";
                num /= 3;
            } else if (num % 3 == 2) {
                s = s + "-";
                num /= 3;
                num++;
            }
        }

        int sum = 0;
        String str = "";
        for (int i = 0;i < s.length(); i++)
        {
            if (s.charAt(i) == '+') {
                str += " + " + arr[i];
                sum += arr[i];
            }
            else if (s.charAt(i) == '-')
            {
                str += " - " + arr [i];
                sum -= arr[i];
            }
        }
        System.out.println(sum + " =" + str);
    }
}