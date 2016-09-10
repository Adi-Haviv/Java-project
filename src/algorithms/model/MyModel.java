package algorithms.model;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.controller.Controller;

public class MyModel implements Model {
	private Controller controller;	
	private Map<String, Maze3d> mazes = new ConcurrentHashMap<String, Maze3d>();
	
	private List<Thread> threads = new ArrayList<Thread>();

	public MyModel(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void generateMaze(String name, int rows, int columns, int floors) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				GrowingTreeGenerator generator = new GrowingTreeGenerator();
				Maze3d maze = generator.generate(rows, columns , floors);
				mazes.put(name, maze);
				
				controller.notifyMazeIsReady(name);				
			}	
		});
		thread.start();
		threads.add(thread);		
	}

	@Override
	public Maze3d getMaze(String name) {
		return mazes.get(name);
	}

	@Override
	public String getDirectoryContents(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getCrossSectionBy(int index, char axis, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMazeToFile(String name, String filename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadMazeFromFile(String filename, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void solveMaze(String name, String algorithm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Solution<Position> getMazeSolution(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
