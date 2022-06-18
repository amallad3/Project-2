/**
 * Interface for GUI classes. This interface is used for interaction between
 * the controller object and GUI objects.
 *  
 * @author Yeongbae Jeon
 * @version 1.0
 */
public interface ClassifierView {

    /**
     * This method draws points and lines in the DrawableGroup to screen.
     * @param group group of points and lines to draw
     */
    public void render(DrawableGroup group);
}
