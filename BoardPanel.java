import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.List;
/*
* The BoardPanel is responsible for Draw points & lines.
* @author: Cungang Zhang
* @version:1.1 (6/20/2022)
* */

public class BoardPanel extends JPanel {
    private List<Point> points;
    private List<Line> lines;

    public BoardPanel(){
        this.setBackground(Color.GRAY);
        this.setVisible(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                PointColor c = PointColor.NONE;
                ClassifierModel model = ClassifierModel.getInstance();
                List<Point> points = model.addPoint(new Point(x, y, c));
                drawPoints(points);
            }
        });
    }

    public void drawPoints(List<Point> points) {
        this.points = points;
        repaint();
    }
    public void drawLines(List<Line> lines){
        this.lines = lines;
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.points != null) {
            g.setColor(Color.BLACK);
            for (Point p : this.points) {
                switch (p.getColor()){
                    case BLUE:
                        g.setColor(Color.BLUE);
                        break;
                    case RED:
                        g.setColor(Color.RED);
                        break;
                    case NONE:
                    default:
                        g.setColor(Color.BLACK);
                }
                g.fillOval(p.getX(), p.getY(), 10, 10);
            }
        }
        if (this.lines != null){

            for(Line l : this.lines){
                Point x1 = l.getEndPoint1();
                Point x2 = l.getEndPoint2();
                g.setColor(Color.ORANGE);
                g.drawLine(x1.getX(),x1.getY(),x2.getX(),x2.getY());
            }
        }
    }
}