package algorithms.model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface Model {
	void generateMaze(String name, int rows, int columns , int floors);
	Maze3d getMaze(String name);
	String getDirectoryContents(String path);
	int[][] getCrossSectionBy(int index, char axis, String name);
	void saveMazeToFile(String name, String filename);
	void loadMazeFromFile(String filename, String name);
	void solveMaze(String name, String algorithm);
	Solution<Position> getMazeSolution(String name);
	void exit();
	
	//TODO add the table`s methods just like generate and get maze
}