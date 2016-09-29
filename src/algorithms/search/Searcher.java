package algorithms.search;

/**
 * Defines the basic Searcher interface.
 * Every Searcher must implement it's own search and getNumberOfNodesEvaluated methods.
 * 
 * @author Adi Haviv & Bar Genish
 *
 * @param <T> Type of State in Searchable.
 */
public interface Searcher<T> {
	Solution<T> search(Searchable<T> s);
	
	public int getNumberOfNodesEvaluated();
}
