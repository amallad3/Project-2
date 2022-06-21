import java.util.List;
import java.util.LinkedList;
import java.util.Observable;


/**
 * This class manages points and lines 
 * 
 * Because this is a container class, notifyObserver() is called by ClassifierModel class.
 * the ClassifierModel class pass UpdateOption argument to handlers when calling motifyObserver().
 * 
 * @author Yeongbae Jeon
 * @version 2022.06.19
 */
public class Board extends Observable {

    private List<Point> points = null;
    private List<Line> lines = null;


    public Board() {
        this.points = new LinkedList<Point>();
        this.lines = new LinkedList<Line>();

        this.addObserver(new NearestNeighborHandler());
        this.addObserver(new KMClusterHandler());
    }

    public void addPoint(Point newPoint) {
        this.points.add(newPoint);
        this.setChanged();
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
        this.setChanged();
    }

    public List<Line> getLines() {
        return this.lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
        this.setChanged();
    }
}
