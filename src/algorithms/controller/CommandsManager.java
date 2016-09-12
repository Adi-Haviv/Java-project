package algorithms.controller;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.model.Model;
import algorithms.view.View;

public class CommandsManager {
	
	private Model model;
	private View view;
		
	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;		
	}
	
	
	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("generate_maze", new GenerateMazeCommand());
		commands.put("display", new DisplayMazeCommand());
		
		return commands;
	}
	
	public class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			int rows = Integer.parseInt(args[1]);
			int columns = Integer.parseInt(args[2]);
			int floors = Integer.parseInt(args[3]);
			model.generateMaze(name, rows, columns , floors);
		}		
	}
	
	public class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			Maze3d maze = model.getMaze(name);
			view.displayMaze(maze);
		}
		
	}
	
	public class GetMazeCommand implements Command{
		@Override
		public void doCommand (String[] args){
			String name = args[0];
			model.getMaze(name);
			
		}
		
	}
	
	public class GetDirectoryContentsCommand implements Command{
		@Override
		public void doCommand(String[] args){
			String path = args[0];
			model.getDirectoryContents(path);
			
		}
	}
	
	public class GetCrossSectionByCommand implements Command{
		@Override
		public void doCommand (String[] args){
			int index = Integer.parseInt(args[0]);
			char axis = args[1].charAt(0);
			String name = args[2];
			model.getCrossSectionBy(index, axis, name);
		}
	}
	
}

