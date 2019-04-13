package application.domain;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Path {
	
	private Set<Node> nodes;
	private List<Position2D> nodePositions;
	private long runTime;
	
	public Path(Set<Node> nodes, int runTime) {
		this.nodes = nodes;
		this.runTime = runTime;		
		this.nodePositions = new ArrayList<Position2D>();
	}
	
	public Path(Set<Node> nodes)
	{
		this.nodes = nodes;
		this.runTime = nodes.size();
	}

	public Path() {
		this.nodes = new LinkedHashSet<Node>();
		this.nodePositions = new ArrayList<Position2D>();
		this.runTime = 0;
	}
	
	public void buildStep(Maze maze, int x, int y)
	{
		Node node = maze.getNodeAtRowCol(x, y);
		nodes.add(node);
	}
	
	public void setRuntime(long runTime) {
		this.runTime = runTime;
	}

	public void addNodePosition(Position2D currentPosition) {
		nodePositions.add(currentPosition);
	}
	
	public List<Position2D> getNodePositions()
	{
		return this.nodePositions;
	}

	public void removeStep(Node node) {
		nodes.remove(node);
	}

	public boolean containsNode(Node node) {
		if (nodes.contains(node)) {
			return true;
		}
		return false;
	}

	public long getRunTime() {
		return runTime;
	}

	public int getLength() {
		return nodes.size();
	}

	public void addNodePosition(Node node) {
		this.nodes.add(node);
	}
	
	public Set<Node> getNodeSet()
	{
		return this.nodes;
	}

}
