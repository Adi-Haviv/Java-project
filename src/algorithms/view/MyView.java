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
		//TODO add methods according to commands table
}
