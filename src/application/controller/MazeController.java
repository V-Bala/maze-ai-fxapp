package application.controller;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.Set;

import application.domain.Maze;
import application.domain.MazeImpl;
import application.domain.Node;
import application.domain.Path;
import application.service.AStarHeuristic;
import application.service.AStarPathFinder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Controller object handles user events, triggers the internal system, and
 * updates the display
 * 
 * @author vbala
 *
 */
public class MazeController {
	/**
	 * The {@link Maze} instance associated with the controller
	 */
	private Maze maze;

	/**
	 * GUI components
	 */
	@FXML
	private Button createMazeButton;

	@FXML
	private Button solveMazeButton;

	@FXML
	private TextField widthTextField;

	@FXML
	private TextField heightTextField;

	@FXML
	private TextField landMineTextField;

	@FXML
	private Pane mazePane;

	@FXML
	private TextField mazeOutputLabel;

	@FXML
	private ComboBox<String> algorithmComboBox;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 * 
	 * @throws IOException
	 * @throws ProtocolException
	 */
	@FXML
	private void initialize() throws ProtocolException, IOException {
		String[] algorithmNames = AStarHeuristic.algorithms;
		ObservableList<String> algorithms = FXCollections.observableArrayList(algorithmNames);
		algorithmComboBox.setItems(algorithms);

		// Event - Create the maze and update GUI
		createMazeButton.setOnAction((event) -> {
			initializeMaze(Integer.parseInt(widthTextField.getText()), Integer.parseInt(heightTextField.getText()),
					Integer.parseInt(landMineTextField.getText()));
		});

		// Event - Solve the maze and update GUI
		solveMazeButton.setOnAction((event) -> {
			executePathFinder(algorithmComboBox.getSelectionModel().getSelectedItem());
		});
	}

	/**
	 * Accept user input and build maze
	 * 
	 * @param width
	 * @param height
	 * @param landMineCount
	 */
	public void initializeMaze(int width, int height, int landMineCount) {

		// Clear the existing maze
		mazePane.getChildren().clear();

		// Initialize maze
		maze = new MazeImpl(width, height, landMineCount);

		// Update GUI
		Pane pane = buildMaze(maze);
		mazePane.getChildren().add(pane);
	}

	/**
	 * Creates a path finding agent and solve for shortest path solution
	 * 
	 * Called when user selects "SOLVE MAZE" button in GUI
	 */
	public void executePathFinder(String algorithmToUse) {
		// Path path;
		Path newPath;

		AStarPathFinder finder = new AStarPathFinder(maze, 10000, true);
		newPath = finder.solveMaze(0, 0, maze.getWidth(), maze.getHeight(), algorithmToUse);
		if (newPath == null) {
			mazeOutputLabel.setText("Path not found. Try reducing the landmine count!");
		} else {
			Pane pane = buildPath(maze, newPath);
			mazePane.getChildren().add(pane);

			// Print total number of nodes traversed
			mazeOutputLabel.setText("Total time taken (# of nodes): " + newPath.getNodeSet().size()
					+ ".  Runtime of A* algorithm: " + newPath.getRunTime() + " ms.");
		}
	}

	/**
	 * Draw the {@link Path} on the {@Maze} displayed in the GUI.
	 * 
	 * Also display the total time taken, etc. This is final draw method.
	 * 
	 * @param path
	 */
	private Pane buildPath(Maze maze, Path path) {

		Set<Node> nodeSet = path.getNodeSet();
		double width = maze.getWidth();
		double height = maze.getHeight();

		Pane pane = new Pane();
		buildTiles(maze, pane, width, height, nodeSet, true);

		return pane;
	}

	/**
	 * Draw the {@Maze} displayed in the GUI.
	 * 
	 * Also display the total time taken, etc. This is final draw method.
	 * 
	 * @param path
	 */
	private Pane buildMaze(Maze maze) {
		double width = maze.getWidth();
		double height = maze.getHeight();
		Set<Node> nodeSet = maze.getNodeSet();

		Pane pane = new Pane();
		buildTiles(maze, pane, width, height, nodeSet, false);

		return pane;
	}

	/**
	 * Draw {@link Rectangle}s on the {@Pane} to be displayed on the GUI.
	 * 
	 * @param pane
	 * @param width
	 * @param height
	 * @return
	 */
	private void buildTiles(Maze maze, Pane pane, double width, double height, Set<Node> pathNodeSet,
			boolean solution) {
		Rectangle[][] tiles = new Rectangle[(int) width][(int) height];

		double rectangleW = 30;
		double rectangleH = 30;

		for (int i = 0; i < width; i++) {
			// Draw rows
			for (int j = 0; j < height; j++) {
				// Draw columns
				tiles[i][j] = new Rectangle();
				tiles[i][j].setX(i * rectangleW);
				tiles[i][j].setY(j * rectangleH);
				tiles[i][j].setWidth(rectangleW);
				tiles[i][j].setHeight(rectangleH);

				if (i == 0 && j == 0) {
					tiles[i][j].setFill(Color.GREEN);
				} else if (i == (width - 1) && j == (height - 1)) {
					tiles[i][j].setFill(Color.RED);
				} else {
					tiles[i][j].setFill(Color.CORAL);
				}

				tiles[i][j].setStroke(Color.NAVY);
				pane.getChildren().add(tiles[i][j]);
			}
		}

		for (Node node : maze.getNodeSet()) {
			// Draw landmines
			if (node.isLandMine()) {
				tiles[node.getRow()][node.getCol()].setFill(Color.BLACK);
			}
		}

		if (solution) {
			System.out.println("-------Path--------");

			// Flag for drawing the solution on the grid
			for (Node node : pathNodeSet) {
				System.out.println("Node: " + node.getRow() + ", " + node.getCol());

				if ((maze.getStartNode().getRow() == node.getRow() && maze.getStartNode().getCol() == node.getCol())
						|| (maze.getEndNode().getRow() == node.getRow()
								&& maze.getEndNode().getCol() == node.getCol())) {
					continue;
				}

				tiles[node.getRow()][node.getCol()].setFill(Color.YELLOW);
			}
		}
	}

}
