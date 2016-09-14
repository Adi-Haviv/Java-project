package algorithms.controller;

public interface Controller {
	void notifyMazeIsReady(String name);
		
	void notifyMazeIsSaved(String name);
	
	void notifyMazeIsLoaded(String name);
	
	void notifyMazeIsSolved(String name);
	
	void write(String str);
}
