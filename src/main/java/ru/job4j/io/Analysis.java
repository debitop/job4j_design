package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(target)) {
            boolean broken = false;
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (!broken && (line.startsWith("400") || line.startsWith("500"))) {
                    out.append(line.substring(line.indexOf(" "))).append(";");
                    broken = true;
                }
                if (broken && !(line.startsWith("400") || line.startsWith("500"))) {
                    broken = false;
                    out.append(line.substring(line.indexOf(" ") + 1)).append(" ")
                            .append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}