package com.javarush.task.task15.task1522;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Закрепляем паттерн Singleton
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static Planet thePlanet;
    static {
        try {
            readKeyFromConsoleAndInitPlanet();
        } catch (IOException e) {
        }
    }

    //add static block here - добавьте статический блок тут

    public static void readKeyFromConsoleAndInitPlanet() throws IOException {
        // implement step #5 here - реализуйте задание №5 тут
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String parametr = reader.readLine();
        if (parametr.equals(Planet.EARTH)){
            thePlanet = Earth.getInstance();
        }else if (parametr.equals(Planet.MOON)){
            thePlanet = Moon.getInstance();
        }else if (parametr.equals(Planet.SUN)){
            thePlanet = Sun.getInstance();
        }else {
            thePlanet = null;
        }
    }

}
