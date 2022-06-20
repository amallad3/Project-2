import java.util.*;

/**
 * This class handles updating the List<Line> in board.
 * 
 *@author Aditya Malladi
 *@version 1.1
 */
public class NearestNeighborHandler implements Observer {

	/**
	 * This method will update the List<Line> in board based on received options.
	 *
	 *@param observable
	 *@param options
	 */
	@Override
	public void update(Observable o, Object args) {
		Board board = (Board)o;
		UpdateOption option = (UpdateOption)args;
		if (option == UpdateOption.CLUSTER) {
			board.setLines(Collections.<Line>emptyList());
		} else {
			board.setLines(nearestNeighbor(board));
		}
	}
	
	private List<Line> nearestNeighbor(Board board) {
		List<Line> lines = new LinkedList<Line>();
		List<Point> points = new ArrayList<Point>(board.getPoints());
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
