package PriorityQueue;

public class Pair<T extends Comparable<T>> {
	T element;
	int priority;
	public Pair(T element, int priority) {
		this.element=element;
		this.priority=priority;
	}
	public T getElement() {
		return this.element;
	}
	public int getPriority() {
		return this.priority;
	}
	public int compareTo(Pair<T> pair) {
		int ret=0;
		if(pair!=null) {
		if ((( pair.getElement()).compareTo(element))>0)
	        	ret= -1;
		else if ((( pair.getElement()).compareTo(element))<0)
	        	ret= (1);
		else if ((( pair.getElement()).compareTo(element))==0) {
	        	if((pair.getPriority()>priority)) {
	  //      		System.out.println("Hi came first"+pair.getElement()+element);
	        		ret= (1);
	        	}
	        	if((pair.getPriority()<priority)) {
	  //      		System.out.println("Hi came last"+pair.getElement()+element);
	        		ret= (-1);
	        	}
		}
		else {
			ret=0;
		}
		}
		return ret; 

		       
	}

}
