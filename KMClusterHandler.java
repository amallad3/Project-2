import java.util.Observer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
/**
 * 
 * Using the K-Mean clustering methods to deal withe board coordinates
 * 
 * @author Yaci Zhu
 * @version 1.0
 */
public class KMClusterHandler implements PointHandler { 
  
    
    private final int clusters=2;
    private int times;
    private final int maxrecrusionTime=2;
    private double[][] means = new double[2][2];
    private ArrayList<Integer>[] ClustersA ;
    private ArrayList<Integer>[] ClustersB ;
    private int[][] points;
  
	private List<Point> KMCluster(Board observable) {
		
		List<Point> dots = new ArrayList<Point>(observable.getPoints());
		List<Point> Clusterpionts = new LinkedList<Point>();
		
		for (int i = 0; i < dots.size() - 1; i++) {
			Point a = dots.get(i);
			points[times][0]=a.getX();
			points[times++][1] =a.getY();
			}	
		
		sortPoint(points);
		for(int i=0; i<means.length; i++) {
			means[i][0] = points[(int) (Math.floor((points.length*1/clusters)/2) + i*points.length/clusters)][0];
			means[i][1] = points[(int) (Math.floor((points.length*1/clusters)/2) + i*points.length/clusters)][1];
		}
		
		for(int i=0; i<clusters; i++) {
			ClustersA[i] = new ArrayList<Integer>();
			ClustersB[i] = new ArrayList<Integer>();
		}
		createClusters(ClustersA, means, points);
		int recrusionTime = 0;
		while(true) {
			calcluateMeans(ClustersA, means, points);
			createClusters(ClustersB, means, points);
			recrusionTime++;
			if(recrusionTime > maxrecrusionTime || Equality(ClustersA, ClustersB))
				break;
			else
				reClusters(ClustersA, ClustersB);
		}
		
		return Clusterpionts;
		
	
}
	

	private static void sortPoint(int [][] points) {
		int[] temp;
		for(int i=0; i<points.length; i++)
		    for(int j=1; j<(points.length-i); j++)
			if(points[j-1][0] > points[j][0]) {
			    temp = points[j-1];
			    points[j-1] = points[j];
			    points[j] = temp;
			}
	}

	private static void calcluateMeans(ArrayList<Integer>[] clusterList, double[][] means, int[][] points) {
		double totalX = 0;
		double totalY = 0;
		for(int i=0; i<clusterList.length; i++) {
			totalX = 0;
			totalY = 0;
			for(int index: clusterList[i]) {
				totalX += points[index][0];
				totalY += points[index][1];
			}
			means[i][0] = totalX/clusterList[i].size();
			means[i][1] = totalY/clusterList[i].size();
		}
	}

	private static  ArrayList<Integer>[] createClusters (ArrayList<Integer>[] clusterList, double[][] means, int[][] points) {
		double distance[] = new double[means.length];
		double minDistance = 999999999;
		int minIndex = 0;

		for(int i=0; i<points.length; i++) {
			minDistance = 999999999;
			for(int j=0; j<means.length; j++) {
				distance[j] = Math.sqrt(Math.pow((points[i][0] - means[j][0]), 2) + Math.pow((points[i][1] - means[j][1]), 2));
				if(distance[j] < minDistance) {
					minDistance = distance[j];
					minIndex = j;
				}
			}
			 clusterList[minIndex].add(i);
		}
		return clusterList;
	}
	
	private static boolean Equality(ArrayList<Integer>[] clusterA, ArrayList<Integer>[] clusterB) {
		for(int i=0; i<clusterA.length; i++) {
			if(clusterA[i].size() != clusterB[i].size())
				return false;

			for(int j=0; j<clusterA[i].size(); j++)
				if(clusterA[i].get(j) != clusterB[i].get(j))
					return false;
		}

		return true;
	}
	
	private static void reClusters(ArrayList<Integer>[] ClustersA, ArrayList<Integer>[] ClustersB) {
		for(int i=0; i<ClustersB.length; i++) {
			ClustersA[i].clear();
			for(int index: ClustersB[i])
				ClustersA[i].add(index);

			ClustersB[i].clear();
		}
	}
	


	@Override
	public void update(Board observable, List<UpdateOption> options) {
		if (options.isEmpty()) {
			observable.setPoints(Collections.<Point>emptyList());
		} else if (options.get(0) == UpdateOption.LINE) {
			observable.setPoints(Collections.<Point>emptyList());
		} else {
			observable.setPoints(KMCluster(observable));
		}		
	}	
}

