package algorithms.search;

import java.util.ArrayList;

public class Solution {
	private ArrayList<State> states;

	public ArrayList<State> getStates() {
		return states;
	}
	
	public void add(int index, State s){
		states.add(index, s);
	}
}
