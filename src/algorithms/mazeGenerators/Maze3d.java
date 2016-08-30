package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algorithms.mazeGenerators.Position;

public class Maze3d{
	int[][][] maze;
	Position entry = new Position();
	Position exit = new Position();
	
	
	//default constructor for no values
	public Maze3d(){
		maze = new int[1][1][1];
		maze[0][0][0] = 0;
	}
	
	// constructor for maze sizes, initiates entire maze with 1's
	public Maze3d(int xSize, int ySize, int zSize){
		try{
			// Generate new maze object according to N*2-1 to leave space for walls
			maze = new int[(xSize * 2) - 1][(ySize * 2) - 1][(zSize * 2) - 1];
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[i].length; j++) {
					for (int k = 0; k < maze[i][j].length; k++) {
						maze[i][j][k] = 1;
					}
				}
			}
		}
		catch(NegativeArraySizeException e){
			System.err.println("Invalid maze size recieved.\n" + e);
		}
	}
	
	
	// Returns entry point position object
	public Position getStartPosition(){
		return entry;
	}
	
	// Returns exit point position object
	public Position getGoalPosition(){
		return exit;
	}
	
	// abstract methods that can vary by the type of maze.
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

	// Sets entry point values
	public void setStartPosition(int[] p){
		System.arraycopy(p, 0, entry.coords, 0, entry.coords.length);
	}
	
	// Sets exit point values
	public void setGoalPosition(int[] p){
		System.arraycopy(p, 0, exit.coords, 0, exit.coords.length);
	}
	
	// Remove x axis from maze
	public int[][] getCrossSectionByX(int coord){
		try{
			return maze[coord];
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	// Remove y axis from maze
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
	
	//remove z axis from maze
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
	
	// Movement designed for Gameplay, not for generation only.
	// Move only if still within bounds of maze and no wall exists between you and the destination
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

	// Receives a cell and a direction to move in, creates new cell as the next cell and returns it 
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
}
