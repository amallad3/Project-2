import java.util.List;


/**
 * A simple container with points and lines.
 * 
 * @author Yeongbae Jeon
 * @version 1.0
 */
class DrawableGroup {
    private List<Point> points;
    private List<Line> lines;

    public DrawableGroup(List<Point> points, List<Line> lines) {
        this.points = points;
        this.lines = lines;
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public List<Line> getLine() {
        return this.lines;
    }
    
}