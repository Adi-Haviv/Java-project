package algorithms.search;

import java.util.ArrayList;

public class Solution<T> {
	private ArrayList<State<T>> states = new ArrayList<State<T>>();

	public ArrayList<State<T>> getStates() {
		return states;
	}
	
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
