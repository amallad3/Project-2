import java.util.List;

/* Interface for classes that will update the points and lines in board.
 * 
 * @author Aditya Malladi
 * @version 1.0
 * */
public interface PointHandler {

	/* This method will modify the points and lines in board based on the options.
	 * 
	 * param observable
	 * param options
	 * */
	public void update(Board observable, List<UpdateOption> options);
	
}
