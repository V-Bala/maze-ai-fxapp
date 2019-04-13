# Maze-AI
A JavaFX application that utilizes the A-Star search algorithm to compute the shortest path through a “maze”. A user has the ability to specify the dimensions of the maze (represented by a 2D NxM grid of nodes), and how many landmines to place in it. The user can also tune the behavior of the A-Star algorithm by specifying the distance heuristic to use when computing the optimal path. The heuristic options currently supported are Manhattan, Euclidean, and Chebyshev. The application finds a path through a maze, given the start position (entry) is at the top left tile and the final position (exit) is at the bottom right tile. 

![Overview](images/Overview.png)

# Build and Deploy
Run the following command in the project directory:

* `ant -p` to list all available targets,
* `ant build` to build project (jar file),
* `ant run` to run sample application,
* `ant doc` to generate documentation,
* `ant clean` to clean up project folder.
