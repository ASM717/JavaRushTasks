package com.javarush.task.task24.task2406;

import java.math.BigDecimal;

/* 
Наследование от внутреннего класса
*/
public class Solution {
    public class Building {
        public class Hall {

            public Hall(BigDecimal square) {
            }

            public Hall() {

            }
        }

        public class Apartments {
        }
    }
    public class Apt3Bedroom extends Building.Apartments {
        Apt3Bedroom(Building building) {
            building.super();
        }
    }
    public class BigHall extends Building.Hall {
        BigHall(Building building) {
            building.super();
        }

    }

    public static void main(String[] args) {

    }
}
