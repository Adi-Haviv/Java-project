package algorithms.mazeGenerators;

import algorithms.mazeGenerators.Maze3d;

/**
 * This interface defines the basic methods needed in a class designed for maze generation.
 * 
 * @author Adi Haviv & Bar Genish
 *
 */
public interface Maze3dGenerator {
	Maze3d generate(int sizeX, int sizeY, int sizeZ);
	
	String measureAlgorithmTime(int sizeX, int sizeY, int sizeZ);

}
