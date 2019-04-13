package application.domain;

public class Node implements Comparable<Node>{
	
	private int row;
	private int col;
	private int time;
	private boolean isLandMine;
	public float cost;
	public Node parent;
	public float heuristic;
	public int level;
	
	/**
	 * Constructor
	 * 
	 * @param row
	 * @param col
	 * @param time
	 * @param isLandMine
	 */
	public Node(int row, int col, int time, boolean isLandMine) {
		this.row = row;
		this.col = col;
		this.time = time;
		this.isLandMine = isLandMine;
	}
	
	/**
	 * Constructor
	 * 
	 * @param row
	 * @param col
	 */
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
		this.time = 1;
	}
	
	/**
	 * Constructor for "Empty" nodes
	 */
	public Node() {
		this.row = -1;
		this.col = -1;
		this.isLandMine = true;
	}

	/**
	 * Set the parent of this node
	 * 
	 * @param parent The parent node which lead us to this node
	 * @return level that we have searched
	 */
	public int setParent(Node parent) {
		level = parent.level + 1;
		this.parent = parent;
		
		return level;
	}
	
	/**
	 * @see Comparable#compareTo(Node)
	 */
	public int compareTo(Node other) {
		Node o = (Node) other;
		
		float f = heuristic + cost;
		float of = o.heuristic + o.cost;
		
		if (f < of) {
			return -1;
		} else if (f > of) {
			return 1;
		} else {
			return 0;
		}
	}

	public Position2D getPosition()
	{
		return new Position2D(row, col);
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	public boolean isLandMine() {
		return isLandMine;
	}

	public void setLandMine(boolean isLandMine) {
		this.isLandMine = isLandMine;
	}

}
