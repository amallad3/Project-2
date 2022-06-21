import java.util.List;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;


/**
 * This class handles updating the List<Line> in board.
 * 
 *@author Aditya Malladi
 *@version 1.1
 */
public class KMClusterHandler implements Observer {

	private static int NUM_OF_TRY = 30;

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
	
		List<Point> points = board.getPoints();
		
		if (option == UpdateOption.CLUSTER) {
			clusterIntoTwoGroups(points);
		}
		else {
			clearAllColors(points);
		}
	}

	private void clusterIntoTwoGroups(List<Point> points) {
		DoublePoint centroid1 = null;
		DoublePoint centroid2 = null;


		/* select two random centroids from the list of points */ 
		
		while (centroid1 == centroid2) {
			centroid1 = randomCentroid(points);
			centroid2 = randomCentroid(points);
		}


		/* find precise centroid of two groups (maximum try: NUM_OF_TRY) */

		int tried = NUM_OF_TRY;

		List<Point> cluster1 = new ArrayList<>(points.size());
		List<Point> cluster2 = new ArrayList<>(points.size());

		while (tried > 0) {
			cluster1.clear();
			cluster2.clear();

			for (Point p : points) {
				// find distance between centroid and point
				double dist1 = distance(centroid1, p);
				double dist2 = distance(centroid2, p);

				double shortestDist = Math.min(dist1, dist2);

				if (shortestDist == dist1)
					cluster1.add(p);
				else
					cluster2.add(p);
			}

			centroid1 = meanOfCluster(cluster1);
			centroid2 = meanOfCluster(cluster2);

			tried--;
		}

		
		/* set color */

		for (Point p : cluster1)
			p.setColor(PointColor.RED);

		for (Point p : cluster2)
			p.setColor(PointColor.BLUE);
	}

	private DoublePoint meanOfCluster(List<Point> points) {
		double meanX = 0;
		double meanY = 0;
		for (Point p : points) {
			meanX += p.getX();
			meanY += p.getY();
		}

		meanX /= points.size();
		meanY /= points.size();
	
		return new DoublePoint(meanX, meanY);	
	}

	private DoublePoint randomCentroid(List<Point> points) {
		int randomNum = ThreadLocalRandom.current().nextInt(0, points.size());
		Point p = points.get(randomNum);
		return new DoublePoint(p.getX(), p.getY());
	}

	private double distance(DoublePoint a, Point b) {
		double xDiffSquared = Math.pow(a.getX() - b.getX(), 2);
		double yDiffSquared = Math.pow(a.getY() - b.getY(), 2);

		return Math.sqrt(xDiffSquared + yDiffSquared);
	}

	private void clearAllColors(List<Point> points) {
		for (Point p : points) {
			p.setColor(PointColor.NONE);
		}
	}
}


class DoublePoint {
	private double x;
	private double y;

	DoublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	@Override
	public boolean equals(Object o) {
		DoublePoint other = (DoublePoint) o;
		return this.x == other.x && this.y == other.y;
	}
} 