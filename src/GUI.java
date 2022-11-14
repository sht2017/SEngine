import sengine.io.IO;
import sengine.io.PPMParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class GUI extends JFrame {
    //    private Image img;
//    public GUI(Image img){
//        super("ImageBufferRenderer");
//        this.img=img;
//        setSize(img.getWidth(),img.getHeight()+23);
//        setResizable(false);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//    }
//    public GUI(String title,Image img){
//        super(title);
//        this.img=img;
//        setSize(img.getWidth(),img.getHeight()+23);
//        setResizable(false);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//    }
    private final BufferedImage buffer;
    Font font1 = new Font("TimesRoman", Font.BOLD, 26);
    Font font2 = new Font("TimesRoman", Font.PLAIN, 16);
    int orgX = 0;
    int orgY = 0;
    Color color = Color.BLACK;
    private BufferedImage canvasBuffer;
    private BufferedImage overlayBuffer;
    private BufferedImage widgetBuffer;
    private BufferedImage mixedBuffer;

    public GUI(BufferedImage buffer) {
        super("ImageBufferRenderer");
        this.buffer = buffer;
        this.canvasBuffer = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.overlayBuffer = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.widgetBuffer = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.mixedBuffer = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_INT_ARGB);
        setSize(buffer.getWidth(), buffer.getHeight() + 23);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new MouseEvents());
        addMouseMotionListener(new MouseMotionEvents());
    }

    public GUI(String title, BufferedImage buffer) {
        super(title);
        this.buffer = buffer;
        setSize(buffer.getWidth(), buffer.getHeight() + 23);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g2do = widgetBuffer.createGraphics();
        g2do.setColor(Color.BLACK);
        g2do.fillRect(0, 0, getWidth(), 50);
        g2do.setColor(Color.WHITE);
        g2do.fillRect(4, 4, getWidth() - 24, 42);
        g2do.setColor(Color.BLACK);
        g2do.fillRect(getWidth() / 2 - 2, 8, 34, 34);
        g2do.fillRect(getWidth() / 2 + 38, 8, 34, 34);
        g2do.fillRect(getWidth() / 2 + 78, 8, 34, 34);
        g2do.fillRect(getWidth() / 2 + 118, 8, 34, 34);
        g2do.fillRect(getWidth() / 2 + 158, 8, 34, 34);
        g2do.fillRect(getWidth() / 2 + 328, 8, 34, 34);
        g2do.setColor(Color.RED);
        g2do.fillRect(getWidth() / 2 + 1, 11, 28, 28);
        g2do.setColor(Color.GREEN);
        g2do.fillRect(getWidth() / 2 + 41, 11, 28, 28);
        g2do.setColor(Color.BLUE);
        g2do.fillRect(getWidth() / 2 + 81, 11, 28, 28);
        g2do.setColor(Color.BLACK);
        g2do.fillRect(getWidth() / 2 + 121, 11, 28, 28);
        g2do.fillRect(0, 0, 100, 50);
        g2do.fillRect(96, 0, 100, 50);
        g2do.setColor(Color.WHITE);
        g2do.fillRect(getWidth() / 2 + 161, 11, 28, 28);
        g2do.fillRect(4, 4, 92, 42);
        g2do.fillRect(100, 4, 92, 42);
        g2do.setColor(color);
        g2do.fillRect(getWidth() / 2 + 331, 11, 28, 28);
        g2do.setColor(Color.BLACK);
        g2do.setFont(font1);
        g2do.drawString("Save", 18, 33);
        g2do.drawString("CLR", 116, 33);
        g2do.setFont(font2);
        g2do.drawString("Selected Color:", getWidth() / 2 + 220, 30);


        Graphics2D gc = mixedBuffer.createGraphics();
        gc.drawImage(buffer, 0, 0, null);
        gc.drawImage(canvasBuffer, 0, 0, null);
        gc.drawImage(overlayBuffer, 0, 0, null);
        gc.drawImage(widgetBuffer, 0, 0, null);

        g2d.drawImage(mixedBuffer, 8, 31, null);


//        int[][][] rawImg=img.getData();
//        for (int h=0;h<rawImg[0].length;h++){

//            for (int w=0;w<rawImg.length;w++){
//                g2d.setColor(new Color(rawImg[w][h][0],rawImg[w][h][1],rawImg[w][h][2]));
//                //g2d.drawRect(w,h+23,1,1);
//                g2d.drawLine(w,h+23,w,h+23);
//            }
//        }
    }

    public void drawCanvas(int x, int y, int x1, int y1) {
        //Graphics2D g2d = (Graphics2D) getGraphics();
        Graphics2D g2d = canvasBuffer.createGraphics();
        //Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 10, 10);
        //g2d.fill(circle);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(x, y, x1, y1);
        repaint();
    }

    public void clearCanvas() {
        Graphics2D g2d = canvasBuffer.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, overlayBuffer.getWidth(), overlayBuffer.getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        repaint();
    }

    private void drawOverlay(int x, int y) {
        //Graphics2D g2d = (Graphics2D) getGraphics();
        Graphics2D g2d = overlayBuffer.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, overlayBuffer.getWidth(), overlayBuffer.getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.setColor(color);
        Ellipse2D.Double circle = new Ellipse2D.Double(x - 5, y - 5, 10, 10);
        g2d.fill(circle);
        repaint();
    }

    private void saveImage() {
        Graphics2D g2d = mixedBuffer.createGraphics();
        g2d.drawImage(buffer, 0, 0, null);
        g2d.drawImage(canvasBuffer, 0, 0, null);
        new IO(
                JOptionPane.showInputDialog(this, "Please enter the filename you wanna use (without suffix):") + ".ppm",
                true
        ).writeBytes(PPMParser.compose(mixedBuffer));
    }

    private class MouseEvents implements MouseListener {
        Color selColorA, selColorB;

        public void mouseClicked(MouseEvent e) {
            int x = e.getX() - 8;
            int y = e.getY() - 31;
            //System.out.println("Clicked x:"+x+", y:"+y);

            if (x > 0 && x < getWidth() && y > 0 && y < 50) {
                if (x > 4 && x < 96) {
                    saveImage();
                }
                if (x > 100 && x < 196) {
                    clearCanvas();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX() - 8;
            int y = e.getY() - 31;
            if (x > 0 && x < getWidth() && y > 50 && y < getHeight()) {
                orgX = x;
                orgY = y;
            }
            if (x > 0 && x < getWidth() && y > 0 && y < 50) {
                if (x > getWidth() / 2 - 2 && x < getWidth() / 2 + 34 && y > 10 && y < 40) {
                    selColorA = Color.RED;
                    System.out.println("Selected RED");
                } else if (x > getWidth() / 2 + 38 && x < getWidth() / 2 + 72 && y > 10 && y < 40) {
                    selColorA = Color.GREEN;
                    System.out.println("Selected GREEN");
                } else if (x > getWidth() / 2 + 78 && x < getWidth() / 2 + 112 && y > 10 && y < 40) {
                    selColorA = Color.BLUE;
                    System.out.println("Selected BLUE");
                } else if (x > getWidth() / 2 + 118 && x < getWidth() / 2 + 152 && y > 10 && y < 40) {
                    selColorA = Color.BLACK;
                    System.out.println("Selected BLACK");
                } else if (x > getWidth() / 2 + 158 && x < getWidth() / 2 + 192 && y > 10 && y < 40) {
                    selColorA = Color.WHITE;
                    System.out.println("Selected WHITE");
                } else {
                    selColorA = null;
                }
            }
            //draw(e.getX(),e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int x = e.getX() - 8;
            int y = e.getY() - 31;
            if (x > 0 && x < getWidth() && y > 0 && y < 50) {
                if (x > getWidth() / 2 - 2 && x < getWidth() / 2 + 34 && y > 10 && y < 40) {
                    selColorB = Color.RED;
                    System.out.println("Selected RED");
                } else if (x > getWidth() / 2 + 38 && x < getWidth() / 2 + 72 && y > 10 && y < 40) {
                    selColorB = Color.GREEN;
                    System.out.println("Selected GREEN");
                } else if (x > getWidth() / 2 + 78 && x < getWidth() / 2 + 112 && y > 10 && y < 40) {
                    selColorB = Color.BLUE;
                    System.out.println("Selected BLUE");
                } else if (x > getWidth() / 2 + 118 && x < getWidth() / 2 + 152 && y > 10 && y < 40) {
                    selColorB = Color.BLACK;
                    System.out.println("Selected BLACK");
                } else if (x > getWidth() / 2 + 158 && x < getWidth() / 2 + 192 && y > 10 && y < 40) {
                    selColorB = Color.WHITE;
                    System.out.println("Selected WHITE");
                } else {
                    selColorB = null;
                }
                if (selColorA == selColorB && (selColorA != null) && (selColorB != null)) {
                    color = selColorB;
                    repaint();
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class MouseMotionEvents implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            //System.out.println("dragged x="+e.getX()+", y="+e.getY());
            int x = e.getX() - 8;
            int y = e.getY() - 31;
            if (x > 0 && x < getWidth() && y > 40 && y < getHeight()) {
                drawCanvas(x, y, orgX, orgY);
                drawOverlay(x, y);
                orgX = x;
                orgY = y;
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            //System.out.println("moved x="+e.getX()+", y="+e.getY());
            int x = e.getX() - 8;
            int y = e.getY() - 31;
            if (x > 0 && x < getWidth() && y > 40 && y < getHeight()) {
                drawOverlay(x, y);
            }
        }
    }
}



