package algorithms.search;

public class State<T> implements Comparable<State<T>> {
	private String description;
	private double cost;
	private State<T> cameFrom;
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
	public State<T> getCameFrom() {
		return cameFrom;
	}
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	@Override
	public int compareTo(State<T> o) {
		return (int)(this.cost - o.cost);		
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	
	public State(){};
	
	public State(String desc, double c, State<T> parent, T value){
		this.setDescription(desc);
		this.setCost(c);
		this.setCameFrom(parent);
		this.setValue(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		State<T> s = (State<T>)obj;
		return (s.getValue().equals(this.getValue()));
	}
	
	@Override
	public String toString(){
		return value.toString();
	}
}
