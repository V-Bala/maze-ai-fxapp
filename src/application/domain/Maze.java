package application.domain;

import java.util.Set;

/**
 * Maze interface represents a grid of nodes according to the user configuration
 * 
 * @author Vijay
 *
 */
public interface Maze {

	public void initialize();

	public int getWidth();

	public int getHeight();

	public Node getStartNode();

	public Node getEndNode();

	public Set<Node> getNodeSet();

	public Node getNodeAtRowCol(int row, int col);

	public void markVisitedNode(int xp, int yp);
}
