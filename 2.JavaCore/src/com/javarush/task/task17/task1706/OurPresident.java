package com.javarush.task.task17.task1706;

public class OurPresident {
    private static OurPresident president;

    private OurPresident() {  //приватный конструктор
    }

    public static OurPresident getOurPresident() {   // приватный статический метод
        return president;
    }

    static {
        synchronized (OurPresident.class) {
            president = new OurPresident();
        }
    }
}
