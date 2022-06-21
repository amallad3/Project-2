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
	private int k,n;
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
		if (option == UpdateOption.CLUSTER) {
			board.setPoints(kmclsuer(board));
		}
	}

	private  List<Point> kmclsuer(Board board) {
		List<Point> dots = new ArrayList<Point>(board.getPoints());
		n=dots.size();
		k = Math.max(1, n);
		cent = new Point[k];

		for(int i =0; i < n; i++){
			dots.get(i).setClusterNumber(i);
		}
		
		int changed;
		int bestPercent = n/1000;
		int minIndex;
		kpp();
		
		int maxTimes=2;
		do {
			for (Point c : cent) {
					c.setX (0);
					c.setY(0);
					c.setClusterNumber(0);
			}
		for (int i=0;i<dots.size();i++) {
			int l=1;
			int num=dots.get(i).getClusterNumber();
				if(num < 0 || num > cent.length){
						((Point) dots).setClusterNumber(rand.nextInt(cent.length));
				}
				cent[num].setX(((Point) dots).getX()) ;
				cent[num].setY(((Point) dots).getY()) ;
				cent[num].setClusterNumber(l);
				l++;
		}
		for (Point c: cent) {
			int num1 = c.getX()/c.getClusterNumber();
			int num2 = c.getY()/c.getClusterNumber();
				c.setX(num1);
				c.setY(num2);
		}
		changed = 0;
		for (int i=0;i<dots.size();i++) {
			int num=dots.get(i).getClusterNumber();
				minIndex = nearest(dots.get(i), cent, k);
				if (k !=num) {
						changed++;
						dots.get(i).setClusterNumber(minIndex) ;
				}
		}
			maxTimes--;
		} while (changed > bestPercent && maxTimes > 0);

		for(int i=0;i< dots.size();i++){
			if (dots.get(i).getClusterNumber()==1){
				dots.get(i).setColor(PointColor.BLUE);
		}else{
				dots.get(i).setColor(PointColor.RED);
			}
		}
		return dots;
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
		cent[0] = dots.get(rand.nextInt(n));
		double[] dist = new double[n];
		double sum = 0;
		for (int i = 1; i < k; i++) {
			for (int j = 0; j < n; j++) {
				dist[j] = nearestDistance(dots.get(j), cent, i);
				sum += dist[j];
			}
			sum = (sum * rand.nextInt(Integer.MAX_VALUE)) / Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				if ((sum -= dist[j]) > 0)
					continue;
				cent[i].setX(dots.get(i).getX());
				cent[i].setY(dots.get(i).getY());
			}
		}
		for (int i = 0; i < n; i++) {
			dots.get(i).setClusterNumber(nearest(dots.get(i), cent, k));
		}
	}

}

