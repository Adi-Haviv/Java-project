package algorithms.controller;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.model.Model;
import algorithms.search.Solution;
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
		commands.put("dir", new GetDirectoryContentsCommand());
		commands.put("display_cross_section", new GetCrossSectionByCommand());
		commands.put("save_maze", new SaveMazeToFileCommand());
		commands.put("load_maze", new LoadMazeFromFileCommand());
		commands.put("solve", new SolveMazeCommand());
		commands.put("display_solution", new DisplaySolutionCommand());
		
		
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
		public void doCommand(String[] args){
			int index = Integer.parseInt(args[0]);
			char axis = args[1].charAt(0);
			String name = args[2];
			model.getCrossSectionBy(index, axis, name);
		}
	}
	
	public class SaveMazeToFileCommand implements Command{
		@Override
		public void doCommand(String[] args){
			String name = args[0];
			String filename = args[1];
			model.saveMazeToFile(name, filename);
		}
	}
	
	public class LoadMazeFromFileCommand implements Command {
		@Override
		public void doCommand(String[] args){
			String filename = args[0];
			String name = args[1];
			model.loadMazeFromFile(filename, name);
		}
	}
	
	public class SolveMazeCommand implements Command{
		@Override
		public void doCommand(String[] args){
			String name = args[0];
			String algorithm = args[1];
			model.solveMaze(name, algorithm);
		}
	}
	
	public class GetMazeSolutionCommand implements Command{
		@Override
		public void doCommand(String[] args){
			String name = args[0];
			model.getMazeSolution(name);
		}
	}
	
	public class ExitCommand implements Command{
		@Override
		public void doCommand(String[] args){
			model.exit();
		}
	}

	public class DisplayMazeCommand implements Command {
		@Override
		public void doCommand(String[] args){
			String name = args[0];
			Maze3d maze = model.getMaze(name);
			view.displayMaze(maze);
		}
	}
		
	public class DisplaySolutionCommand implements Command{
		@Override
		public void doCommand(String[] args){
			String name = args[0];
			Solution<Position> sol = model.getMazeSolution(name);
			view.displaySolution(sol);
			
		}
	}
	
}

