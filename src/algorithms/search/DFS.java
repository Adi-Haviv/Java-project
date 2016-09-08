package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>Depth-First Search Algorithm</h1>
 * Searches for a path inside a Searchable object by following the DFS algorithm.
 * This algorithm will return the first path encountered by going deeply into the Searchable
 * from the entry to the exit.
 * 
 * Note: This algorithm does not guarantee the result is the <b>only</b> path in the Searchable,
 * nor is it supposed to be the most cost efficient path.
 * 
 * @author Adi Haviv & Bar Genish
 *
 * @param <T> The type to be used as a State inside the Searchable.
 */
public class DFS<T> extends CommonSearcher<T> {
	private State<T> startState;
	private State<T> goalState;
	private Set<State<T>> visited = new HashSet<State<T>>();
	
	/**
	 * Defines CommonSearcher's search method to follow the Depth First Search algorithm
	 * in order to find a path inside the Searchable.
	 * 
	 * @param Searchable<T> Searchable object to search path inside.
	 * @param T Searchable's type of State.
	 * 
	 * @return Solution<T> This is the first path of T's in the Searchable the algorithm found.
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		startState = s.getStartState();
		goalState = s.getGoalState();
		evaluatedNodes = 0;
		
		if (startState.equals(goalState)){
			return backTrace(goalState);
		}
		
		searchRecurse(startState, s);
		
		
		return backTrace(goalState);
	}
	
	/**
	 * Recursively searches inside the Searchable for the next cell in the path, according to DFS.
	 * 
	 * @param current Current cell to work on. From this cell's neighbors the next one will be chosen.
	 * @param s Searchable in which to search for the path.
	 */
	public void searchRecurse(State<T> current, Searchable<T> s){
		List<State<T>> neighbors = s.getAllPossibleStates(current);
		
		visited.add(current);
		evaluatedNodes++;
		if(current.equals(goalState)){
			goalState = current;
			return;
		}
		
		for(State<T> neighbor : neighbors){
			if(!(visited.contains(neighbor))){
				neighbor.setCameFrom(current);
				searchRecurse(neighbor, s);
			}
		}
	}
	
}
