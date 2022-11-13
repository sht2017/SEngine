package sengine.io;
import java.util.Scanner;
import java.io.*;
public class IO {
    public File file;
    public IO(String path){
        try {
            this.file=new File(path);
            if (!file.exists()){
                file.createNewFile();
            }
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    public Scanner read(){
        try {
            return new Scanner(file);
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    public FileWriter write(Boolean mode){
        try {
            return new FileWriter(file,mode);
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
