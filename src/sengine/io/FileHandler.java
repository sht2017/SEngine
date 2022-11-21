package sengine.io;

import java.util.Scanner;

public class FileHandler extends IO {

    public FileHandler(String path) {
        super(path);
    }

    public String read(String parameter) {
        Scanner reader = read();
        StringBuilder result = new StringBuilder();
        while (reader.hasNext()) {
            result.append(reader.nextLine()).append(String.format("%n"));
        }
        return result.toString();
    }

    public String[] parse(String delimiter) {
        return read("").split(delimiter);
    }
}
