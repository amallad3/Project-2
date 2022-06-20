import java.awt.*;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
/*
* The BoardPanel is responsible for Draw points & lines.
* @author: Cungang Zhang
* @version:1.0
* */

public class BoardPanel extends JPanel {
    private JLabel label;
    private Point clickPoint;


    private void buildUI(Container container) {
        //container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        CoordinateArea coordinateArea = new CoordinateArea(this);
        container.add(coordinateArea);
        label = new JLabel();
        container.add(label);
    }

    public BoardPanel(){
        ClassifierModel model = ClassifierModel.getInstance();
        JPanel panel = new JPanel();
        BoardPanel controller = new BoardPanel();
        controller.buildUI(panel);//Think it is not right... but dont know how to fix //controller.buildUI(frame.getContentPane());
        panel.setVisible(true);
    }

    //adding points on board
    public void addPoint(Point p) {
        clickPoint = p;
    }

    public static class CoordinateArea extends JComponent implements MouseInputListener {
        Point point = null;
        BoardPanel controller;
        public CoordinateArea(BoardPanel controller) {
            this.controller = controller;
            addMouseListener(this);
            addMouseMotionListener(this);
            setOpaque(true);
        }
        protected void paintComponent(Graphics g) {
            if (point != null) {
                g.setColor(getForeground());
                g.fillOval(point.x-3, point.y-3, 7,7);
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
            controller.addPoint(point);
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