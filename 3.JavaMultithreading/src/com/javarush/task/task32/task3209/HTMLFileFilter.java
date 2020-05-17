package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

//Для открытия или сохранения файла мы будем использовать JFileChooser из библиотеки swing.
//Объекты этого типа поддерживают фильтры, унаследованные от FileFilter.
//Сейчас мы напишем свой фильтр:

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        if (file.getName().toLowerCase().endsWith(".html")) {
            return true;
        }
        if (file.getName().toLowerCase().endsWith(".htm")) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
