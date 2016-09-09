package algorithms.model;

import algorithms.mazeGenerators.Maze3d;

public interface Model {
	void generateMaze(String name, int rows, int columns , int floors);
	Maze3d getMaze(String name);
}