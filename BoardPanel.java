import java.awt.*;
import java.awt.Point;
import java.awt.event.*;
import java.util.Observable;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
/*
* The BoardPanel is responsible for Draw points & lines.
* @author: Cungang Zhang
* @version:
* */

public class BoardPanel extends Observable {
    private JLabel label;
    private Point clickPoint;
    private void buildUI(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        CoordinateArea coordinateArea = new CoordinateArea(this);
        container.add(coordinateArea);
        label = new JLabel();
        container.add(label);
        coordinateArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public void updateClickPoint(Point p) {
        clickPoint = p;
    }
    public BoardPanel(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoardPanel controller = new BoardPanel();
        controller.buildUI(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }
    //
    public int getX() {
        return getX();
    }
    //
    public int getY() {
        return getY();
    }

    public static class CoordinateArea extends JComponent implements MouseInputListener {
        Point point = null;
        BoardPanel controller;
        Dimension preferredSize = new Dimension(500, 500);
        public CoordinateArea(BoardPanel controller) {
            this.controller = controller;
            addMouseListener(this);
            addMouseMotionListener(this);
            setBackground(Color.WHITE);
            setOpaque(true);
        }
        public Dimension getPreferredSize() {
            return preferredSize;
        }
        protected void paintComponent(Graphics g) {
            if (point != null) {
                g.setColor(getForeground());
                g.fillOval(point.x -3, point.y -3, 7,7);
            }
        }

        // show the dots
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (point == null) {
                point = new Point(x, y);
            } else {
                point.x = x;
                point.y = y;
            }
            controller.updateClickPoint(point);
            repaint();
        }
        public void mouseMoved(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseDragged(MouseEvent e) {}
    }
}