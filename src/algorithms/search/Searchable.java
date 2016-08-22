package algorithms.search;

import java.util.ArrayList;

public interface Searchable {
	State getStartState();
	State getGoalState();
	ArrayList<State> getAllPossibleMoves(State s);
}
