package algorithms.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Searchable;
import algorithms.search.SearchableMazeAdapter;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.controller.Controller;
import algorithms.io.MyCompressorOutputStream;
import algorithms.io.MyDecompressorInputStream;

public class MyModel implements Model {
	private Controller controller;	
	private Map<String, Maze3d> mazes = new ConcurrentHashMap<String, Maze3d>();
	private Map<String, Solution> solutions = new ConcurrentHashMap<String, Solution>();
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
		switch (Character.toUpperCase(axis)) {
		case 'X':
			return mazes.get(name).getCrossSectionByX(index);
		case 'Y':
			return mazes.get(name).getCrossSectionByY(index);
		case 'Z':
			return mazes.get(name).getCrossSectionByZ(index);
		}
		return null;
	}

	@Override
	public void saveMazeToFile(String name, String filename) {
		MyCompressorOutputStream out;
		try {
			out = new MyCompressorOutputStream(new FileOutputStream(filename));
			try {
				out.write(mazes.get(name).toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadMazeFromFile(String filename, String name) {
		try {
			MyDecompressorInputStream in = new MyDecompressorInputStream(new FileInputStream(filename));
			byte[] mazeArr = null;
			try {
				in.read(mazeArr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mazes.put(name, new Maze3d(mazeArr));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void solveMaze(String name, String algorithm) {
		Searcher<Position> searcher;
		SearchableMazeAdapter maze = new SearchableMazeAdapter(mazes.get(name));
		Solution<Position> sol;
		
		switch (algorithm) {
		case "DFS":
			searcher = new DFS<Position>();
			sol = searcher.search(maze);
			solutions.put(name, sol);
			break;
		
		case "BFS":
			searcher = new BFS<Position>();
			sol = searcher.search(maze);
			solutions.put(name, sol);
			break;
			
		default:
			break;
		}
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
