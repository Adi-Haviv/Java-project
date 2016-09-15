package algorithms.controller;

import algorithms.model.Model;
import algorithms.view.View;
/**
 * <h1> MyController Class </h1>
 * This Class implements the Controller Interface
 * This class creates a connection between Model and View. 
 * @author Adi Haviv & Bar Genish
 *
 */
public class MyController implements Controller {

	private View view;
	private Model model;
	private CommandsManager commandsManager;
	
	/**
	 * Controller c'tor
	 * @param view
	 * @param model
	 */
	public MyController(View view, Model model) {
		this.view = view;
		this.model = model;
		
		commandsManager = new CommandsManager(this.model, this.view);
		view.setCommands(commandsManager.getCommandsMap());
	}
	
	/**
	 * This method operates "notifyMazeIsReady from class "MyView" by a given maze.
	 * @param name Name of maze.
	 */
	
	@Override
	public void notifyMazeIsReady(String name) {
		view.notifyMazeIsReady(name);
	}

	/**
	 * This method operates "notifyMazeIsSaved from class "MyView" by a given maze.
	 * @param name Name of maze.
	 */
	
	@Override
	public void notifyMazeIsSaved(String name) {
		view.notifyMazeIsSaved(name);
	}

	/**
	 * This method operates "notifyMazeIsLoaded" from class "MyView" by a given maze.
	 * @param name Name of maze.
	 */
	
	@Override
	public void notifyMazeIsLoaded(String name) {
		view.notifyMazeIsLoaded(name);
		
	}
	/**
	 * This method operates "notifyMazeIsSolved from class "MyView" by a given maze.
	 * @param name Name of maze.
	 */
	
	@Override
	public void notifyMazeIsSolved(String name) {
		view.notifyMazeIsSolved(name);
		
	}
	/**
	 * This method operates "write" method from class "MyView" by a given String.
	 * @param str String to be written.
	 */
	
	@Override
	public void write(String str){
		view.write(str);
	}
}
