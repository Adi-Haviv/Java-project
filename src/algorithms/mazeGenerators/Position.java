package algorithms.mazeGenerators;

import java.util.Arrays;

public class Position{
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
