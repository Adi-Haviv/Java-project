package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.*;
import algorithms.search.Searchable;
import algorithms.search.State;

public class SearchableMazeAdapter implements Searchable<Position> {
	private Maze3d maze;
	State<Position> entry;
	State<Position> exit;
	
	public SearchableMazeAdapter(Maze3d maze){
		this.maze = maze;
		entry = new State<Position>("", 0, null, this.maze.getStartPosition());
		exit = new State<Position>("",0,null,this.maze.getGoalPosition());
	}
	@Override
	public State<Position> getStartState() {
		return entry;
	}

	@Override
	public State<Position> getGoalState() {
		return exit;
	}

	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		ArrayList<State<Position>> moves = new ArrayList<State<Position>>();
		int[] next = new int[3];
		Position curr = new Position();
		
		// Check surrounding cells and translate the library's values to words
		// 0 for left, 1 for right etc.
		for (int i = 0; i < 6; i++) {
			curr = (Position) s.getValue();
			System.arraycopy(curr.getCoords(), 0, next, 0, curr.getCoords().length);
			next = maze.move(i, next);

			if (next[0] != -1){
				switch (i) {
				case 0:
					moves.add(new State<Position>("", s.getCost() + 1, s, new Position(next[0], next[1], next[2])));
					break;
				case 1:
					moves.add(new State<Position>("", s.getCost() + 1, s, new Position(next[0], next[1], next[2])));
					break;
				case 2:
					moves.add(new State<Position>("", s.getCost() + 1, s, new Position(next[0], next[1], next[2])));
					break;
				case 3:
					moves.add(new State<Position>("", s.getCost() + 1, s, new Position(next[0], next[1], next[2])));
					break;
				case 4:
					moves.add(new State<Position>("", s.getCost() + 1, s, new Position(next[0], next[1], next[2])));
					break;
				case 5:
					moves.add(new State<Position>("", s.getCost() + 1, s, new Position(next[0], next[1], next[2])));
					break;
				}
			}
		}
		
		return moves;

	}

	@Override
	public double getMoveCost(State<Position> src, State<Position> dst){
		return dst.getCost() - src.getCost();
	}
	
}
