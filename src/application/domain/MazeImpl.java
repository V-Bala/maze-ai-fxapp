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
 * Represents a 2D grid of NxM tiles
 * 
 * @author vbala
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
	 * Create a grid of {@link Node}s and store in a HashSet. Then randomly select
	 * node indices based on the dimensions provided and add landmines.
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
			if (node.getRow() == (width - 1) && node.getCol() == (height - 1)) {
				return node;
			}
		}

		return null;

	}

	public void markVisitedNode(int x, int y) {
		visited[x][y] = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.domain.Maze#getWidthInTiles()
	 */
	@Override
	public int getWidth() {
		return this.width;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.domain.Maze#getHeightInTiles()
	 */
	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public Set<Node> getNodeSet() {
		return this.nodeSet;
	}

	/*
	 * (non-Javadoc)
	 * 
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
