package algorithms.search;

import java.util.ArrayList;

/**
 * Defines the Searchable interface to be used by object who wish to be searched in by Searchers.
 * 
 * @author Adi Haviv & Bar Genish
 *
 * @param <T> Type of State in Searchable.
 */
public interface Searchable<T> {
	State<T> getStartState();
	State<T> getGoalState();
	ArrayList<State<T>> getAllPossibleStates(State<T> s);
	double getMoveCost(State<T> src, State<T> dst);
}
