import java.util.*;

/**
 * 
 * Using the K-Mean clustering methods to deal withe board coordinates
 * 
 * @author Yaci Zhu
 * @version 2.0
 */
public class KMClusterHandler implements Observer{
	private Point[] cent;
	private int k;
	private final int clusters=2;
	private List<Point> dots;
	private Random rand;
/**
 * 
 * upate the object for observable class
 * 
 * @para board & option
 */
	@Override
	public void update(Observable o, Object arg) {
		Board board = (Board)o;
		UpdateOption option = (UpdateOption)arg;
		if (option == UpdateOption.LINE) {
			board.setLines(Collections.<Line>emptyList());
		} else {
			List<Point> dots = new ArrayList<Point>(board.getPoints());
			int size=dots.size();
			k = Math.max(1, clusters);
			cent = new Point[k];

			for(int i =0; i < clusters; i++){
				dots.get(i).setClusterNumber(i);
			}
			kpp();

			for(int i=0;i< dots.size();i++){
				if (dots.get(i).getClusterNumber()==1){
					dots.get(i).setColor(PointColor.BLUE);
			}else{
					dots.get(i).setColor(PointColor.RED);
				}
			}
		}
	}


	private static double distance(Point a, Point b) {
		return (a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY());
	}
	private static int nearest(Point pt, Point[] others, int len){
		double minD = Double.MAX_VALUE;
		int index = pt.getClusterNumber();
		len = Math.min(others.length, len);
		double dist;
		for (int i = 0; i < len; i++) {
			if (minD > (dist = distance(pt, others[i]))) {
				minD = dist;
				index = i;
			}
		}
		return index;
	}
	private static double nearestDistance(Point pt, Point[] others, int len){
		double minD = Double.MAX_VALUE;
		len = Math.min(others.length, len);
		double dist;
		for (int i = 0; i < len; i++) {
			if (minD > (dist = distance(pt, others[i]))) {
				minD = dist;
			}
		}
		return minD;
	}
	private void kpp(){
		cent[0] = dots.get(rand.nextInt(clusters));
		double[] dist = new double[clusters];
		double sum = 0;
		for (int i = 1; i < k; i++) {
			for (int j = 0; j < clusters; j++) {
				dist[j] = nearestDistance(dots.get(j), cent, i);
				sum += dist[j];
			}
			sum = (sum * rand.nextInt(Integer.MAX_VALUE)) / Integer.MAX_VALUE;
			for (int j = 0; j < clusters; j++) {
				if ((sum -= dist[j]) > 0)
					continue;
				cent[i].setX(dots.get(i).getX());
				cent[i].setY(dots.get(i).getY());
			}
		}
		for (int i = 0; i < clusters; i++) {
			dots.get(i).setClusterNumber(nearest(dots.get(i), cent, k));
		}
	}

}

