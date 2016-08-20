package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class GrowingTreeGenerator extends CommonMaze3dGenerator{
	Maze3d maze;
	Random rand = new Random();
	
	// generate the 3d Maze by sizes received
	public Maze3d generate(int sizeX, int sizeY, int sizeZ) {
		int carveDir;
		maze = new Maze3d(sizeX, sizeY, sizeZ);
		List<Integer> neighbors = new ArrayList<Integer>();
		List<int[]> cells = new ArrayList<int[]>();
		List<int[]> removed = new ArrayList<int[]>();
		int[] cell = { rand.nextInt(sizeX) * 2, rand.nextInt(sizeY) * 2, rand.nextInt(sizeZ) * 2 };
		cells.add(cell);
		while(!(cells.isEmpty())){
			cell = chooseCell(cells);
			neighbors.removeAll(neighbors);
			neighbors.addAll(checkNeighbors(cell, cells, removed));
			if(neighbors.isEmpty()){
				cells.remove(cell);
				removed.add(cell);
			}
			else{
				int[] newCell = new int[3];
				System.arraycopy(cell, 0, newCell, 0, cell.length);
				carveDir = neighbors.get(rand.nextInt(neighbors.size()));
				carve(carveDir, cell);
				newCell = move(carveDir, newCell);
				cells.add(newCell);
			}
		}
		int[] entry = { rand.nextInt(sizeX) * 2, rand.nextInt(sizeY) * 2, rand.nextInt(sizeZ) * 2 };
		maze.entry.coords = entry;  
		int[] exit = { rand.nextInt(sizeX) * 2, rand.nextInt(sizeY) * 2, rand.nextInt(sizeZ) * 2 };
		while(Arrays.equals(entry, exit)){
			exit[0] = rand.nextInt(sizeX);
			exit[1] = rand.nextInt(sizeY);
			exit[2] = rand.nextInt(sizeZ);
		}
		maze.exit.coords = exit;
		return maze;
	}
	
	private boolean checkContains(int[] cell, List<int[]> cells){
		for (int i = 0; i < cells.size(); i++) {
			if(Arrays.equals(cell, cells.get(i))){
				return true;
			}
		}
		return false;
	}
	
	private void carve(int dir, int[] pos){
		switch (dir) {
		case 0:
			this.maze.maze[pos[0] - 1][pos[1]][pos[2]] = 0;
			this.maze.maze[pos[0] - 2][pos[1]][pos[2]] = 0;
			break;
		case 1:
			this.maze.maze[pos[0] + 1][pos[1]][pos[2]] = 0;
			this.maze.maze[pos[0] + 2][pos[1]][pos[2]] = 0;
			break;
		case 2:
			this.maze.maze[pos[0]][pos[1] - 1][pos[2]] = 0;
			this.maze.maze[pos[0]][pos[1] - 2][pos[2]] = 0;
			break;
		case 3:
			this.maze.maze[pos[0]][pos[1] + 1][pos[2]] = 0;
			this.maze.maze[pos[0]][pos[1] + 2][pos[2]] = 0;
			break;
		case 4:
			this.maze.maze[pos[0]][pos[1]][pos[2] - 1] = 0;
			this.maze.maze[pos[0]][pos[1]][pos[2] - 2] = 0;
			break;
		case 5:
			this.maze.maze[pos[0]][pos[1]][pos[2] + 1] = 0;
			this.maze.maze[pos[0]][pos[1]][pos[2] + 2] = 0;
			break;
		}
	}
	
	
	// Choose last cell in C
	private int[] chooseCell(List<int[]> cells){
		int[] result = cells.get(cells.size() - 1);
		return result;
	}
	// Choose cell from c randomly
//	private int[] chooseCell(List<int[]> cells){
//		Random rand = new Random();
//		int[] result = cells.get(rand.nextInt(cells.size()));
//		return result;
//	}
	
	// Checks neighbors and returns unvisited directions
	private List<Integer> checkNeighbors(int[] cell, List<int[]> cells, List<int[]> removed){
		List<Integer> neighbors = new ArrayList<Integer>();
		int[] next = new int[3];
		for (int i = 0; i < 6; i++) {
			next = move(i, cell);
			if(checkBounds(next) && !(checkContains(next, cells)) && !(checkContains(next,removed))){
				neighbors.add(i);
			}
		}
		
		return neighbors;
	}
	
	private boolean checkBounds(int[] cell){
		return cell[0] > -1 && cell[1] > -1 && cell[2] > -1 && cell[0] < maze.maze.length && cell[1] < maze.maze[0].length && cell[2] < maze.maze[0][0].length;
	}
	private int[] move(int dir, int[] cell){
		int[] next = new int[3];
		System.arraycopy(cell, 0, next, 0, cell.length);
		switch (dir) {
		case 0:
			next = goLeft(next);
			break;
		case 1:
			next = goRight(next);
			break;
		case 2:
			next = goBack(next);
			break;
		case 3:
			next = goFwd(next);
			break;
		case 4:
			next = goDown(next);
			break;
		case 5:
			next = goUp(next);
			break;
		}
		return next;
	}
	
	private int[] goLeft(int[] cell){
		if(cell[0] > 1){
			cell[0] -= 2;
		}
		else{
			cell[0] = -1;
		}
		return cell;
	}
	
	private int[] goRight(int[] cell){
		if(cell[0] < this.maze.maze.length - 2){
			cell[0] += 2;
		}
		else{
			cell[0] = -1;
		}
		return cell;
	}
	
	private int[] goBack(int[] cell){
		if(cell[1] > 1){
			cell[1] -= 2;
		}
		else{
			cell[0] = -1;
		}
		return cell;
	}
	
	private int[] goFwd(int[] cell){
		if(cell[1] < this.maze.maze[0].length - 2){
			cell[1] += 2;
		}
		else{
			cell[0] = -1;
		}
		return cell;
	}
	
	private int[] goDown(int[] cell){
		if(cell[2] > 1){
			cell[2] -= 2;
		}
		else{
			cell[0] = -1;
		}
		return cell;
	}
	
	private int[] goUp(int[] cell){
		if(cell[2] < this.maze.maze[0][0].length){
			cell[2] += 2;
		}
		else{
			cell[0] = -1;
		}
		return cell;
	}
}

