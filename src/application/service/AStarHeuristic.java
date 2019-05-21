package application.service;

/**
 * Heuristic options for the A* Algorithm
 * 
 * @author vbala
 *
 */
public class AStarHeuristic 
{
	
	public static String [] algorithms = {"Chebyshev", "Euclidean", "Manhattan"};
	
	/**
	 * Chebyshev Distance Heuristic
	 */
	public static float getCostChebyshevHeuristic(int x, int y, int tx, int ty) 
	{
		
		// Use Chebyshev distance heuristic if we can move one square either
		// adjacent or diagonal
		float distance = 1;
		float distanceTwo = 1;
		float dx = Math.abs(x - tx);
		float dy = Math.abs(y - ty);
		
		return distance * (dx + dy) + (distanceTwo - 2 * distance) * Math.min(dx, dy);
	}
	
	/**
	 * Euclidean Distance Heuristic
	 */
	public static float getCostEuclideanDistance(int x, int y, int tx, int ty) 
	{		
		float dx = tx - x;
		float dy = ty - y;
		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		
		return result;
	}
	
	/**
	 * Manhattan Distance Heuristic
	 */
	public static float getCostManhattanDistance(int x, int y, int tx, int ty) 
	{	
		float distance = 1;
		float dx = Math.abs(x - tx);
		float dy = Math.abs(y - ty);
		
		float result = (float) distance* (dx + dy) + (distance - 2 * distance) * Math.min(dx, dy);
		
		return result;
	}

}
