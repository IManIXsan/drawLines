package javaapplication3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class DrawLines extends JApplet {
    int xDown, yDown; // координаты нажатия клавиши
    int xPrev, yPrev; // предыдущие координаты конца линии
    
    Vector lines; // массив координат линий

    @Override
    public void init() {
        lines = new Vector();
        MouseListener ml = new MouseAdapter(){
        
            @Override
            public void mousePressed(MouseEvent e) {
                xPrev = e.getX(); yPrev = e.getY();

            }

            @Override
    public void mouseReleased(MouseEvent e) {
        xDown = e.getX(); yDown = e.getY();
        int k = e.getClickCount();
        System.out.println(k);
            if ( k < 2) {
                Rectangle p=new Rectangle(xPrev, yPrev, xDown-xPrev, yDown-yPrev);
                lines.addElement(p);
            }
            else lines.removeAllElements();
            repaint(); }
        };
        addMouseListener(ml);
}

    @Override
    public void paint(Graphics g) {
    Dimension appSize = getSize();
    g.setColor(Color.yellow);
    g.fillRect(0,0,appSize.width, appSize.height);
    g.setColor(Color.black);
    int size = lines.size();
        for ( int i = 0; i < size; i++ ) {
            Rectangle p=(Rectangle)lines.elementAt(i);
            g.drawLine(p.x, p.y, p.x+p.width, p.y+p.height);
            }
}

public static void main(String[] args) {
    JFrame frame = new JFrame ("Пример");
    DrawLines appl = new DrawLines();
    appl.init(); appl.start();
    frame.getContentPane().add(appl);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 300);
    frame.setVisible(true);
}

}
