package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
*/

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        int score = 0;
        if (this.age > anotherCat.age)
            score++;
        if (this.weight > anotherCat.weight)
            score++;
        if (this.strength > anotherCat.strength)
            score++;

        if (score >= 2)
            return true;
        else return false;

        //напишите тут ваш код
    }

    public static void main(String[] args) {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();

        cat1.age = 7;
        cat1.weight = 5;
        cat1.strength = 10;

        cat2.age = 7;
        cat2.weight = 5;
        cat2.strength = 10;

        cat3.age = 15;
        cat3.weight = 15;
        cat3.strength = 15;

        System.out.println(cat1.fight(cat2));
        System.out.println(cat3.fight(cat1));


    }
}
