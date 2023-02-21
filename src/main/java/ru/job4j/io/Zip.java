package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source.toFile().getPath()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkArgs(String[] args) {
        ArgsName arg = ArgsName.of(args);
        if (!Files.isDirectory(Paths.get(arg.get("d")))) {
            throw new IllegalArgumentException("No such directory");
        }
        if (!arg.get("e").startsWith(".")) {
            throw new IllegalArgumentException("No such file extension");
        }
        if (!arg.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("wrong fileName");
        }

    }

    public static void main(String[] args) throws IOException {

        checkArgs(args);
        ArgsName arg = ArgsName.of(args);

        Zip zip = new Zip();
        List<Path> list = Search.search(Paths.get(arg.get("d")), p -> !p.toFile().getName().endsWith(arg.get("e")));
        zip.packFiles(list, new File(arg.get("o")));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}