package algorithms.view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import algorithms.view.CLI;
import algorithms.controller.Command;
import algorithms.controller.Controller;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class MyView implements View {
	HashMap<String, Command> commands;
	CLI cli;
	PrintWriter out;
	BufferedReader in;
	Controller controller;
	
	MyView(BufferedReader in, PrintWriter out){
		this.in = in;
		this.out = out;
		
		cli = new CLI(in,out);
	}
	
	public void setController(Controller controller){
		this.controller = controller;
	}
	
	public void start(){
		cli.start();
	}
	
	@Override
	public void notifyMazeIsReady(String name) {
		String output = name + " maze is ready, Have Fun!";
		cli.write(output);

	}

	@Override
	public void displayMaze(Maze3d maze) {
		cli.write(maze.toString());
	}

	@Override
	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
		cli.setCommands(commands);
	}

	@Override
	public void displayDirectoryContents(String dirContents) {
		cli.write(dirContents);
		
	}

	@Override
	public void displayCrossSectionBy(int[][] maze2d) {
		cli.write(maze2d.toString());
		
	}

	@Override
	public void notifyMazeIsSaved(String name) {
		String output = name + " maze was saved succesfully";
		cli.write(output);
		
	}

	@Override
	public void notifyMazeIsLoaded(String name) {
		String output = name + " maze was loaded succesfully";
		cli.write(output);
		
	}

	@Override
	public void notifyMazeIsSolved(String name) {
		String output = name + " maze was solved";
		cli.write(output);		
	}

	@Override
	public void displaySolution(Solution<Position> sol){
		cli.write(sol.toString());
		
	}
	
	
}
