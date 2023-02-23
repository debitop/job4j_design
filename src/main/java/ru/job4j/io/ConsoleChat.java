package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> listAnswers = new ArrayList<>();
    private List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        readPhrases();
        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();
        log.add(phrase);
        while (!OUT.equals(phrase)) {
            if (STOP.equals(phrase)) {
                while (!CONTINUE.equals(phrase)) {
                    phrase = scanner.nextLine();
                    log.add(phrase);
                }
            }
            String answer = listAnswers.get((new Random().nextInt(listAnswers.size())));
            System.out.println(answer);
            log.add(answer);
            phrase = scanner.nextLine();
            log.add(phrase);
        }
        saveLog(log);
    }

    private void readPhrases() {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            listAnswers = br.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/chatLog.txt", "data/answers.txt");
        cc.run();
    }
}