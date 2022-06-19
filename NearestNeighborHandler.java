import java.util.*;

/**
 * This class handles updating the List<Line> in board.
 * 
 *@author Aditya Malladi
 *@version 1.0
 */
public class NearestNeighborHandler implements PointHandler {

	/**
	 * This method will update the List<Line> in board based on received options.
	 *
	 *@param observable
	 *@param options
	 */
	@Override
	public void update(Board observable, List<UpdateOption> options) {
		if (options.isEmpty()) {
			observable.setLines(Collections.<Line>emptyList());
		} else if (options.get(0) == UpdateOption.CLUSTER) {
			observable.setLines(Collections.<Line>emptyList());
		} else {
			observable.setLines(nearestNeighbor(observable));
		}
	}
	
	private List<Line> nearestNeighbor(Board observable) {
		List<Line> lines = new LinkedList<Line>();
		List<Point> points = new ArrayList<Point>(observable.getPoints());
		if (points.size() == 1) {
			return Collections.<Line>emptyList();
		}
		for (int i = 0; i < points.size() - 1; i++) {
			float min = Float.MAX_VALUE;
			Point nearest = points.get(i);
			for (int j = i + 1; j < points.size(); j++) {
				float distance = distanceTo(points.get(i), points.get(j));
				if (distance < min) {
					min = distance;
					nearest = points.get(j);
				}
			}
			lines.add(new Line(points.get(i), nearest));
		}
		return lines;
	}
	
	private float distanceTo(Point a, Point b) {
		return (float) Math.sqrt((a.getX() - b.getX())*(a.getX() - b.getX())
				+ (a.getY() - b.getY())*(a.getY() - b.getY()));
	}

}
