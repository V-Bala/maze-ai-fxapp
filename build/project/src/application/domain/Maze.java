package application.domain;

import java.util.Set;

public interface Maze {
	
	public void initialize();
	
	public int getWidth();

	public int getHeight();
	
	public Node getStartNode();
	
	public Node getEndNode();

	public boolean isLandMine(Node node);

	public int getTime(Node start, Node end);

	public int getLandMineCount();
	
	public Set<Node> getNodeSet();
	
	public Node getNodeAtRowCol(int row, int col);
	
	public boolean isBlocked(int x, int y);

	public void visitedNode(int xp, int yp);
 }

