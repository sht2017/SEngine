package sengine.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class IO {
    public File file;

    public IO(String path) {
        this.file = new File(path);
    }

    public IO(String path, boolean create) {
        if (create) {
            try {
                this.file = new File(path);
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getLocalizedMessage());
                System.out.println(e.getCause());
                System.exit(1);
            }
        } else {
            this.file = new File(path);
        }

    }

    public Scanner read() {
        try {
            return new Scanner(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getCause());
            System.exit(1);
        }
        return null;
    }

    public FileWriter write(Boolean mode) {
        try {
            return new FileWriter(file, mode);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getCause());
            System.exit(1);
        }
        return null;
    }

    public byte[] readBytes() {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getCause());
            System.exit(1);
        }
        return null;
    }

    public void writeBytes(byte[] data) {
        try {
            Files.write(file.toPath(), data);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getCause());
            System.exit(1);
        }
    }
}
