package algorithms.view;

import java.util.HashMap;

import algorithms.controller.Command;
import algorithms.mazeGenerators.Maze3d;

public class MyView implements View {

	@Override
	public void notifyMazeIsReady(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMaze(Maze3d maze) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCommands(HashMap<String, Command> commands) {
		// TODO Auto-generated method stub

	}
		//TODO add CLI as a data member 
		//TODO add start method

	@Override
	public void displayDirectoryContents(String dirContents) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSectionBy(int[][] maze2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMazeIsSaved(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMazeIsLoaded(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMazeIsSolved(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(String name) {
		// TODO Auto-generated method stub
		
	}
	
	
}
