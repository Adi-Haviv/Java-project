package algorithms.mazeGenerators;

import algorithms.mazeGenerators.Maze3d;

public interface Maze3dGenerator {
	Maze3d generate(int sizeX, int sizeY, int sizeZ);
	
	String measureAlgorithmTime(int sizeX, int sizeY, int sizeZ);

}
