package algorithms.demo;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;

public class Demo {
	
	public void run(int size){
		GrowingTreeGenerator mg = new GrowingTreeGenerator();
		BFS<Position> bfSearcher = new BFS<Position>();
		DFS<Position> dfSearcher = new DFS<Position>();
		Maze3d maze = mg.generate(size, size, size);
		SearchableMazeAdapter searchableMaze = new SearchableMazeAdapter(maze);
		Solution bfsSolution;
		Solution dfsSolution;
		
		bfsSolution = bfSearcher.search(searchableMaze);
		dfsSolution = dfSearcher.search(searchableMaze);
		
		System.out.println(maze);
		System.out.println(dfSearcher.getNumberOfNodesEvaluated());
		System.out.println(dfSearcher.getNumberOfNodesEvaluated());
	}
}
