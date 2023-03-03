package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) {
        List<String> input = new ArrayList<>();
        List<String> output = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        try (Scanner scanner = new Scanner(new File(argsName.get("path")))) {
            while (scanner.hasNext()) {
                input.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] filteredColumn = argsName.get("filter").split(",");
        String[] allColumn = input.get(0).split(delimiter);
        List<Integer> columnIndex = new ArrayList<>();
        for (String s : filteredColumn) {
            for (int i = 0; i < allColumn.length; i++) {
                if (s.equals(allColumn[i])) {
                    columnIndex.add(i);
                }
            }
        }
        for (String s : input) {
            StringJoiner joiner = new StringJoiner(delimiter);
            String[] columns = s.split(delimiter);
            for (Integer i : columnIndex) {
                joiner.add(columns[i]);
            }
            output.add(joiner.toString());
        }
        if ("stdout".equals(argsName.get("out"))) {
            for (String s : output) {
                System.out.println(s);
            }
        } else {
            try (FileWriter fileWriter = new FileWriter(argsName.get("out"))) {
                for (String s : output) {
                    fileWriter.write(s + System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkArgs(ArgsName argsName) {
        if (!Files.exists(Path.of(argsName.get("path")))) {
            throw new IllegalArgumentException("Wrong path");
        }
        if (!";".equals(argsName.get("delimiter")) && !",".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Wrong delimiter");
        }
        if (!"stdout".equals(argsName.get("out")) && !argsName.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong out");
        }
        if (argsName.get("filter").length() == 0) {
            throw new IllegalArgumentException("Wrong filter");
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of args");
        }
        checkArgs(ArgsName.of(args));
        handle(ArgsName.of(args));
    }
}

