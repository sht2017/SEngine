import java.awt.*;
import javax.swing.*;
public class Test extends JFrame{
    public Test(){
        super("");
        setSize(500,500);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Test test = new Test();
                test.setVisible(true);
            }
        });
    }
}
