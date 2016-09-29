package algorithms.search;

/**
 * This abstract class defines the common searcher.
 * 
 * @author Adi Haviv & Bar Genish
 *
 * @param <T> Type of state to be searched.
 */
public abstract class CommonSearcher<T> implements Searcher<T> {

	protected int evaluatedNodes;
	
	/**
	 * Getter for evaluatedNodes;
	 */
	@Override
	public int getNumberOfNodesEvaluated(){
		return evaluatedNodes;
	}
	
	/**
	 * This method generates the path from the goal state to the entry state
	 * by following each state's parent state, starting with the goal.
	 * 
	 * @param goalState Goal to be reached at the end of the path.
	 * 
	 * @return Solution<T> This is the result of the search algorithm.
	 */
	protected Solution<T> backTrace(State<T> goalState) {
		Solution<T> sol = new Solution<T>();
		sol.add(0, goalState);
		State<T> parent = new State<T>();
		parent = goalState.getCameFrom();
		
		if(parent == null){
			return sol;
		}
		else {
			while(true){			
				sol.add(0, parent);
				
				parent = parent.getCameFrom();
				
				if(parent == null){
					return sol;
				}
			}
		}
	}

}
