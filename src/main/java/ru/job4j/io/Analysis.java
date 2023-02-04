package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean broken = false;
            String s = "";
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (!broken && (line.startsWith("400") || line.startsWith("500"))) {
                    s = line.substring(line.indexOf(" ")) + ";";
                    broken = true;
                }
                if (broken && !(line.startsWith("400") || line.startsWith("500"))) {
                    s = s + line.substring(line.indexOf(" ") + 1);
                    broken = false;
                    System.out.println(s);
                    out.println(s);
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