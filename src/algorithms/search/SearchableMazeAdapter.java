package algorithms.search;

import java.util.ArrayList;

import algorithms.mazeGenerators.*;

/**
 * This Adapter is used to convert a Maze3d object into a Searchable maze
 * so that it may be used inside Searchers in order to find paths.
 * 
 * @author Adi Haviv & Bar Genish
 *
 */
public class SearchableMazeAdapter implements Searchable<Position> {
	private Maze3d maze;
	State<Position> entry;
	State<Position> exit;
	
	/**
	 * Generates a Searchable Maze3d object.
	 * 
	 * @param maze Original maze to be made Searchable.
	 */
	public SearchableMazeAdapter(Maze3d maze){
		this.maze = maze;
		entry = new State<Position>("", 0, null, this.maze.getStartPosition());
		exit = new State<Position>("",0,null,this.maze.getGoalPosition());
	}
	
	/**
	 * Required method by the Searchable interface.
	 * Returns the entry State of the Searchable maze.
	 */
	@Override
	public State<Position> getStartState() {
		return entry;
	}
	
	/**
	 * Required method by the Searchable interface.
	 * Returns the exit position of the Searchable maze.
	 */
	@Override
	public State<Position> getGoalState() {
		return exit;
	}

	/**
	 * Required method by the Searchable interface.
	 * Generates a list of possible movements around a given State.
	 * A possible movement is a move that is within maze boundaries and no wall is present in the way.
	 * 
	 * @param s Position from which possible movement is checked.
	 */
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

	/**
	 * Required method by the Searchable interface.
	 * Calculates the cost of a movement from a source State to a destination State.
	 * 
	 * @param src Source Position for possible movement.
	 * @param dst Destination Position for possible movement.
	 * 
	 * @return double Cost of movement from source to destination.
	 */
	@Override
	public double getMoveCost(State<Position> src, State<Position> dst){
		return dst.getCost() - src.getCost();
	}
	
}
