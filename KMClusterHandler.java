import java.util.Observer;
import java.util.ArrayList;
import java.util.Observable;
/**
 * 
 * Using the K-Mean clustering methods to deal withe board coordinates
 * 
 * @author Yaci Zhu
 * @version 1.0
 */
public class KMClusterHandler implements Observer{ 
  
    private final int clusters=2;
    private int times;
    private final int maxrecrusionTime=5;
    private double[][] means = new double[2][2];
    private ArrayList<Integer>[] ClustersA ;
    private ArrayList<Integer>[] ClustersB ;
    public int[][] points;
    private int records= =points.length;
  
 /**
 * constructor
 */
	public KMClusterHandler(){	
		sortPoint(points);
		for(int i=0; i<means.length; i++) {
			means[i][0] = points[(int) (Math.floor((records*1/clusters)/2) + i*records/clusters)][0];
			means[i][1] = points[(int) (Math.floor((records*1/clusters)/2) + i*records/clusters)][1];
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

	private static void createClusters(ArrayList<Integer>[] clusterList, double[][] means, int[][] points) {
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
	
//	you may need this method.
	
//	public static void displayCluster(ArrayList<Integer>[] clusterList, double[][] points) {
//		for(int i=0; i<clusterList.length; i++) {
//			for(int index: clusterList[i])	{
//				}			
//		}
//	}
  
/**
 * Set the points array 
 * @param o Observable object that notified this plotPanel observer
 * @param arg Object passed by notifyObservers()
 */
	@Override
	public void update(Observable o, Object arg) {	
			points[times][0] = ((BoardPanel) o).getX();
			points[times++][1] =((BoardPanel) o).getY();
		}
	
}
