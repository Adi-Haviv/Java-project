package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.Arrays;

/**
 * <h1>Position class</h1>
 * Class used to represent a position in the maze.
 * The class holds the coordinates for the location in the maze
 * as well as overrides for general functionality of the maze.
 * 
 * @author Adi Haviv & Bar Genish
 *
 */
public class Position implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int x, int y, int z) {
		this.coords[0] = x;
		this.coords[1] = y;
		this.coords[2] = z;
	}

	int[] coords = new int[3];
	
	public Position(){
		coords[0] = 0;
		coords[1] = 0;
		coords[2] = 0;
	}
	
	public Position(int x, int y, int z){
		coords[0] = x;
		coords[1] = y;
		coords[2] = z;
	}
	
	public Position(Position p){
		this.coords[0] = p.getCoords()[0];
		this.coords[1] = p.getCoords()[1];
		this.coords[2] = p.getCoords()[2];
	}
	
	@Override
	public boolean equals(Object obj){
		Position p = (Position) obj;
		return Arrays.equals(this.getCoords(), p.getCoords());
	}
	
	@Override
	public int hashCode(){
		return coords[0] * 100 + coords[1] * 10 + coords[2];
	}
	
	@Override
	public String toString(){
		return String.format("{ %d, %d, %d }", coords[0], coords[1],coords[2]);
	}
}
