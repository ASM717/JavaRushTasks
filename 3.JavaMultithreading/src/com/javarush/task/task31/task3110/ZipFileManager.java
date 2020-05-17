package com.javarush.task.task31.task3110;

import java.nio.file.Path;

public class ZipFileManager {
    private Path zipFile;
    //В ней мы будем хранить полный путь к архиву, с которым будем работать.
    private Path source;
    //это путь к чему-то, что мы будем архивировать.

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {

    }
}
