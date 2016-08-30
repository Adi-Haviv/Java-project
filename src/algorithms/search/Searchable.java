package algorithms.search;

import java.util.ArrayList;

public interface Searchable<T> {
	State<T> getStartState();
	State<T> getGoalState();
	ArrayList<State<T>> getAllPossibleStates(State<T> s);
	double getMoveCost(State<T> src, State<T> dst);
}
