package algorithms.mazeGenerators;

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
	
	Position(){
		coords[0] = 0;
		coords[1] = 0;
		coords[2] = 0;
	}
	
	Position(int x, int y, int z){
		coords[0] = x;
		coords[1] = y;
		coords[2] = z;
	}
	
	@Override
	public String toString(){
		return String.format("{ %d, %d, %d }", coords[0], coords[1],coords[2]);
	}
}
