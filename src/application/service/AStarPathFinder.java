package application.service;

import java.util.ArrayList;
import application.domain.Maze;
import application.domain.Node;
import application.domain.Path;
import application.domain.SortedList;

/**
 * A path finder implementation that uses the AStar search algorithm with
 * heuristics (Manhattan, Euclidean, or Chebychev) to find the shortest-path
 * from start to end.
 * 
 * @author vbala
 */
public class AStarPathFinder {

	/** Set of already searched nodes */
	private ArrayList<Node> closed = new ArrayList<Node>();

	/** Set of not traversed nodes */
	private SortedList open = new SortedList();

	/** Object containing maze configuration details */
	private Maze maze;

	/** Max search distance */
	private int maxSearchDistance;

	/** Complete set of all nodes */
	private Node[][] nodes;

	/**
	 * Create a path finder
	 * 
	 * @param heuristic         The heuristic used to determine the search order of
	 *                          the map
	 * @param map               The map to be searched
	 * @param maxSearchDistance The maximum depth we'll search before giving up
	 * @param allowDiagMovement True if the search should try diagonal movement
	 */
	public AStarPathFinder(Maze maze, int maxSearchDistance, boolean allowDiagMovement) {
		this.maze = maze;
		this.maxSearchDistance = maxSearchDistance;

		nodes = new Node[maze.getWidth()][maze.getHeight()];
		for (int x = 0; x < maze.getWidth(); x++) {
			for (int y = 0; y < maze.getHeight(); y++) {
				nodes[x][y] = new Node(x, y);
			}
		}
	}

	/**
	 * Solve the maze specified by the input parameters and build a {@Path}. Track
	 * the runtime of the A* algorithm using the Java provided System utility.
	 * 
	 * If no solution is found then return NULL.
	 * 
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 * @param algorithmToUse
	 * @return
	 */
	public Path solveMaze(int startX, int startY, int width, int height, String algorithmToUse) {
		long startTime = System.nanoTime();
		Path path = findPath(startX, startY, width, height, algorithmToUse);

		if (path != null) {
			long endTime = System.nanoTime();
			long duration = (endTime - startTime) / (1000000); // divide to get ms
			path.setRuntime(duration);
		}

		return path;
	}

	/**
	 * Return a {@Path} if one is found, else return NULL.
	 * 
	 */
	public Path findPath(int startX, int startY, int targetX, int targetY, String heuristicToUse) {

		// Decrement max x and max y values to account for zero-based indexing
		targetX = targetX - 1;
		targetY = targetY - 1;

		// Initial state for A*. The closed group is empty
		// Start node is in the open list
		nodes[startX][startY].cost = 0;
		nodes[startX][startY].level = 0;
		closed.clear();
		open.clear();
		open.add(nodes[startX][startY]);

		nodes[targetX][targetY].parent = null;

		// while we haven'n't exceeded our max search depth
		int maxDepth = 0;
		while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {
			// Pull the first node, which is our start node
			Node current = getBaseNode();
			if (current == nodes[targetX][targetY]) {
				break;
			}

			removeFromOpen(current);
			addToClosed(current);

			// Traverse the neighbors for the current node
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {

					if ((x == 0) && (y == 0)) {
						// Skip not a neighbor
						continue;
					}

					// Get the location of the neighbor
					int xp = x + current.getRow();
					int yp = y + current.getCol();

					if (isValidLocation(xp, yp)) {

						// Add the base node to the visited list and get
						// neighbors
						float nextStepCost = current.cost + getMovementCost();
						Node neighbour = nodes[xp][yp];
						maze.markVisitedNode(xp, yp);

						// Node already exists, check its cost
						// and update or ignore accordingly
						if (nextStepCost < neighbour.cost) {
							if (inOpenList(neighbour)) {
								removeFromOpen(neighbour);
							}
							if (inClosedList(neighbour)) {
								removeFromClosed(neighbour);
							}
						}

						// We have not yet processed this node, compute the
						// heuristic costs and add to the open list
						if (!inOpenList(neighbour) && !(inClosedList(neighbour))) {
							neighbour.cost = nextStepCost;
							neighbour.heuristic = getHeuristicCost(xp, yp, targetX, targetY, heuristicToUse);
							maxDepth = Math.max(maxDepth, neighbour.setParent(current));
							addToOpen(neighbour);
						}
					}
				}
			}
		}

