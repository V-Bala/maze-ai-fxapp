import org.junit.Test;

import application.domain.Maze;
import application.domain.MazeImpl;
import application.domain.Path;
import application.service.AStarHeuristic;
import application.service.AStarPathFinder;

/**
 * TEST
 * 
 * @author vbala
 *
 */
public class MazeAIUTest 
{
	
	@Test
	public void test10x10Chebyshev()
	{
		int rowCount = 10;
		int colCount = 10;
		int landMineCount = 40; // Less than half the area
		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);
		AStarPathFinder finder = new AStarPathFinder(maze, 10000, true);
		Path path = finder.solveMaze(0, 0, maze.getWidth(), maze.getHeight(), AStarHeuristic.algorithms[0]);

		debug(maze, path);
	}
	
	@Test
	public void test25x25Chebyshev()
	{
		int rowCount = 25;
		int colCount = 25;
		int landMineCount = 100; // Less than half the area
		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);
		AStarPathFinder finder = new AStarPathFinder(maze, 10000, true);
		Path path = finder.solveMaze(0, 0, maze.getWidth(), maze.getHeight(), AStarHeuristic.algorithms[0]);

		debug(maze, path);
	}
	
	@Test
	public void test50x50Chebyshev()
	{
		int rowCount = 50;
		int colCount = 50;
		int landMineCount = 250; // Less than half the area
		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);
		AStarPathFinder finder = new AStarPathFinder(maze, 10000, true);
		Path path = finder.solveMaze(0, 0, maze.getWidth(), maze.getHeight(), AStarHeuristic.algorithms[0]);

		debug(maze, path);
	}
	
	@Test
	public void test10x10Euclidean()
	{
		int rowCount = 10;
		int colCount = 10;
		int landMineCount = 40; // Less than half the area
		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);	
		AStarPathFinder finder = new AStarPathFinder(maze, 10000, true);
		Path path = finder.solveMaze(0, 0, maze.getWidth(), maze.getHeight(), AStarHeuristic.algorithms[1]);

		debug(maze, path);
	}
	
	@Test
	public void test25x25Euclidean()
	{
		int rowCount = 25;
		int colCount = 25;
		int landMineCount = 100; // Less than half the area
		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);
		AStarPathFinder finder = new AStarPathFinder(maze, 10000, true);
		Path path = finder.solveMaze(0, 0, maze.getWidth(), maze.getHeight(), AStarHeuristic.algorithms[1]);

		debug(maze, path);
	}
	
	@Test
	public void test50x50Euclidean()
	{
		int rowCount = 50;
		int colCount = 50;
		int landMineCount = 250; // Less than half the area
		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);		
		AStarPathFinder finder = new AStarPathFinder(maze, 10000, true);
		Path path = finder.solveMaze(0, 0, maze.getWidth(), maze.getHeight(), AStarHeuristic.algorithms[1]);

		debug(maze, path);
	}

	@Test
	public void test10x10Manhattan()
	{
		int rowCount = 10;
		int colCount = 10;
		int landMineCount = 40; // Less than half the area
		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);	
		AStarPathFinder finder = new AStarPathFinder(maze, 10000, true);
		Path path = finder.solveMaze(0, 0, maze.getWidth(), maze.getHeight(), AStarHeuristic.algorithms[2]);

		debug(maze, path);
	}
	
	@Test
	public void test25x25Manhattan()
	{
		int rowCount = 25;
		int colCount = 25;
		int landMineCount = 100; // Less than half the area
		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);
		AStarPathFinder finder = new AStarPathFinder(maze, 10000, true);
		Path path = finder.solveMaze(0, 0, maze.getWidth(), maze.getHeight(), AStarHeuristic.algorithms[2]);

		debug(maze, path);
	}
	
	@Test
	public void test50x50Manhattan()
	{
		int rowCount = 50;
		int colCount = 50;
		int landMineCount = 250; // Less than half the area
		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);		
		AStarPathFinder finder = new AStarPathFinder(maze, 10000, true);
		Path path = finder.solveMaze(0, 0, maze.getWidth(), maze.getHeight(), AStarHeuristic.algorithms[2]);

		debug(maze, path);
	}
	private void debug(Maze maze, Path path)
	{
		System.out.println("-------MAZE ------");
		System.out.println("Start: " + maze.getStartNode().getRow() + ", " + maze.getStartNode().getRow());
		System.out.println("End: " + maze.getEndNode().getRow() + ", " + maze.getEndNode().getRow());
		System.out.println("-------Path ------");
		System.out.println("Nodes traversed: + " + path.getNodeSet().size());
		System.out.println("Runtime: " + path.getRunTime());
	}
}
