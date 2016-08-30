package algorithms.search;

public interface Searcher<T> {
	Solution<T> search(Searchable<T> s);
	
	public int getNumberOfNodesEvaluated();
}
