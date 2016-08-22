package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {
	Maze3d simpleMaze;
	//bla bla mother fucker
	// Generate a new Maze3d object according to user dimensions
	public Maze3d generate(int sizeX, int sizeY, int sizeZ) {
		Random rand = new Random();
		simpleMaze = new Maze3d(sizeX, sizeY, sizeZ);
		List<int[]> path = new ArrayList<int[]>();
		List<Integer> possibleAxis = new ArrayList<Integer>();
		possibleAxis.add(0);
		possibleAxis.add(1);
		possibleAxis.add(2);
		
		// Generate random entry and exit points for maze, duplicate by 2 to consider walls.
		int[] entry = { rand.nextInt(sizeX) * 2, rand.nextInt(sizeY) * 2, rand.nextInt(sizeZ) * 2 };
		int[] exit = { rand.nextInt(sizeX) * 2, rand.nextInt(sizeY) * 2, rand.nextInt(sizeZ) * 2};
		int[] pos = new int[3];
		System.arraycopy(entry, 0, pos, 0, entry.length);;
		
		// Randomize the exit as long as the entry and exit points are the same
		while (Arrays.equals(entry, exit)){
			exit[0] = rand.nextInt(sizeX);
			exit[1] = rand.nextInt(sizeY);
			exit[2] = rand.nextInt(sizeZ);
		}
		
		//Generate path from entry to exit
		path.add(entry);
		path.add(exit);
		while(!(Arrays.equals(pos, exit))){
			int axis;
			
			// Randomizes a direction to move in.
			if(possibleAxis.size() == 1){
				axis = possibleAxis.get(0);
			} else {
				axis = possibleAxis.get(rand.nextInt(possibleAxis.size()));
			}
			
			// If that axis is already equal to the exit, remove it from possible directions. 
			if(pos[axis] == exit[axis]){
				possibleAxis.remove(new Integer(axis));
			}
			
			// Move towards exit on the randomized axis
			if(pos[axis] > exit[axis] && pos[axis] > 1){
				carve(axis * 2, pos);
				pos[axis] -= 1;
				path.add(path.size() - 1, pos);
				pos[axis] -= 1;
				path.add(path.size() - 1, pos);
			}
			else if (pos[axis] < exit[axis]){
				carve(axis * 2 + 1, pos);
				pos[axis] += 1;
				path.add(path.size() - 1, pos);
				pos[axis] += 1;
				path.add(path.size() - 1, pos);
			}
		}
		
		// Randomizes values for all the cells in the maze that are Not in the path
		for(int i = 0; i < simpleMaze.maze.length; i++){
			for(int j = 0; j< simpleMaze.maze[i].length; j++){
				for (int k = 0; k < simpleMaze.maze[i][j].length; k++) {
					if(!(path.contains(simpleMaze.maze[i][j][k]))){
						simpleMaze.maze[i][j][k] = (rand.nextInt() % 2 == 0) ? 1 : 0;
					}
				}
			}
		}
		
		// Saves exit and entry points to Maze3d object and returns it
		simpleMaze.entry.coords = entry;
		simpleMaze.exit.coords = exit;
		return simpleMaze;
	}
	
	// Carves path from a cell to its neighbor in the given direction, used for generation of correct path
	private void carve(int dir, int[] pos){
		switch (dir) {
		case 0:
			this.simpleMaze.maze[pos[0] - 1][pos[1]][pos[2]] = 0;
			this.simpleMaze.maze[pos[0] - 2][pos[1]][pos[2]] = 0;
			break;
		case 1:
			this.simpleMaze.maze[pos[0] + 1][pos[1]][pos[2]] = 0;
			this.simpleMaze.maze[pos[0] + 2][pos[1]][pos[2]] = 0;
			break;
		case 2:
			this.simpleMaze.maze[pos[0]][pos[1] - 1][pos[2]] = 0;
			this.simpleMaze.maze[pos[0]][pos[1] - 2][pos[2]] = 0;
			break;
		case 3:
			this.simpleMaze.maze[pos[0]][pos[1] + 1][pos[2]] = 0;
			this.simpleMaze.maze[pos[0]][pos[1] + 2][pos[2]] = 0;
			break;
		case 4:
			this.simpleMaze.maze[pos[0]][pos[1]][pos[2] - 1] = 0;
			this.simpleMaze.maze[pos[0]][pos[1]][pos[2] - 2] = 0;
			break;
		case 5:
			this.simpleMaze.maze[pos[0]][pos[1]][pos[2] + 1] = 0;
			this.simpleMaze.maze[pos[0]][pos[1]][pos[2] + 2] = 0;
			break;
		}
	}
}
