package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BFS extends CommonSearcher {
	
	private PriorityQueue<State> openList = new PriorityQueue<State>();
	private PriorityQueue<State> closedList = new PriorityQueue<State>();

	@Override
	public Solution search(Searchable searchable) {
		State startState = searchable.getStartState();
		State goalState = searchable.getGoalState();
		openList.add(startState);
		
		while (!openList.isEmpty()) {
			State s = openList.poll();
			closedList.add(s);
			
			if (s.equals(goalState)) {
				return backTrace(goalState);
			}
			
			ArrayList<State> neighbors = searchable.getAllPossibleMoves(s);
			for (State neighbor : neighbors) {
				// TODO : define checking of neighbors by specific state.
			}
		}
		
		return backTrace(goalState);
	}

}
