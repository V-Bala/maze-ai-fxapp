import org.junit.Test;

import application.domain.Maze;
import application.domain.MazeImpl;
import application.domain.Path;
import application.service.AStarHeuristic;
import application.service.AStarPathFinder;
import application.service.NoSolutionToMazeError;

/**
 * TEST
 * 
 * @author Vijay
 *
 */
public class MazeAIUTest {
	
	@Test
	public void test10x10Chebyshev() throws NoSolutionToMazeError
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
	public void test25x25Chebyshev() throws NoSolutionToMazeError
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
	public void test50x50Chebyshev() throws NoSolutionToMazeError
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
	public void test10x10Euclidean() throws NoSolutionToMazeError
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
	public void test25x25Euclidean() throws NoSolutionToMazeError
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
	public void test50x50Euclidean() throws NoSolutionToMazeError
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
	public void test10x10Manhattan() throws NoSolutionToMazeError
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
	public void test25x25Manhattan() throws NoSolutionToMazeError
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
	public void test50x50Manhattan() throws NoSolutionToMazeError
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
	
//	@Test
//	public void test24x24()
//	{
//		int rowCount = 25;
//		int colCount = 25;
//		int landMineCount = 5;
//		
//		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);
//		Path path = PathFinder.solveMaze(maze);
//		debug(maze, path);
//	}
//
//	@Test
//	public void test48x48()
//	{
//		int rowCount = 48;
//		int colCount = 48;
//		int landMineCount = 0;
//		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);
//		System.out.println(maze.toString());
//		Set<Node> nodeSet = maze.getNodeSet();
//		Path path = PathFinder.solveMaze(maze);
//	}
//	
//	@Test
//	public void test64x64()
//	{
//		int rowCount = 64;
//		int colCount = 64;
//		int landMineCount = 0;
//		Maze maze = new MazeImpl(rowCount, colCount, landMineCount);
//		System.out.println(maze.toString());
//		Set<Node> nodeSet = maze.getNodeSet();
//		Path path = PathFinder.solveMaze(maze);
//	}

}
