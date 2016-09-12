package algorithms.search;

/**
 * This class defines a State in a search problem.
 * 
 * @author Adi Haviv & Bar Genish
 *
 * @param <T> Type of State to be used.
 */
public class State<T> implements Comparable<State<T>> {
	private String description;
	private double cost;
	private State<T> cameFrom;
	private T value;
	
	public State(){};
	
	public State(String desc, double c, State<T> parent, T value){
		this.setDescription(desc);
		this.setCost(c);
		this.setCameFrom(parent);
		this.setValue(value);
	}
	
	/**
	 * Getter for description data member.
	 * @return String Description of State.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Setter for description data member.
	 * @param description Description to be set as value for data member.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter for cost data member
	 * @return double Cost of State when reached from parent.
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Setter for cost data member
	 * @param cost Value to be set into data member.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * Getter for parent State
	 * @return State<T> Parent of the current State.
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}
	
	/**
	 * Setter for parent State
	 * @param cameFrom Value to be set as parent of current State.
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	/**
	 * Getter for data member
	 * @return
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * Setter for data member
	 * @param cost Value to be set into data member.
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(State<T> o) {
		return (int)(this.cost - o.cost);		
	}
	
	@Override
	public boolean equals(Object obj) {
		State<?> s = (State<?>)obj;
		return (s.getValue().equals(this.getValue()));
	}
	
	@Override
	public int hashCode(){
		return this.getValue().hashCode();
	}
	
	@Override
	public String toString(){
		return value.toString();
	}
}
