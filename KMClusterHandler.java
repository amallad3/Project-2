import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * Using the K-Mean clustering methods to deal withe board coordinates
 * 
 * @author Yaci Zhu and Yeongbae Jeon
 * @version 2.0
 */
public class KMClusterHandler implements Observer{
	
	/**
	 * 
	 * Update the object for observable class
	 * 
	 * @param board & option
	 */
	@Override
	public void update(Observable o, Object arg1) {
		List<Point> dots = ((Board) o).getPoints();
		UpdateOption option = (UpdateOption)arg1;
		if (option == UpdateOption.CLUSTER) {
			clusterIntoTwoGroups(dots);
		}
		else {
			clearAllColors(dots);
		}
	}

	private void clearAllColors(List<Point> points) {
		for (Point p : points) {
			p.setColor(PointColor.NONE);
		}
	}

	private void clusterIntoTwoGroups(List<Point> points) {
		tempPoint cent1 = null,cent2=null;
		
		while (cent1 == cent2) {
			cent1 = randomCent(points);
			cent2 = randomCent(points);
		}
		
		int maxTimes=30;
		List<Point> cluster1 = new ArrayList<>(points.size());
		List<Point> cluster2 = new ArrayList<>(points.size());
		do {
			cluster1.clear();
			cluster2.clear();
			for (Point p : points) {
				double dist1 = distance(cent1, p);
				double dist2 = distance(cent2, p);
				double shortestDist = Math.min(dist1, dist2);

				if (shortestDist == dist1)
					cluster1.add(p);
				else
					cluster2.add(p);
		

				cent1 = meanOfCluster(cluster1);
				cent2 = meanOfCluster(cluster2);

				maxTimes--;
			}
		}while(maxTimes==0);
			
		
		for (Point p: cluster1)
			p.setColor(PointColor.RED);
		for (Point p : cluster2)
			p.setColor(PointColor.BLUE);
		
		}
	
	private tempPoint meanOfCluster(List<Point> cluster) {
		double meanX = 0;
		double meanY = 0;
		for (Point p : cluster) {
			meanX += p.getX();
			meanY += p.getY();
		}

		meanX /= cluster.size();
		meanY /= cluster.size();
	
		return new tempPoint(meanX, meanY);
	}

	private double distance(tempPoint a, Point b) {
		double xDiffSquared = Math.pow(a.getX() - b.getX(), 2);
		double yDiffSquared = Math.pow(a.getY() - b.getY(), 2);
		return Math.sqrt(xDiffSquared + yDiffSquared);
	}

	private tempPoint randomCent(List<Point> points) {
		int randomNum = ThreadLocalRandom.current().nextInt(0, points.size());
		Point p = points.get(randomNum);
		return new tempPoint(p.getX(), p.getY());
	}
/**
 * 
 * This class is temporary use in the KMC in order to store the float center points
 * 
 * @author Yaci Zhu and Yeongbae Jeon
 * @version 2.0
 */
	class tempPoint {
		private double x;
		private double y;

		tempPoint(double x, double y) {
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
			tempPoint other = (tempPoint) o;
			return this.x == other.x && this.y == other.y;
		}
		}
	}
