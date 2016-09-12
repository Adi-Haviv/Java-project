package algorithms.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.SearchableMazeAdapter;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.controller.Controller;
import algorithms.io.MyCompressorOutputStream;
import algorithms.io.MyDecompressorInputStream;

/**
 * <h1> MyModel Class </h1>
 * This Class is the backend of the project.
 * The model holds all data for the program.
 * The model also has all methods for generating data or for handling it in any way.
 * 
 * @author Adi Haviv & Bar Genish
 *
 */
public class MyModel implements Model {
	private Controller controller;	
	private Map<String, Maze3d> mazes = new ConcurrentHashMap<String, Maze3d>();
	private Map<String, Solution<Position>> solutions = new ConcurrentHashMap<String, Solution<Position>>();
	private List<Thread> threads = new ArrayList<Thread>();
	private List<GeneratorRunnable> generators = new ArrayList<GeneratorRunnable>();
	
	/**
	 * This Class is used as a Runnable object so that it may be run as a thread.
	 * This is important if the user wishes to generate multiple mazes at once without the program freezing.
	 */
	public class GeneratorRunnable implements Runnable{
		int xSize;
		int ySize;
		int zSize;
		String name;
		GrowingTreeGenerator generator = new GrowingTreeGenerator();
		
		public GeneratorRunnable(String name, int xSize, int ySize, int zSize){
			this.xSize = xSize;
			this.ySize = ySize;
			this.zSize = zSize;
			this.name = name;
		}
		
		@Override
		public void run() {
			Maze3d maze = generator.generate(xSize, ySize, zSize);
			mazes.put(name, maze);
			
			controller.notifyMazeIsReady(name);				
		}	
		
		public void terminate(){
			generator.setIsDone(true);
		}
	}
	
	/**
	 * C'tor
	 * @param controller
	 */
	MyModel(Controller controller) {
		this.controller = controller;
	}
	
	/**
	 * This method sets the controller data member to the given controller.
	 * @param controller
	 */
	public void setController(Controller controller){
		this.controller = controller;
	}
	
	/**
	 * This method generates a maze based on user inputs.
	 * @param name 
	 */
	@Override
	public void generateMaze(String name, int rows, int columns, int floors) {
		GeneratorRunnable generator = new GeneratorRunnable(name, rows, columns, floors);
		Thread thread = new Thread(generator);
		generators.add(generator);
		thread.start();
		threads.add(thread);		
	}


	/**
	 * This method returns a maze object from the list of saved mazes.
	 * 
	 * @param name Name of maze to return.
	 */
	@Override
	public Maze3d getMaze(String name) {
		return mazes.get(name);
	}
	
	/**
	 * Returns the contents of a directory in the file system.
	 */

	@Override
	public String getDirectoryContents(String path) {
		StringBuilder sb = new StringBuilder();
		File dir = new File(path);
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
		    if (file.isFile()) {
		        sb.append(file.getName());
		    }
		}
		return sb.toString();
	}

	
	/**
	 * Returns a maze's cross section by a given axis.
	 * @param index Index in specified axis at which to take cross section.
	 * @param axis Axis to be used for cross section.
	 * @param name Name of maze from which to take cross section.
	 */
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
	
	/**
	 * Saves a maze from memory to a file.
	 */

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
	
	/**
	 * Loads a maze from a file to memory.
	 */

	@Override
	public void loadMazeFromFile(String filename, String name) {
		try {
			MyDecompressorInputStream in = new MyDecompressorInputStream(new FileInputStream(filename));
			byte[] mazeArr = null;
		
			in.read(mazeArr);
			mazes.put(name, new Maze3d(mazeArr));
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Solves a maze and stores the solution in the solutions data member.
	 * 
	 * @param name Name of maze to be solved.
	 * @param algorithm Solution algorithm to be used to solve the maze.
	 */
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
	
	/**
	 * Returns a maze's solution.
	 * 
	 * @param name Name of the maze who's solution should be returned.
	 */

	@Override
	public Solution<Position> getMazeSolution(String name) {
		return solutions.get(name);
	}

	
	/**
	 * Stops all maze generation threads and dies.
	 */
	@Override
	public void exit() {
		for(GeneratorRunnable generator: generators){
			generator.terminate();
		}
		
	}

}
