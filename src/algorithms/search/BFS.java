package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class BFS<T> extends CommonSearcher<T> {
	
	private PriorityQueue<State<T>> openList = new PriorityQueue<State<T>>();
	private Set<State<T>> closedList = new HashSet<State<T>>();

	@Override
	public Solution<T> search(Searchable<T> searchable) {
		State<T> startState = searchable.getStartState();
		State<T> goalState = searchable.getGoalState();
		openList.add(startState);
		evaluatedNodes = 0;
		
		while (!openList.isEmpty()) {
			State<T> s = openList.poll();
			closedList.add(s);
			evaluatedNodes++;
			if (s.equals(goalState)) {
				return backTrace(goalState);
			}
			
			ArrayList<State<T>> neighbors = searchable.getAllPossibleStates(s);
			for (State<T> neighbor : neighbors) {
				if (!openList.contains(neighbor) && !closedList.contains(neighbor)) {
					neighbor.setCameFrom(s);
					neighbor.setCost(s.getCost() + searchable.getMoveCost(s, neighbor));
					openList.add(neighbor);
				}
				else {
					double newPathCost = s.getCost() + searchable.getMoveCost(s, neighbor);
					if (neighbor.getCost() > newPathCost) {
						neighbor.setCost(newPathCost);
						neighbor.setCameFrom(s);
						
						if (!openList.contains(neighbor)) {
							openList.add(neighbor);
						} 
						else { // must notify the priority queue about the change of cost
							openList.remove(neighbor);
							openList.add(neighbor);
						}
					}
				}			
			}
		}
		
		return backTrace(goalState);
	}

}
