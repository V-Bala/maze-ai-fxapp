package application.service;

import application.domain.Maze;

public class NoSolutionToMazeError extends Exception {

	public NoSolutionToMazeError(Maze maze) {
		// TODO Auto-generated constructor stub
	}

	public NoSolutionToMazeError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoSolutionToMazeError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NoSolutionToMazeError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoSolutionToMazeError(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