		if (nodes[targetX][targetY].parent == null) {
			// Nothing left to search, return null
			return null;
		}

		// Build path if we have one, else return null
		Path path = new Path();
		Node target = nodes[targetX][targetY];
		while (target != nodes[startX][startY]) {
			path.buildStep(maze, target.getRow(), target.getCol());
			target = target.parent;
		}
		path.buildStep(maze, startX, startY);

		// Return path
		return path;
	}

	/**
	 * Get the first element from the open list. This is the next one to be
	 * searched.
	 * 
	 * @return The first element in the open list
	 */
	protected Node getBaseNode() {
		return (Node) open.first();
	}

	/**
	 * Add a node to the open list
	 * 
	 * @param node The node to be added to the open list
	 */
	protected void addToOpen(Node node) {
		open.add(node);
	}

	/**
	 * Check if a node is in the open list
	 * 
	 * @param node The node to check for
	 * @return True if the node given is in the open list
	 */
	protected boolean inOpenList(Node node) {
		return open.contains(node);
	}

	/**
	 * Remove a node from the open list
	 * 
	 * @param node The node to remove from the open list
	 */
	protected void removeFromOpen(Node node) {
		open.remove(node);
	}

	/**
	 * Add a node to the closed list
	 * 
	 * @param node The node to add to the closed list
	 */
	protected void addToClosed(Node node) {
		closed.add(node);
	}

	/**
	 * Check if the node supplied is in the closed list
	 * 
	 * @param node The node to search for
	 * @return True if the node specified is in the closed list
	 */
	protected boolean inClosedList(Node node) {
		return closed.contains(node);
	}

	/**
	 * Remove a node from the closed list
	 * 
	 * @param node The node to remove from the closed list
	 */
	protected void removeFromClosed(Node node) {
		closed.remove(node);
	}

	/**
	 * Check if a given location is valid.
	 * 
	 * @param x The x coordinate of the location to check
	 * @param y The y coordinate of the location to check
	 * @return True if the location is valid
	 */
	protected boolean isValidLocation(int x, int y) {

		if ((x < 0) || (y < 0) || (x >= maze.getWidth()) || (y >= maze.getHeight())) {
			return false;
		} else if (maze.getNodeAtRowCol(x, y).isLandMine()) {
			return false;
		}

		return true;
	}

	/**
	 * Get the cost to move through a given location
	 * 
	 * @param sx The x coordinate of the node whose cost is being determined
	 * @param sy The y coordiante of the node whose cost is being determined
	 * @param tx The x coordinate of the target location
	 * @param ty The y coordinate of the target location
	 * @return The cost of movement through the given node
	 */
	public float getMovementCost() {
		return 1;
	}

	/**
	 * Get the heuristic cost for the given location. This determines in which order
	 * the locations are processed.
	 * 
	 * @param x    The x coordinate of the current node
	 * @param y    The y coordinate of the current node
	 * @param endX The x coordinate of the target location
	 * @param endY The y coordinate of the target location
	 * @return The heuristic cost assigned to the node
	 */
	public float getHeuristicCost(int x, int y, int endX, int endY, String heuristicToUse) {
		if (heuristicToUse.equals(AStarHeuristic.algorithms[0])) {
			return AStarHeuristic.getCostChebyshevHeuristic(x, y, endX, endY);
		} else if (heuristicToUse.equals(AStarHeuristic.algorithms[1])) {
			return AStarHeuristic.getCostEuclideanDistance(x, y, endX, endY);
		} else if (heuristicToUse.equals(AStarHeuristic.algorithms[2])) {
			AStarHeuristic.getCostManhattanDistance(x, y, endX, endY);
		}

		return AStarHeuristic.getCostEuclideanDistance(x, y, endX, endY);
	}

}
