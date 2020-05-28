package com.javarush.task.task31.task3111;
//https://habr.com/ru/post/437694/
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private List<Path> foundFiles = new LinkedList<>();
    private String partOfName = null;
    private String partOfContent = null;
    private int minSize = -1;
    private int maxSize = -1;

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    /**Требования:
     1. В классе SearchFileVisitor нужно создать поля partOfName, partOfContent, minSize, maxSize и сеттеры для них.
     2. В классе SearchFileVisitor нужно создать поле foundFiles и геттер для него. Поле должно быть сразу инициализировано.
     3. Если в SearchFileVisitor задан критерий поиска partOfName, метод visitFile должен добавить файл в foundFiles, если в названии содержится строка partOfName.
     4. Если в SearchFileVisitor задан критерий поиска partOfContent, метод visitFile должен добавить файл в foundFiles, если в содержимом встречается строка partOfContent.
     5. Если в SearchFileVisitor задан критерий поиска maxSize, метод visitFile должен добавить файл в foundFiles, если размер файла меньше maxSize.
     6. Если в SearchFileVisitor задан критерий поиска minSize, метод visitFile должен добавить файл в foundFiles, если размер файла больше minSize.
     7. Метод visitFile должен быть реализован так, чтобы учитывать сразу несколько критериев поиска. */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean withFilter = true;
        if (partOfName != null && withFilter) {
            withFilter = file.getFileName().toString().contains(partOfName);
        }
        if (partOfContent != null && withFilter) {
            withFilter = Files.readAllLines(file).toString().contains(partOfContent);
        }
        if (minSize >= 0 && withFilter) {
            withFilter = attrs.size() > minSize;
        }
        if (maxSize >= 0 && withFilter) {
            withFilter = attrs.size() < maxSize;
        }

        if (withFilter) foundFiles.add(file);

        return FileVisitResult.CONTINUE;
    }
}
