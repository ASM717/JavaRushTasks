package com.javarush.task.task18.task1814;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {
    //TxtInputStream txtInputStream;


    public TxtInputStream(String fileName) throws FileNotFoundException, UnsupportedFileNameException, IOException {
        super(fileName);
        if(!fileName.endsWith(".txt")) {
            super.close();
            throw new UnsupportedFileNameException();
        }
        if (fileName.endsWith(".txt")) {
        }
    }

    public static void main(String[] args) {
    }
}

