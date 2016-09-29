package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class defines a solution for a search problem.
 * A solution is a path inside a Searchable from the start to the goal.
 * 
 * @author Adi Haviv & Bar Genish
 *
 * @param <T> Type of State to be used.
 */
public class Solution<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<State<T>> states = new ArrayList<State<T>>();

	/**
	 * @return ArrayList<State<T>> A List of all states in the solution. The solution itself.
	 */
	public ArrayList<State<T>> getStates() {
		return states;
	}
	
	/**
	 * Adds a State to the Solution at a given index.
	 * Used to back trace the solution from the goal State.
	 * 
	 * @param index Index in which to insert State to list.
	 * @param s State to insert.
	 */
	public void add(int index, State<T> s){
		states.add(index, s);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(State<T> s : states){
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
}
