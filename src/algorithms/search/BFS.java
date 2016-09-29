package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * <h1>Breadth-First Search Algorithm</h1>
 * This generic algorithm receives a Searchable object and a type of State in the Searchable.
 * The algorithm then searches for the best, cost-wise, path from the Searchable's start state
 * to the Searchable's goal state.
 * 
 * Note: This algorithm doesn't guarantee the solution is the <b>only</b> solution, but is the best.
 * 
 * @author Adi Haviv & Bar Genish
 *
 * @param <T> Type of state in the problem to be used as a state in the searchable.
 */
public class BFS<T> extends CommonSearcher<T> {
	private PriorityQueue<State<T>> openList = new PriorityQueue<State<T>>();
	private Set<State<T>> closedList = new HashSet<State<T>>();

	/**
	 * Defines CommonSearcher's search method to follow the Breadth First Search algorithm
	 * in order to find the most cost-effective path inside the Searchable.
	 * 
	 * @param Searchable<T> Searchable object to search path inside.
	 * @param T Searchable's type of State.
	 * 
	 * @return Solution<T> This is the best path of T's in the Searchable.
	 */
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
			if (s.equals(goalState) && (s.getCost() < goalState.getCost() || goalState.getCost() == 0)) {
				goalState = s;
			}
			ArrayList<State<T>> neighbors = searchable.getAllPossibleStates(s);
			for (State<T> neighbor : neighbors) {
				if ((!openList.contains(neighbor)) && !(closedList.contains(neighbor))) {
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
