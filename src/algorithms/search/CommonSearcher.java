package algorithms.search;

public abstract class CommonSearcher<T> implements Searcher<T> {

	protected int evaluatedNodes;
	
	@Override
	public int getNumberOfNodesEvaluated(){
		return evaluatedNodes;
	}
	
	protected Solution<T> backTrace(State<T> goalState) {
		Solution<T> sol = new Solution<T>();
		sol.add(0, goalState);
		State<T> parent = new State<T>();
		parent = goalState.getCameFrom();
		
		while(true){
			sol.add(0, parent);
			
			if(parent.getCameFrom() == null){
				return sol;
			}
			
			parent = parent.getCameFrom();
		}
	}

}
