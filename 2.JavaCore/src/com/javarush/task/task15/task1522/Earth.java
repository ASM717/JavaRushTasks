package com.javarush.task.task15.task1522;

public final class Earth implements Planet {
    private static Earth instance = null;

    private Earth() {}

    public static synchronized Earth getInstance() {
        if (instance == null)
            instance = new Earth();
        return instance;
    }
}
