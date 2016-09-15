package algorithms.controller;
/**
 * <h1> Controller Interface </h1>
 * @author Adi Haviv & Bar Genish
 *
 */
public interface Controller {
	void notifyMazeIsReady(String name);
		
	void notifyMazeIsSaved(String name);
	
	void notifyMazeIsLoaded(String name);
	
	void notifyMazeIsSolved(String name);
	
	void write(String str);
}
