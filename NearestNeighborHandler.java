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
		List<Point> points = new LinkedList<Point>(board.getPoints());
		int total = points.size();
		Boolean[] visited = new Boolean[total];
		Queue<Integer> q = new LinkedList<Integer>();
		
		if (total < 2) {
			return Collections.<Line>emptyList();
		}
		
		Point topLeft = new Point(0, 0, null, 0);
		int startIndex = 0;
		{
			float minDistance = Float.MAX_VALUE;
			for (int i = 0; i < total; i++) {
				float distance = distanceTo(topLeft, points.get(i));
				if (distance < minDistance) {
					minDistance = distance;
					startIndex = i;
				}
			}
		}
		
		for (int i = 0; i < total; i++) {
			visited[i] = false;
		}
		visited[startIndex] = true;
		q.add(startIndex);
		
		int count = 0;
		while (!q.isEmpty()) {
			count++;
			if (count == total) {
				break;
			}
			Point startPoint = points.get(q.poll());
			int endPointIndex = 0;
			float minDistance = Float.MAX_VALUE;
			
			for (int i = 0; i < total; i++) {
				if (visited[i] == false) {
					float distance = distanceTo(startPoint, points.get(i));
					if (distance < minDistance) {
						minDistance = distance;
						endPointIndex = i;
					}
				}
			}
			
			visited[endPointIndex] = true;
			q.add(endPointIndex);
			lines.add(new Line(startPoint, points.get(endPointIndex)));
		}
		
		return lines;
	}
	
	private float distanceTo(Point a, Point b) {
		return (float) Math.sqrt((a.getX() - b.getX())*(a.getX() - b.getX())
				+ (a.getY() - b.getY())*(a.getY() - b.getY()));
	}

}
