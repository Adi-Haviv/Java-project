package algorithms.search;

public abstract class CommonSearcher implements Searcher {

//	private int evaluatedNodes;
	
	protected Solution backTrace(State goalState) {
		Solution sol = new Solution();
		sol.add(0, goalState);
		State parent = new State();
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
