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
		List<List<Integer>> visited = new ArrayList<List<Integer>>(points.size());

		if (points.size() < 2) {
			return Collections.<Line>emptyList();
		}
		
		for (int i = 0; i < points.size(); i++) {
			visited.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < points.size(); i++) {
			
			float minDistance = Float.MAX_VALUE;
			int minIndex = i;
			
			for (int j = 0; j < points.size(); j++) {
				if (i != j) {
					float distance = distanceTo(points.get(i), points.get(j));
					if (distance < minDistance) {
						minDistance = distance;
						minIndex = j;
					}
				}
			}
			
			if (!visited.get(i).contains(minIndex)) {
				visited.get(i).add(minIndex);
				visited.get(minIndex).add(i);
				lines.add(new Line(points.get(i), points.get(minIndex)));
			}
		}
		return lines;
	}
	
	private float distanceTo(Point a, Point b) {
		return (float) Math.sqrt((a.getX() - b.getX())*(a.getX() - b.getX())
				+ (a.getY() - b.getY())*(a.getY() - b.getY()));
	}

}
