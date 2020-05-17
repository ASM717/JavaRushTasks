package com.javarush.task.task18.task1815;

import java.util.List;

/* 
Таблица
*/

public class Solution {

    public class TableInterfaceWrapper implements TableInterface {
        TableInterface tableInterface;


        public TableInterfaceWrapper(TableInterface tableInterface) {
            this.tableInterface = tableInterface;
        }

        @Override
        public void setModel(List rows) {
            System.out.println(rows.size());
            this.tableInterface.setModel(rows);
        }

        @Override
        public String getHeaderText() {
            return this.tableInterface.getHeaderText().toUpperCase();
        }

        @Override
        public void setHeaderText(String newHeaderText) {
            this.tableInterface.setHeaderText(newHeaderText);
        }
    }

    public interface TableInterface {
        void setModel(List rows);

        String getHeaderText();

        void setHeaderText(String newHeaderText);
    }

    public static void main(String[] args) {
    }
}