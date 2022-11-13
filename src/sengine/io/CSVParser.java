package sengine.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler extends IO{

    public FileHandler(String path) {
        super(path);
    }

    public String read(String parameter) {
        Scanner reader = read();
        String result = "";
        while (reader.hasNext()) {
            result += reader.nextLine() + String.format("%n");
        }
        return result;
    }
    public String[] parse(String delimiter){
        return read("").split(delimiter);
    }
    public static void main(String[] args){
        FileHandler file=new FileHandler("test.txt");
        System.out.println(file.read(""));
    }

}
