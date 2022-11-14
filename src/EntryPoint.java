import sengine.io.IO;
import sengine.io.PPMParser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class EntryPoint {

    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        //System.out.print("Please enter the file name you selected (only support ppm format): ");
        //PPMParser image=new PPMParser(key.nextLine());
        String result = "";
        try {
            result = JOptionPane.showInputDialog(new JFrame(), "Please enter the filename you wanna use (without suffix):");
        } catch (Exception e) {
            System.out.println("File not found/No input");
            System.exit(1);
        }

        if (result != null && !result.isEmpty()) {
            IO file = new IO(result + ".ppm");
            BufferedImage image = new PPMParser(file.readBytes()).getBufferedImage();
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    //GUI gui = new GUI("Cute Cat 1920*1280",imgx);
                    GUI gui = new GUI(image);

                    gui.setVisible(true);
                }
            });
        } else {
            System.out.println("empty filename!");
            System.exit(1);
        }


    }
}
