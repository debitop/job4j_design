package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(e -> !e.startsWith("#") && !e.isEmpty())
                    .filter(this::checkException)
                    .forEach(e -> values.put(e.substring(0, e.indexOf("=")), e.substring(e.indexOf("=") + 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean checkException(String s) {
        if (!s.contains("=") || s.substring(0, s.indexOf("=")).isEmpty()
                || s.substring(s.indexOf("=") + 1).isEmpty() || s.length() < 3) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}