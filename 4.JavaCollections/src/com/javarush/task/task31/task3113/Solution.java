package com.javarush.task.task31.task3113;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    static long totalFolders = 0;
    static long totalFiles = 0;
    static long totalSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String StringPath = reader.readLine();
        reader.close();
        Path path = Paths.get(StringPath);


        if (!Files.isDirectory(path)) {
            System.out.println(path +" - не папка");
        } else {
            Files.walkFileTree(path, new Visitors());

            System.out.println("Всего папок - " + (totalFolders - 1));
            System.out.println("Всего файлов - " + totalFiles);
            System.out.println("Общий размер - " + totalSize);
        }
    }


    private static class Visitors extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            totalFolders += 1;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            totalFiles += 1;
            totalSize += attrs.size();
            return FileVisitResult.CONTINUE;
        }
    }
}
