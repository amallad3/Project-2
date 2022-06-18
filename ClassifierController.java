import java.util.List;

/**
 * This controller class mediates between model singleton object and GUI renderer objects.
 * 
 * Every method in this class should be called by event listeners in renderer classees.
 * 
 * @author Yeongbae Jeon
 * @version 1.0
 */
public class ClassifierController {
    private ClassifierModel board;
    private ClassifierView renderer;
    

    /**
     * @param board singleton object of model class
     * @param renderer renderer object
     */
    public ClassifierController(ClassifierModel board, ClassifierView renderer) {
        this.board = board;
        this.renderer = renderer;
    }

    /**
     * Compute new lines and clusters based on the options and draw the lines and cluster 
     * @param options list of options to execute
     */
    public void runOptions(List<UpdateOption> options) {
        DrawableGroup group = board.update(options);
        renderer.render(group);
    }

    /**
     * Add new point to model and draw the new point to the screen
     * @param newPoint new point to add
     */
    public void addNewPoint(Point newPoint) {
        DrawableGroup group = board.addPoint(newPoint);
        renderer.render(group);
    }
}
