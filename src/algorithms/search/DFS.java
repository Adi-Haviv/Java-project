package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS<T> extends CommonSearcher<T> {
	private State<T> startState;
	private State<T> goalState;
	private Set<State<T>> visited = new HashSet<State<T>>();
	
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
	
	public void searchRecurse(State<T> current, Searchable<T> s){
		List<State<T>> neighbors = s.getAllPossibleStates(current);
		
		visited.add(current);
		evaluatedNodes++;
		
		for(State<T> neighbor : neighbors){
			if(!(visited.contains(neighbor))){
				neighbor.setCameFrom(current);
				searchRecurse(neighbor, s);
			}
		}
	}
	
}
