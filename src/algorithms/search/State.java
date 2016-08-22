package algorithms.search;

public class State<T> implements Comparable<State> {
	private String description;
	private double cost;
	private State cameFrom;
	private T value;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public State getCameFrom() {
		return cameFrom;
	}
	public void setCameFrom(State cameFrom) {
		this.cameFrom = cameFrom;
	}
	@Override
	public int compareTo(State o) {
		return (int)(this.cost - o.cost);		
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	
	public State(){};
	
	public State(String desc, double c, State parent, T value){
		this.setDescription(desc);
		this.setCost(c);
		this.setCameFrom(parent);
		this.setValue(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		State s = (State)obj;
		return (s.getValue().equals(this.getValue()));
	}
}
