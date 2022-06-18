import java.util.List;


/**
 * This interface declares methods related to data such as points and lines.
 * 
 * @author Yeongbae Jeon
 * @version 1.0
 */
public interface ClassifierModel {

    /**
     * This method calls handlers (observers) that compute based on points and lines.
     * 
     * DrawableGroup contains reference to points and lines in model object, so
     * anyone should not modify data in the DrawableGroup.
     * 
     * @param options List of options to determine which handler should be called
     * @return A group of points and lines computed by the handlers.
     */
    public DrawableGroup update(List<UpdateOption> options);

    /**
     * This method add a new point to model, but does not call handlers. 
     * 
     * DrawableGroup contains reference to points and lines in model object, so
     * anyone should not modify data in the DrawableGroup.
     * 
     * @param p a new point to add
     * @return A group of points and lines that were not computed by the handlers.
     */
    public DrawableGroup addPoint(Point p);

    /**
     * Getter
     * 
     * Returned value is just reference to points in the model
     * 
     * This method should not be called by any object other than handlers (observers).
     * @return list of points
     */
    public List<Point> getPoints();

    /**
     * Setter
     * 
     * This method should not be called by any object other than handlers (observers).
     * 
     * Instead of copying points in the argument, this setter just copy reference.
     * 
     * @param points list of points to set
     */
    public void setPoints(List<Point> points);

    /**
     * setter
     * 
     * This method should not be called by any object other than handlers (observers).
     * 
     * Instead of copying points in the argument, this setter just copy reference.
     * 
     * @param lines list of lines to set
     */
    public void setLines(List<Line> lines);
}
