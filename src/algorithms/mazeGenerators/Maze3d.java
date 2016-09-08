/**
* <h1>Maze3D</h1>
* This class is used to represent a three-dimensional maze object.
* It holds the structure of the maze, walls and paths, as well as
* entry points and exit points for completing the maze as a game. 
*
* @author Adi Haviv & Bar Genish
*/

package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algorithms.mazeGenerators.Position;

public class Maze3d{
	int[][][] maze;
	Position entry = new Position();
	Position exit = new Position();
	int rows;
	int columns;
	int floors;
	
	
	/**
	 * Default C'tor.
	 * Initializes all values of the maze to 0.
	 */
	public Maze3d(){
		maze = new int[1][1][1];
		maze[0][0][0] = 0;
		rows = 0;
		columns = 0;
		floors = 0;
	}
	
	/**
	 * This C'tor generates the maze according to user requested dimensions.
	 * The dimensions are used as 2x-1 to leave space in between cells for walls.
	 * 
	 * @param xSize Size to be used as maze's x dimension.
	 * @param ySize Size to be used as maze's y dimension.
	 * @param zSize Size to be used as maze's z dimension.
	 */
	public Maze3d(int xSize, int ySize, int zSize){
		try{
			maze = new int[(xSize * 2) - 1][(ySize * 2) - 1][(zSize * 2) - 1];
			rows = xSize;
			columns = ySize;
			floors = zSize;
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[i].length; j++) {
					for (int c = 0; c < maze[i][j].length; c++) {
						maze[i][j][c] = 1;
					}
				}
			}
		}
		catch(NegativeArraySizeException e){
			System.err.println("Invalid maze size recieved.\n" + e);
		}
	}
	
	/**
	 * This C'tor constructs a Maze3d object by a byte array.
	 * Designed to rebuild a Maze3d object from a saved file.
	 * 
	 * @param arr Array of bytes to use as input for maze.
	 */
	public Maze3d(byte[] arr) {
		int c = 0;
		this.rows = arr[c++];
		this.columns = arr[c++];
		this.floors = arr[c++];
		maze = new int[rows][columns][floors];		
	
		Position entry = new Position(arr[c++], arr[c++], arr[c++]);
		this.setStartPosition(entry);
		Position exit = new Position(arr[c++], arr[c++], arr[c++]);
		this.setGoalPosition(exit);
		
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				for (int z = 0; z < floors; z++){
					maze[x][y][z] = arr[c++];
				}
			}			
		}
	}
	
	/**
	 * This method builds an array of bytes from the Maze3d object in a form
	 * that corresponds with the C'tor for byte array.
	 * <p>
	 * Designed to convert the Maze3d object into a form fit for saving to a file.
	 * 
	 * @return byte[]
	 */
	public byte[] toByteArray() {
		ArrayList<Byte> arr = new ArrayList<Byte>();
		arr.add((byte)rows);
		arr.add((byte)columns);
		arr.add((byte)floors);
		arr.add((byte)entry.coords[0]);
		arr.add((byte)entry.coords[1]);
		arr.add((byte)entry.coords[2]);
		arr.add((byte)exit.coords[0]);
		arr.add((byte)exit.coords[1]);
		arr.add((byte)exit.coords[2]);
		
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				for (int z = 0; z < floors; z++){
					arr.add((byte)maze[x][y][z]);
				}
			}			
		}
		
		byte[] bytes = new byte[arr.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte)arr.get(i);
		}
		return bytes;
	}
	
	/**
	 * This method returns the maze's entry position.
	 * @return Position
	 */
	public Position getStartPosition(){
		return entry;
	}
	
	/**
	 * This method returns the maze's goal position.
	 * @return Position
	 */
	public Position getGoalPosition(){
		return exit;
	}
	
	/**
	 * This method checks the surrounding cells of a Position object
	 * and builds a List of neighbors that are legitimate movements.
	 * @param p Position to be used as current location for possible movements.
	 */
	public String[] getPossibleMoves(Position p){
		List<String> moves = new ArrayList<String>();
		int[] next = new int[3];
		
		// Check surrounding cells and translate the library's values to words
		// 0 for left, 1 for right etc.
		for (int i = 0; i < 6; i++) {
			System.arraycopy(p.coords, 0, next, 0, p.coords.length);
			next = move(i, next);
			if (next[0] != -1){
				switch (i) {
				case 0:
					moves.add("Left");
					break;
				case 1:
					moves.add("Right");
					break;
				case 2:
					moves.add("Back");
					break;
				case 3:
					moves.add("Forward");
					break;
				case 4:
					moves.add("Down");
					break;
				case 5:
					moves.add("Up");
					break;
				}
			}
		}
		
		return moves.toArray(new String[0]);
	}

	/**
	 * This method sets the entry Position values.
	 * @param p Position to be saved as maze's entry.
	 */
	public void setStartPosition(Position p){
		this.entry = p;
	}
	
	/**
	 * This method sets the exit Position values
	 * @param p Position to be saved as maze's goal.
	 */
	public void setGoalPosition(Position p){
		this.exit = p;
	}
	
	/**
	 * This method removes a dimension from the maze and returns the 2d cross-section of the maze.
	 * @param coord Integer of dimension to be used as location for cross-section 
	 */
	public int[][] getCrossSectionByX(int coord){
		try{
			return maze[coord];
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * This method removes a dimension from the maze and returns the 2d cross-section of the maze.
	 * @param coord Integer of dimension to be used as location for cross-section 
	 */
	public int[][] getCrossSectionByY(int coord){
		try {
			int[][] result = new int[maze.length][maze[0][0].length];
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[0][0].length; j++) {
					result[i][j] = maze[i][coord][j];
				}
			}
			
			return result;
		} catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * This method removes a dimension from the maze and returns the 2d cross-section of the maze.
	 * @param coord Integer of dimension to be used as location for cross-section 
	 */
	public int[][] getCrossSectionByZ(int coord){
		try{
			int[][] result = new int[maze.length][maze[0].length];
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[0].length; j++) {
					result[i][j] = maze[i][j][coord];
				}
			}
			return result;
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * This method performs movement designed for Gameplay, not for generation only.
	 * Will move only if still within bounds of maze and no wall exists between you and the destination.
	 * @param cell Current location from which to move in specified direction.
	 * @return int[] Location after movement was made.
	 */
	public int[] goLeft(int[] cell){
		try{
			if(cell[0] > 1 && maze[cell[0] -1][cell[1]][cell[2]] == 0){
				cell[0] -= 2;
			}
			else{
				cell[0] = -1;
			}
		} catch (IndexOutOfBoundsException e){
			cell[0] = -1;
		}
		return cell;
	}
	
	/**
	 * This method performs movement designed for Gameplay, not for generation only.
	 * Will move only if still within bounds of maze and no wall exists between you and the destination.
	 * @param cell Current location from which to move in specified direction.
	 * @return int[] Location after movement was made.
	 */
	public int[] goRight(int[] cell){
		try{
			if(cell[0] < this.maze.length - 2 && maze[cell[0] + 1][cell[1]][cell[2]] == 0){
				cell[0] += 2;
			}
			else{
				cell[0] = -1;
			}
		} catch (IndexOutOfBoundsException e){
			cell[0] = -1;
		}
		return cell;
	}
	
	/**
	 * This method performs movement designed for Gameplay, not for generation only.
	 * Will move only if still within bounds of maze and no wall exists between you and the destination.
	 * @param cell Current location from which to move in specified direction.
	 * @return int[] Location after movement was made.
	 */
	public int[] goBack(int[] cell){
		try {
			if(cell[1] > 1 && maze[cell[0]][cell[1] - 1][cell[2]] == 0){
				cell[1] -= 2;
			}
			else{
				cell[0] = -1;
			}
		} catch (IndexOutOfBoundsException e){
			cell[0] = -1;
		}
		return cell;
	}
	
	/**
	 * This method performs movement designed for Gameplay, not for generation only.
	 * Will move only if still within bounds of maze and no wall exists between you and the destination.
	 * @param cell Current location from which to move in specified direction.
	 * @return int[] Location after movement was made.
	 */
	public int[] goFwd(int[] cell){
		try{
			if(cell[1] < this.maze[0].length - 2 && maze[cell[0]][cell[1] + 1][cell[2]] == 0){
				cell[1] += 2;
			} else{
				cell[0] = -1;
			}
		} catch (IndexOutOfBoundsException e){
			cell[0] = -1;
		}
		return cell;
	}
	
	/**
	 * This method performs movement designed for Gameplay, not for generation only.
	 * Will move only if still within bounds of maze and no wall exists between you and the destination.
	 * @param cell Current location from which to move in specified direction.
	 * @return int[] Location after movement was made.
	 */
	public int[] goDown(int[] cell){
		try{
			if(cell[2] > 1 && maze[cell[0]][cell[1]][cell[2] - 1] == 0){
				cell[2] -= 2;
			}
			else{
				cell[0] = -1;
			}
		} catch (IndexOutOfBoundsException e){
			cell[0] = -1;
		}
		return cell;
	}
	
	/**
	 * This method performs movement designed for Gameplay, not for generation only.
	 * Will move only if still within bounds of maze and no wall exists between you and the destination.
	 * @param cell Current location from which to move in specified direction.
	 * @return int[] Location after movement was made.
	 */
	public int[] goUp(int[] cell){
		try{
			if(cell[2] < this.maze[0][0].length && maze[cell[0]][cell[1]][cell[2] + 1] == 0){
				cell[2] += 2;
			}
			else{
				cell[0] = -1;
			}
		} catch (IndexOutOfBoundsException e){
			cell[0] = -1;
		}
		return cell;
	}

	/**
	 * This method receives a cell and a direction to move in and creates a new cell as the next cell. 
	 * @param dir Direction to move in.
	 * @param cell Current location from which to move.
	 * @return int[] Location after movement was made.
	 */
	public int[] move(int dir, int[] cell){
		int[] next = new int[3];
		switch (dir) {
		case 0:
			next = goLeft(cell);
			break;
		case 1:
			next = goRight(cell);
			break;
		case 2:
			next = goBack(cell);
			break;
		case 3:
			next = goFwd(cell);
			break;
		case 4:
			next = goDown(cell);
			break;
		case 5:
			next = goUp(cell);
			break;
		}
		return next;
	}
	
	@Override
	public String toString(){
		return Arrays.deepToString(maze);
	}
	
	@Override
	 public boolean equals(Object obj){
 		Maze3d maze = (Maze3d) obj;
 		
 		// Checking that every cell is equal to it's parallel in the given maze.
 		for (int x = 0; x < this.maze.length; x++) {
 			for (int y = 0; y < this.maze[x].length; y++) {
 				for (int z = 0; z < this.maze[x][y].length; z++) {
 					if (this.maze[x][y][z] != maze.maze[x][y][z])
 						return false;
 				}
 			}
 		}
 		
 		if (entry.equals(maze.entry)&&(exit.equals(maze.exit)))
 			return true;
 		else
 			return false;
 			
	 } 
}
