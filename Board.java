import java.util.List;
import java.util.LinkedList;
import java.util.Observable;


/**
 * This class observable singleton that implement ClassifierModel. Because this class
 * is singleton, getInstance() method should be used.
 * 
 * When observers are called, this singleton will pass list of UpdateOption to
 * the observers. Based on the list, handlers can decide whether to compute new points
 * and lines. 
 * 
 * addPoint() does not call observers. Only update() method call observers.
 * 
 * 
 * @author Yeongbae Jeon
 * @version 1.0
 */
public class Board extends Observable implements ClassifierModel{
    private static Board _instance = null;

    private List<Point> points = null;
    private List<Line> lines = null;


    /**
     * private constructor for making singleton object
     */
    private Board() {
        this.points = new LinkedList<Point>();
        this.lines = new LinkedList<Line>();
    }

    /**
     * get singleton object of this class.
     * @return singleton object
     */
    public static Board getInstance() {
        if (_instance == null) {
            _instance = new Board();
        }
        return _instance;
    }

    /**
     * refer to ClassifierModel interface
     */
    public DrawableGroup update(List<UpdateOption> options) {
        this.notifyObservers(options);
        return new DrawableGroup(this.points, this.lines);
    }

    /**
     * refer to ClassifierModel interface
     */
    public DrawableGroup addPoint(Point p) {
        this.points.add(p);
        this.setChanged();
        return new DrawableGroup(this.points, this.lines);
    }

    /**
     * refer to ClassifierModel interface
     */
    public List<Point> getPoints() {
        return this.points;
    }

    /**
     * refer to ClassifierModel interface
     */
    public void setPoints(List<Point> points) {
        this.points = points;
    }

    /**
     * refer to ClassifierModel interface
     */
    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}
