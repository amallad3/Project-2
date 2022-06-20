import java.util.LinkedList;
import java.util.List;


/**
 * This class declares methods related to data such as points and lines. There is also
 * Board class that manages the data, but view part should use this class. 
 * 
 * @author Yeongbae Jeon
 * @version 2022.06.19
 */
public class ClassifierModel {
    private static ClassifierModel _instance;

    private Board board;

    /**
     * This method calls a handler with the clustering algorithm to create two groups of points.
     * Instead of returning two lists, every point has one of two colors. And the returned list
     * is a reference in this class, so the list should not be modified.
     * 
     * @return List of points with color.
     */
    public List<Point> calculateCluster() {
        this.board.notifyObservers(UpdateOption.CLUSTER);
        return this.board.getPoints();
    }

    /**
     * This method calls a handler with the point conneting algorithm to create one long line.
     * the returned list is a reference in this class, so the list should not be modified.
     * 
     * @return List of lines that connect points.
     */
    public List<Line> calculateLines() {
        this.board.notifyObservers(UpdateOption.LINE);
        return this.board.getLines();
    }

    /**
     * This method add a new point to model, but does not call any handlers. And the returned
     * list should be readonly.
     * 
     * @param newPoint a new point to add
     * @return A list of points with the new point.
     */
    public List<Point> addPoint(Point newPoint) {
        this.board.addPoint(newPoint);
        return this.board.getPoints();
    }

    /**
     * clear all lists in the model
     */
    public void clearBoard() {
        this.board.setPoints(new LinkedList<>());
        this.board.setLines(new LinkedList<>());
    }

    /**
     * get a singleton object of this class
     * @return singleton object
     */
    public static ClassifierModel getInstance() {
        if (_instance == null)
            new ClassifierModel();

        return _instance;
    }

    private ClassifierModel() {
        this.board = new Board();
        _instance = this;
    }
}
