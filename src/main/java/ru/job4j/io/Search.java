package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        checkParam(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void checkParam(String[] args) {
        Path path = Paths.get(args[0]);
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("Wrong directory in the first argument");
        }
        if (args[1].isEmpty() || !args[1].startsWith(".")) {
            throw new IllegalArgumentException("incorrect file extension in the second parameter");
        }
    }
}