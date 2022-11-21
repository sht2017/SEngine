import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class GUI extends JFrame {
    private final BufferedImage frameBuffer;
    private final BufferedImage widgetBuffer;
    private final BufferedImage widgetOverlay;
    private final BufferedImage mixedBuffer;
    int orgX = 0;
    int orgY = 0;
    boolean menu0status = false;
    boolean menu1status = false;
    int menu0Selected = -1;
    int menu1Selected = -1;

    public GUI() {
        super("BufferRenderer");
        this.frameBuffer = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);
        this.widgetBuffer = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);
        this.widgetOverlay = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);
        this.mixedBuffer = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);
        getContentPane().setPreferredSize(new Dimension(1024, 768));
        //setPreferredSize(new Dimension(1024, 768));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new MouseEvents());
        addMouseMotionListener(new MouseMotionEvents());
        pack();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g2do = widgetBuffer.createGraphics();
        g2do.setColor(Color.decode("#F2F2F2"));
        g2do.fillRect(0, 0, getWidth(), 30);
        g2do.setColor(Color.decode("#C9C9C9"));
        g2do.drawLine(0, 28, getWidth(), 28);
        g2do.setColor(Color.BLACK);
        //g2do.fillRect(0,0,36,30);
        g2do.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        g2do.drawString("File(s)", 10, 19);
        g2do.drawString("Settings", 62, 19);

        Graphics2D gf = frameBuffer.createGraphics();
        gf.setColor(Color.black);
        gf.fillRect(0, 0, getWidth(), getHeight());
        Graphics2D gc = mixedBuffer.createGraphics();
        gc.drawImage(frameBuffer, 0, 0, null);
        gc.drawImage(widgetBuffer, 0, 0, null);

        gc.drawImage(widgetOverlay, 0, 0, null);

        g2d.drawImage(mixedBuffer, 8, 31, null);
    }

    private void updateSelect(int x, int y, int width, int height, Color color) {
        Graphics2D g2do = widgetOverlay.createGraphics();
        g2do.setColor(color);
        g2do.fillRect(x, y, width, height);
        g2do.setColor(Color.black);
        g2do.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        g2do.drawString("File(s)", 10, 19);
        g2do.drawString("Settings", 62, 19);
        repaint();
    }

    private void drawMenu0() {
        menu0status = true;
        updateSelect(0, 0, 53, 28, Color.decode("#1A7DC4"));
        Graphics2D g2do = widgetOverlay.createGraphics();
        g2do.setColor(Color.white);
        g2do.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        g2do.drawString("File(s)", 10, 19);
        g2do.setColor(Color.black);
        g2do.drawString("Settings", 62, 19);
        g2do.setColor(Color.decode("#C9C9C9"));
        g2do.fillRect(0, 28, 142, 110);
        g2do.setColor(Color.decode("#F2F2F2"));
        g2do.fillRect(1, 29, 140, 108);
        g2do.setColor(Color.decode("#C9C9C9"));
        g2do.drawLine(0, 50, 141, 50);
        g2do.drawLine(0, 72, 141, 72);
        g2do.drawLine(0, 94, 141, 94);
        g2do.drawLine(0, 116, 141, 116);
        g2do.drawLine(0, 138, 141, 138);
        g2do.setColor(Color.black);
        g2do.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        g2do.drawString("Open Scene", 10, 44);
        g2do.drawString("Open Model", 10, 66);
        g2do.drawString("Save Scene", 10, 88);
        g2do.drawString("Save Model", 10, 110);
        g2do.drawString("Exit", 10, 132);
        repaint();
    }

    private void drawMenu1() {
        menu1status = true;
        updateSelect(53, 0, 68, 28, Color.decode("#1A7DC4"));
        Graphics2D g2do = widgetOverlay.createGraphics();
        g2do.setColor(Color.black);
        g2do.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        g2do.drawString("File(s)", 10, 19);
        g2do.setColor(Color.white);
        g2do.drawString("Settings", 62, 19);
        g2do.setColor(Color.decode("#C9C9C9"));
        g2do.fillRect(53, 28, 142, 22);
        g2do.setColor(Color.decode("#F2F2F2"));
        g2do.fillRect(54, 29, 140, 20);
        g2do.setColor(Color.decode("#C9C9C9"));
        g2do.drawLine(53, 50, 194, 50);
        g2do.setColor(Color.black);
        g2do.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        g2do.drawString("Open Setting Menu", 63, 44);
        repaint();
    }

    public void clearWidgetOverlay() {
        Graphics2D g2d = widgetOverlay.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, widgetOverlay.getWidth(), widgetOverlay.getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        repaint();
    }

    private class MouseEvents implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX() - 8;
            int y = e.getY() - 31;
            //System.out.println("Clicked x:"+x+", y:"+y);


            if (x < 53 && y < 30) {
                menu1status = false;
                if (!menu0status) {
                    clearWidgetOverlay();
                    drawMenu0();
                } else {
                    menu0status = false;
                    clearWidgetOverlay();
                    repaint();
                }
            } else if (x > 53 && x < 106 && y < 30) {
                menu0status = false;
                if (!menu1status) {
                    clearWidgetOverlay();
                    drawMenu1();
                } else {
                    menu1status = false;
                    clearWidgetOverlay();
                    repaint();
                }
            } else {
                menu0status = menu1status = false;
                clearWidgetOverlay();
                repaint();
            }
            switch (menu0Selected) {
                case 0:
                    System.out.println("read scene");
                    menu0Selected = -1;
                    break;
                case 1:
                    System.out.println("read mod");
                    menu0Selected = -1;
                    break;
                case 2:
                    System.out.println("save scene");
                    menu0Selected = -1;
                    break;
                case 3:
                    System.out.println("save mod");
                    menu0Selected = -1;
                    break;
                case 4:
                    System.out.println("exit");
                    menu0Selected = -1;
                    System.exit(0);
                    break;
            }
            if (menu1Selected == 0) {
                System.out.println("Open Setting Menu");
                menu1Selected = -1;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (x > 0 && x < getWidth() && y > 50 && y < getHeight()) {
                orgX = x;
                orgY = y;
            }
            //draw(e.getX(),e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
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
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            //System.out.println("moved x="+e.getX()+", y="+e.getY());
            int x = e.getX() - 8;
            int y = e.getY() - 31;
            //System.out.println("x: "+x+", y: "+y+", menu0sel:"+menu0Selected+", menu1sel:"+menu1Selected);
            if ((x < 53 && y < 30) || ((x < 142 && y > 28 && y < 138) && menu0status)) {
                if (menu1status) {
                    drawMenu1();
                    menu1Selected = -1;
                }
                if (menu0status) {
                    Graphics2D g2do = widgetOverlay.createGraphics();
                    g2do.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                    if (y > 28 && y < 50) {
                        drawMenu0();
                        g2do.setColor(Color.decode("#1A7DC4"));
                        g2do.fillRect(1, 29, 141, 21);
                        g2do.setColor(Color.white);
                        g2do.drawString("Open Scene", 10, 44);
                        repaint();
                        menu0Selected = 0;
                    } else if (y > 50 && y < 72) {
                        drawMenu0();
                        g2do.setColor(Color.decode("#1A7DC4"));
                        g2do.fillRect(1, 51, 141, 21);
                        g2do.setColor(Color.white);
                        g2do.drawString("Open Model", 10, 66);
                        repaint();
                        menu0Selected = 1;
                    } else if (y > 72 && y < 94) {
                        drawMenu0();
                        g2do.setColor(Color.decode("#1A7DC4"));
                        g2do.fillRect(1, 73, 141, 21);
                        g2do.setColor(Color.white);
                        g2do.drawString("Save Scene", 10, 88);
                        repaint();
                        menu0Selected = 2;
                    } else if (y > 94 && y < 116) {
                        drawMenu0();
                        g2do.setColor(Color.decode("#1A7DC4"));
                        g2do.fillRect(1, 95, 141, 21);
                        g2do.setColor(Color.white);
                        g2do.drawString("Save Model", 10, 110);
                        repaint();
                        menu0Selected = 3;
                    } else if (y > 116 && y < 130) {
                        drawMenu0();
                        g2do.setColor(Color.decode("#1A7DC4"));
                        g2do.fillRect(1, 117, 141, 21);
                        g2do.setColor(Color.white);
                        g2do.drawString("Exit", 10, 132);
                        repaint();
                        menu0Selected = 4;
                    } else {
                        drawMenu0();
                        menu0Selected = -1;
                    }
                } else {
                    menu0Selected = -1;
                    if ((x < 53 && y < 30) && !menu1status) {
                        clearWidgetOverlay();
                        updateSelect(0, 0, 53, 28, Color.decode("#DADADA"));
                    } else {
                        if (!menu1status) {
                            clearWidgetOverlay();
                            repaint();
                        }
                    }
                }
            } else if ((x > 53 && x < 106 && y < 30) || (x > 53 && x < 195 && y > 28 && y < 50)) {
                if (menu0status) {
                    drawMenu0();
                    menu0Selected = -1;
                }
                if (menu1status) {
                    Graphics2D g2do = widgetOverlay.createGraphics();
                    g2do.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                    if (y > 28) {
                        drawMenu1();
                        g2do.setColor(Color.decode("#1A7DC4"));
                        g2do.fillRect(54, 29, 141, 21);
                        g2do.setColor(Color.white);
                        g2do.drawString("Open Setting Menu", 63, 44);
                        repaint();
                        menu1Selected = 0;
                    } else {
                        drawMenu1();
                        menu1Selected = -1;
                    }
                } else {
                    menu1Selected = -1;
                    if ((x < 106 && y < 30) && !menu0status) {
                        clearWidgetOverlay();
                        updateSelect(53, 0, 68, 28, Color.decode("#DADADA"));
                    } else {
                        if (!menu0status) {
                            clearWidgetOverlay();
                            repaint();
                        }
                    }
                }
            } else {
                if (menu0status) {
                    drawMenu0();
                    menu0Selected = -1;
                }
                if (menu1status) {
                    drawMenu1();
                    menu1Selected = -1;
                }
                if (!(menu0status || menu1status)) {
                    clearWidgetOverlay();
                    repaint();
                }
            }
        }
    }
}



