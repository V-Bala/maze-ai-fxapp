/**
 * 
 */
package application.domain;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Maze Object
 * 
 * This object represents a 2D grid of NxM tiles.
 * @author Vijay
 *
 */
public class MazeImpl implements Maze {
	
	private int width;
	private int height;
	private int landMineCount;
	private Set<Node> nodeSet;
	private boolean[][] visited;

	/**
	 * Constructor.
	 * 
	 * @param totalWidth
	 * @param totalHeight
	 * @param landMineCount
	 */
	public MazeImpl(int width, int height, int landMineCount) {
		this.width = width;
		this.height = height;
		this.landMineCount = landMineCount;
		this.nodeSet = new LinkedHashSet<Node>();
		this.visited = new boolean[width][height];
		
		this.initialize();
	}
	
	/**
	 * Initialize the Maze object according to the specified width and height.
	 * Create a grid of {@link Node}s and store in a HashSet. Then randomly 
	 * select node indices based on the dimensions provided and add landmines.
	 * 
	 */
	@Override
	public void initialize() {
		
		// Create NODE objects based on the total length/width
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {				
				nodeSet.add(new Node(i, j));
			}
		}
		
		// Add up to #landMineCount mines to the maze
		for (int i = 0; i < landMineCount; i++) {
			
			// Calculate a random node index to place landmine 
			Random rand = new Random();

			// Obtain a number between [1 - nodeSet.size()].
			int nodeIndex = rand.nextInt(nodeSet.size() - 2) + 1;
			// Place the mine at the "random" location
			Node node = (Node) nodeSet.toArray()[nodeIndex];
			node.setLandMine(true);
		}
		
	}
	
	/**
	 * Get the {@link Node} at the specified row and column.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public Node getNodeAtRowCol(int row, int col) {
		
		for (Node node : nodeSet) {
			if (node.getRow() == row && node.getCol() == col) {
				return node;
			}
		}
		
		return new Node();
	}
	
	/**
	 * Get the start {@link Node}.
	 */
	public Node getStartNode() {
		
		for (Node node : nodeSet) {
			if (node.getRow() == 0 && node.getCol() == 0) {
				return node;
			}
		}
		
		return null;

	}
	
	/**
	 * Get the end {@link Node}.
	 */
	public Node getEndNode() {
		
		for (Node node : nodeSet) {
			if (node.getRow() == (width-1) && node.getCol() == (height-1)) {
				return node;
			}
		}
		
		return null;

	}	
	
	/**
	 * Clear the array marking which tiles have been visted by the path 
	 * finder.
	 */
	public void clearVisited() {
		for (int x=0;x<getWidth();x++) {
			for (int y=0;y<getHeight();y++) {
				visited[x][y] = false;
			}
		}
	}
	
	public boolean isVisited(int x, int y) {
		return visited[x][y];
	}
	
	public boolean isBlocked(int x, int y) {
		// if theres a unit at the location, then it's blocked
		Node node = getNodeAtRowCol(x, y);
		if (node == null || node.isLandMine())
		{
			return false;
		}
		return true;
	}

	public void visitedNode(int x, int y) {
		visited[x][y] = true;
	}

	/* (non-Javadoc)
	 * @see application.domain.Maze#getWidthInTiles()
	 */
	@Override
	public int getWidth() {
		return this.width;
	}

	/* (non-Javadoc)
	 * @see application.domain.Maze#getHeightInTiles()
	 */
	@Override
	public int getHeight() {
		return this.height;
	}
	
	/* (non-Javadoc)
	 * @see application.domain.Maze#getLandMineCount()
	 */
	@Override
	public int getLandMineCount() {
		return this.landMineCount;
	}
	
	@Override
	public Set<Node> getNodeSet() {
		return this.nodeSet;
	}

	/* (non-Javadoc)
	 * @see application.domain.Maze#isLandMine(application.domain.Node)
	 */
	@Override
	public boolean isLandMine(Node node) {
		
		// Return true if the node contains a landmine
		if (node.isLandMine()) {
			return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see application.domain.Maze#getTime(application.domain.Node, application.domain.Node)
	 */
	@Override
	public int getTime(Node start, Node end) {
		
		// Compute the elapsed time from the end node to the start node
		// This is the "duration" it takes to get from START to END
		return (end.getTime() - start.getTime());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MazeImpl [width=").append(width).append(", height=").append(height).append(", landMineCount=")
				.append(landMineCount).append(", nodeSet=").append(nodeSet).append("]");
		return builder.toString();
	}

}
