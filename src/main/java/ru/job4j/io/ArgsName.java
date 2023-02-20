package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        checkKey(key);
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("args is Empty");
        }
        for (String arg : args) {
            checkArgs(arg);
            String[] strings = arg.split("=", 2);
            values.put(strings[0].substring(1), strings[1]);
        }
    }

    private void checkKey(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("No such key");
        }
    }

    private void checkArgs(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException("Args is empty");
        }
        if (s.charAt(1) == '=') {
            throw new IllegalArgumentException("missing key");
        }
        if (s.charAt(0) != '-') {
            throw new IllegalArgumentException("missing -");
        }
        if (!s.contains("=")) {
            throw new IllegalArgumentException("missing =");
        }
        long countEqually = s.chars().filter(ch -> ch == '=').count();
        if (countEqually == 1 && s.endsWith("=")) {
            throw new IllegalArgumentException("missing value");
        }

    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}