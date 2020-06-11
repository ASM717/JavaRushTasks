package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File folder = new File(root);
        List<String> allPath = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();
        Collections.addAll(fileTree, folder.listFiles());
        while ((!fileTree.isEmpty())) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, currentFile.listFiles());
            } else {
                allPath.add(currentFile.getAbsolutePath());
            }
        }

        return allPath;

    }

    public static void main(String[] args) {

    }
}
