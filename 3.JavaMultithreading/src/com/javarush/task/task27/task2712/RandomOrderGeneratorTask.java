package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private int interval;
    private List<Tablet> tablets;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval){
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        if(tablets.isEmpty()) return;
        try
        {
            while (!Thread.currentThread().isInterrupted())
            {
                Tablet tablet =  tablets.get((int)(Math.random()*tablets.size()));
                tablet.createTestOrder();
                Thread.sleep(interval);
            }
        }catch (Exception e){
            //return;
        }
    }
}
